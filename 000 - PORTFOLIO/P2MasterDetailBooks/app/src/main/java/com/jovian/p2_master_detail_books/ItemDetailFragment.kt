package com.jovian.p2_master_detail_books

import android.content.ClipData
import android.os.Bundle
import android.view.DragEvent
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jovian.p2_master_detail_books.placeholder.PlaceholderContent
import com.jovian.p2_master_detail_books.databinding.FragmentItemDetailBinding
import android.R
import android.app.ActionBar

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListFragment]
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    /**
     * The placeholder content this fragment is presenting.
     */
    private var item: BookItem? = null

    lateinit var itemDetailTextView: TextView
    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentItemDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //esto no tengo claro que hace. Preguntar a Carlos
    private val dragListener = View.OnDragListener { v, event ->
        if (event.action == DragEvent.ACTION_DROP) {
            val clipDataItem: ClipData.Item = event.clipData.getItemAt(0)
            val dragData = clipDataItem.text
            item = BookItem.ITEM_MAP[dragData]
            updateContent()
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //¿cargamos en el item el objeto libro que pedimos con el argumento item id?
        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = BookItem.ITEM_MAP[it.getString(ARG_ITEM_ID)]
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //cogemos todos los elementos de la vista
        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        toolbarLayout = binding.toolbarLayout
        itemDetailTextView = binding.itemDetail

        //actulizamos el fragmento del contenido
        updateContent()
        rootView.setOnDragListener(dragListener)

        //para cambiar el titulo en el action bar
        (activity as AppCompatActivity).supportActionBar?.title = "Book list"

        //aqui mostramos el snackBar con la fecha de publicacion del libro
        binding.fab?.setOnClickListener() { view ->
            Snackbar.make(view, "Publication date: ${item?.publication_date}", Snackbar.LENGTH_LONG )
                .setAction(""){}.show()
        }



        return rootView
    }

    //funcion para actualizar el contenido del fragmento de descripcion del libro
    private fun updateContent() {
        //ponemos el titulo del libro en el toolbar, pero solo en modo movil
        toolbarLayout?.title = item?.title

        // mostramos la descripcion del libro
        item?.let {
            itemDetailTextView.text = it.description
        }
    }

    //¿cogemos el id del libro?
    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }

    //destruccion de la vista
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}