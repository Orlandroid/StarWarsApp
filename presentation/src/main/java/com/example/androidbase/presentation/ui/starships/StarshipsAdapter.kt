package com.example.androidbase.presentation.ui.starships

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbase.databinding.ItemStarshipsBinding
import com.example.androidbase.entities.remote.ResultStarship
import com.example.androidbase.presentation.extensions.click
import com.example.androidbase.presentation.extensions.loadUrl
import com.example.androidbase.presentation.util.getImageFromJson
import com.example.androidbase.presentation.util.utilimages.data.getStartShipsImages


class StarshipsAdapter(private val clickOnStarShip: (ResultStarship) -> Unit) :
    RecyclerView.Adapter<StarshipsAdapter.ViewHolder>() {

    private var listOfCategories: List<ResultStarship> = arrayListOf()

    fun setData(lista: List<ResultStarship>) {
        listOfCategories = lista
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemStarshipsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(starShip: ResultStarship, clickOnStarShip: (ResultStarship) -> Unit) =
            with(binding) {
                binding.root.click {
                    clickOnStarShip.invoke(starShip)
                }
                tvName.text = starShip.name
                tvManufacturer.text = starShip.manufacturer
                tvModel.text = starShip.model
                image.loadUrl(getImageFromJson(starShip.name, getStartShipsImages()))
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemStarshipsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfCategories[position], clickOnStarShip)
    }

    override fun getItemCount(): Int {
        return listOfCategories.size
    }


}