package com.example.androidbase.presentation.ui.flims

import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentFilmDetailBinding
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.ui.MainActivity


class FilmDetailFragment : BaseFragment<FragmentFilmDetailBinding>(R.layout.fragment_film_detail) {


    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = "Detail"
    )

    override fun setUpUi() {

    }

}