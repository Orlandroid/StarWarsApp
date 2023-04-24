package com.example.androidbase.presentation.ui.flims

import android.util.Log
import androidx.navigation.fragment.navArgs
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentFilmDetailBinding
import com.example.androidbase.entities.remote.ResultX
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.fromJson
import com.example.androidbase.presentation.extensions.loadUrl
import com.example.androidbase.presentation.ui.MainActivity
import com.example.androidbase.presentation.util.getImageFromJson
import com.example.androidbase.presentation.util.utilimages.data.getFilmsImages


class FilmDetailFragment : BaseFragment<FragmentFilmDetailBinding>(R.layout.fragment_film_detail) {


    private val args: FilmDetailFragmentArgs by navArgs()
    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = "Detail"
    )

    override fun setUpUi() {
        bind(args.flim.fromJson())
    }



    private fun bind(film: ResultX) = with(binding) {
        tvTitulo.text = film.title
        tvEpisodeId.text = film.episode_id.toString()
        tvProducer.text = film.producer
        tvReleaseDate.text = film.release_date
        val imageUrl = getImageFromJson(film.title, getFilmsImages())
        imageFilm.loadUrl(imageUrl)
        Log.w(context?.packageName,imageUrl)
    }

}