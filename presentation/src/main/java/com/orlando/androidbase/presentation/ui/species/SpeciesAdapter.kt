package com.orlando.androidbase.presentation.ui.species

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.orlando.androidbase.entities.remote.ResultSpecie
import com.orlando.androidbase.presentation.extensions.click
import com.orlando.androidbase.presentation.extensions.loadUrl
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getSpeciesImages
import com.orlando.androidbase.databinding.ItemPeopleBinding


class SpeciesAdapter(private val clickOnSpecie: (ResultSpecie) -> Unit) :
    PagingDataAdapter<ResultSpecie, SpeciesAdapter.ViewHolder>(SpecieComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPeopleBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(specie: ResultSpecie) = with(binding) {
            binding.root.click {
                clickOnSpecie(specie)
            }
            tvName.text = specie.name
            image.loadUrl(getImageFromJson(specie.name, getSpeciesImages()))
        }
    }


    object SpecieComparator : DiffUtil.ItemCallback<ResultSpecie>() {
        override fun areItemsTheSame(oldItem: ResultSpecie, newItem: ResultSpecie): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ResultSpecie, newItem: ResultSpecie): Boolean {
            return oldItem == newItem
        }
    }

}