package com.jovian.p2_master_detail_books

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.jovian.p2_master_detail_books.placeholder.PlaceholderContent;
import com.jovian.p2_master_detail_books.databinding.FragmentItemListBinding
import com.jovian.p2_master_detail_books.databinding.ItemListContentBinding
import java.nio.file.Files.size

/**
 * A Fragment representing a list of Pings. This fragment
 * has different presentations for handset and larger screen devices. On
 * handsets, the fragment presents a list of items, which when touched,
 * lead to a {@link ItemDetailFragment} representing
 * item details. On larger screens, the Navigation controller presents the list of items and
 * item details side-by-side using two vertical panes.
 */

class ItemListFragment : Fragment() {

    /**
     * Method to intercept global key events in the
     * item list fragment to trigger keyboard shortcuts
     * Currently provides a toast when Ctrl + Z and Ctrl + F
     * are triggered
     */
    //¿control de teclado? Implementado por la plantilla
    private val unhandledKeyEventListenerCompat =
        ViewCompat.OnUnhandledKeyEventListenerCompat { v, event ->
            if (event.keyCode == KeyEvent.KEYCODE_Z && event.isCtrlPressed) {
                Toast.makeText(
                    v.context,
                    "Undo (Ctrl + Z) shortcut triggered",
                    Toast.LENGTH_LONG
                ).show()
                true
            } else if (event.keyCode == KeyEvent.KEYCODE_F && event.isCtrlPressed) {
                Toast.makeText(
                    v.context,
                    "Find (Ctrl + F) shortcut triggered",
                    Toast.LENGTH_LONG
                ).show()
                true
            }
            false
        }

    private var _binding: FragmentItemListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //cogemos todos los elementos del layout
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.addOnUnhandledKeyEventListener(view, unhandledKeyEventListenerCompat)

        val recyclerView: RecyclerView = binding.itemList
        val acceso: FloatingActionButton? = binding.introToApp



        // Leaving this not using view binding as it relies on if the view is visible the current
        // layout configuration (layout, layout-sw600dp)
        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)

        /** Click Listener to trigger navigation based on if you have
         * a single pane layout or two pane layout
         */
        val onClickListener = View.OnClickListener { itemView ->
            val item = itemView.tag as BookItem
            val bundle = Bundle()
            bundle.putString(
                ItemDetailFragment.ARG_ITEM_ID,
                item.id
            )
            if (itemDetailFragmentContainer != null) {
                itemDetailFragmentContainer.findNavController()
                    .navigate(R.id.fragment_item_detail, bundle)
            } else {
                itemView.findNavController().navigate(R.id.show_item_detail, bundle)
            }
        }

        //con esto controlamos el boton que sale al iniciar el programa
        //basicamente mostramos un snackBar para solicitar al usuario el acceso
        //en caso afirmativo, cargamoa el recyclerview(funcion),
        //y ocultamos el simbolo y el texto que aparecen al inicio
        binding.introToApp?.setOnClickListener() {
            Snackbar.make(view, "DO YOU WANT TO LOAD DATA?", Snackbar.LENGTH_LONG).setAction("LOAD"){
                setupRecyclerView(recyclerView, onClickListener)
                (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(getResources().getDrawable((R.drawable.banlibros)))
                binding.mensajeInicial.setVisibility(View.GONE)
                acceso?.setVisibility(View.GONE) }.show()

        }


        /**
         * Context click listener to handle Right click events
         * from mice and trackpad input to provide a more native
         * experience on larger screen devices
         */
        val onContextClickListener = View.OnContextClickListener { v ->
            val item = v.tag as PlaceholderContent.PlaceholderItem
            Toast.makeText(
                v.context,
                "Context click of item " + item.id,
                Toast.LENGTH_LONG
            ).show()
            true
        }
        //setupRecyclerView(recyclerView, onClickListener)
    }


    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        onClickListener: View.OnClickListener,
        //onContextClickListener: View.OnContextClickListener
    ) {
        //inicio de la carga de datos desde el json
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(
            context?.let { BookItem.loadJson(it) },
            onClickListener,
            //onContextClickListener
        )
    }

    class SimpleItemRecyclerViewAdapter(
        private val bookItemList: MutableList<BookItem>?,
        private val onClickListener: View.OnClickListener
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val binding =
                ItemListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)

        }

        //funcion para cargar los datos de los objetos libros en los elementos de la lista
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val bookItem = bookItemList?.get(position)!!

            holder.authorView.text = bookItem.author
            holder.titleView.text = bookItem.title
            //esto deberia funcionar con la llamada a extension.kt, pero no se cargan las imagenes
            //PENDIENTE DE REVISION
            if (bookItem.id.equals("0"))holder.imagenView.setImageResource(R.drawable.watchman)
            if (bookItem.id.equals("1"))holder.imagenView.setImageResource(R.drawable.hawkins)
            if (bookItem.id.equals("2"))holder.imagenView.setImageResource(R.drawable.nightingale)
            if (bookItem.id.equals("3"))holder.imagenView.setImageResource(R.drawable.triggerwarning)
            if (bookItem.id.equals("4"))holder.imagenView.setImageResource(R.drawable.goldenson)
            if (bookItem.id.equals("5"))holder.imagenView.setImageResource(R.drawable.saintodd)
            if (bookItem.id.equals("6"))holder.imagenView.setImageResource(R.drawable.bookshot1)
            if (bookItem.id.equals("7"))holder.imagenView.setImageResource(R.drawable.modernromance)
            if (bookItem.id.equals("8"))holder.imagenView.setImageResource(R.drawable.beneaththesurface)
            //holder.imagenView.setImageBitmap(bookItem.url_image.toBitmap(holder.imagenView.context))

            with(holder.itemView) {
                tag = bookItem
                setOnClickListener(onClickListener)


                setOnLongClickListener { v ->
                    // Setting the item id as the clip data so that the drop target is able to
                    // identify the id of the content
                    val clipItem = ClipData.Item(bookItem.id)
                    val dragData = ClipData(
                        v.tag as? CharSequence,
                        arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                        clipItem
                    )

                    if (Build.VERSION.SDK_INT >= 24) {
                        v.startDragAndDrop(
                            dragData,
                            View.DragShadowBuilder(v),
                            null,
                            0
                        )
                    } else {
                        v.startDrag(
                            dragData,
                            View.DragShadowBuilder(v),
                            null,
                            0
                        )
                    }
                }
            }
        }

        //GETTER para coger cuantos libros tenemos en la coleccion
        override fun getItemCount() = bookItemList?.size ?: 0

        //añadir cosas de la clase
        inner class ViewHolder(binding: ItemListContentBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val titleView: TextView = binding.tvTitulo
            val authorView: TextView = binding.tvAuthor
            val imagenView: ImageView = binding.ivPoster

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}