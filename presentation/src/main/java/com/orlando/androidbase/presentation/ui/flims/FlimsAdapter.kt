package com.orlando.androidbase.presentation.ui.flims

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.orlando.androidbase.entities.remote.Film
import com.orlando.androidbase.presentation.extensions.click
import com.orlando.androidbase.presentation.extensions.loadUrl
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getFilmsImages
import com.orlando.androidbase.databinding.ItemFilmBinding


class FlimsAdapter(private val clickOnFilm: (Film) -> Unit) :
    PagingDataAdapter<Film, FlimsAdapter.ViewHolder>(FilmComparator) {


    inner class ViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film) = with(binding) {
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
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    object FilmComparator : DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem.episode_id == newItem.episode_id
        }

        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem == newItem
        }
    }

}