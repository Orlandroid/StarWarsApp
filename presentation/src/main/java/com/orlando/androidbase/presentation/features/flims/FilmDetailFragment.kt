package com.orlando.androidbase.presentation.features.flims

import android.util.Log
import androidx.navigation.fragment.navArgs
import com.orlando.androidbase.entities.remote.Film
import com.orlando.androidbase.presentation.base.BaseFragment
import com.orlando.androidbase.presentation.extensions.fromJson
import com.orlando.androidbase.presentation.extensions.loadUrl
import com.orlando.androidbase.presentation.features.MainActivity
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getFilmsImages
import com.orlando.androidbase.R
import com.orlando.androidbase.databinding.FragmentFilmDetailBinding


class FilmDetailFragment : BaseFragment<FragmentFilmDetailBinding>(R.layout.fragment_film_detail) {


    private val args: FilmDetailFragmentArgs by navArgs()
    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = "Detail"
    )

    override fun setUpUi() {
        bind(args.flim.fromJson())
    }



    private fun bind(film: Film) = with(binding) {
        tvTitulo.text = film.title
        tvEpisodeId.text = film.episode_id.toString()
        tvProducer.text = film.producer
        tvReleaseDate.text = film.release_date
        val imageUrl = getImageFromJson(film.title, getFilmsImages())
        imageFilm.loadUrl(imageUrl)
        Log.w(context?.packageName,imageUrl)
    }

}