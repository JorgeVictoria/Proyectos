package com.jovian.recyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_HERO = "arg_HERO"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
        private var superHeroId: Int? = null
        private var superHero: Superhero? = null


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            arguments?.let {
                superHeroId = it.getInt(ARG_HERO,-1)
                superHero = Superhero.getSuperHeroById(superHeroId)
            }
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return  inflater.inflate(R.layout.fragment_detail, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            view.findViewById<TextView>(R.id.tvSuperhero).text = superHero?.superhero
            view.findViewById<TextView>(R.id.tvRealName).text  = superHero?.realName
            view.findViewById<TextView>(R.id.tvDescription).text  = superHero?.description

            view.findViewById<ImageView>(R.id.ivPoster).setImageBitmap(superHero?.photo?.toBitmap(view.context))

        }




        companion object {
            @JvmStatic
            fun newInstance(heroId: Int) =
                DetailFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_HERO, heroId)
                    }
                }
        }
    }
