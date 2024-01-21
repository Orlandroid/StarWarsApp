package com.orlando.androidbase.presentation.ui.starships

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.orlando.androidbase.entities.remote.ResultStarship
import com.orlando.androidbase.presentation.extensions.click
import com.orlando.androidbase.presentation.extensions.loadUrl
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getStartShipsImages
import com.orlando.androidbase.databinding.ItemStarshipsBinding


class StarshipsAdapter(private val clickOnStarShip: (ResultStarship) -> Unit) :
    PagingDataAdapter<ResultStarship, StarshipsAdapter.ViewHolder>(StarshipComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemStarshipsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: ItemStarshipsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(starShip: ResultStarship) = with(binding) {
            binding.root.click {
                clickOnStarShip.invoke(starShip)
            }
            tvName.text = starShip.name
            tvManufacturer.text = starShip.manufacturer
            tvModel.text = starShip.model
            image.loadUrl(getImageFromJson(starShip.name, getStartShipsImages()))
        }
    }


    object StarshipComparator : DiffUtil.ItemCallback<ResultStarship>() {
        override fun areItemsTheSame(oldItem: ResultStarship, newItem: ResultStarship): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ResultStarship, newItem: ResultStarship): Boolean {
            return oldItem == newItem
        }
    }

}