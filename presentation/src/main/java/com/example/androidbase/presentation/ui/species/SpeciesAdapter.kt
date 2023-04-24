package com.example.androidbase.presentation.ui.species

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbase.databinding.ItemPeopleBinding
import com.example.androidbase.entities.remote.ResultSpecie
import com.example.androidbase.presentation.extensions.click
import com.example.androidbase.presentation.extensions.loadUrl
import com.example.androidbase.presentation.util.getImageFromJson
import com.example.androidbase.presentation.util.utilimages.data.getSpeciesImages


class SpeciesAdapter(private val clickOnSpecie: (ResultSpecie) -> Unit) :
    RecyclerView.Adapter<SpeciesAdapter.ViewHolder>() {

    private var listOfSpecies: List<ResultSpecie> = arrayListOf()

    fun setData(lista: List<ResultSpecie>) {
        listOfSpecies = lista
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(specie: ResultSpecie, clickOnSpecie: (ResultSpecie) -> Unit) = with(binding) {
            binding.root.click {
                clickOnSpecie(specie)
            }
            tvName.text = specie.name
            image.loadUrl(getImageFromJson(specie.name, getSpeciesImages()))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPeopleBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfSpecies[position], clickOnSpecie)
    }

    override fun getItemCount(): Int {
        return listOfSpecies.size
    }


}