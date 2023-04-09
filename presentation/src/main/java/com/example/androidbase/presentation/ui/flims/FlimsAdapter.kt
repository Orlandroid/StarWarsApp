package com.example.androidbase.presentation.ui.flims

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbase.databinding.ItemFilmBinding
import com.example.androidbase.entities.remote.ResultX
import com.example.androidbase.presentation.extensions.loadUrl
import com.example.androidbase.presentation.util.utilimages.data.getFilmsImages
import com.example.androidbase.presentation.util.getImageFromJson


class FlimsAdapter(private val clickOnPeople: (ResultX) -> Unit) :
    RecyclerView.Adapter<FlimsAdapter.ViewHolder>() {

    private var listOfCategories: List<ResultX> = arrayListOf()

    fun setData(lista: List<ResultX>) {
        listOfCategories = lista.sortedBy { it.release_date }
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: ResultX, clickOnPeople: (ResultX) -> Unit) = with(binding) {
            tvTitle.text = film.title
            tvEpisode.text = film.episode_id.toString()
            tvPremiere.text = film.release_date
            image.loadUrl(getImageFromJson(film.title, getFilmsImages()))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFilmBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfCategories[position], clickOnPeople)
    }

    override fun getItemCount(): Int {
        return listOfCategories.size
    }


}