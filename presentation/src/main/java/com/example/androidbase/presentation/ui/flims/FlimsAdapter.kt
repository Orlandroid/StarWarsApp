package com.example.androidbase.presentation.ui.flims

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbase.databinding.ItemFilmBinding
import com.example.androidbase.entities.remote.Film
import com.example.androidbase.presentation.extensions.click
import com.example.androidbase.presentation.extensions.loadUrl
import com.example.androidbase.presentation.util.utilimages.data.getFilmsImages
import com.example.androidbase.presentation.util.getImageFromJson


class FlimsAdapter(private val clickOnFilm: (Film) -> Unit) :
    RecyclerView.Adapter<FlimsAdapter.ViewHolder>() {

    private var listOfCategories: List<Film> = arrayListOf()

    fun setData(lista: List<Film>) {
        listOfCategories = lista.sortedBy { it.release_date }
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film, clickOnFilm: (Film) -> Unit) = with(binding) {
            tvTitle.text = film.title
            tvEpisode.text = film.episode_id.toString()
            tvPremiere.text = film.release_date
            image.loadUrl(getImageFromJson(film.title, getFilmsImages()))
            binding.root.click {
                clickOnFilm(film)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFilmBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfCategories[position], clickOnFilm)
    }

    override fun getItemCount(): Int {
        return listOfCategories.size
    }


}