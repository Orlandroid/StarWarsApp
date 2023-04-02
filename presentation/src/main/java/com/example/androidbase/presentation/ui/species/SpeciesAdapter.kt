package com.example.androidbase.presentation.ui.species

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbase.databinding.ItemPeopleBinding
import com.example.androidbase.entities.remote.ResultPeople
import com.example.androidbase.entities.remote.ResultSpecie
import com.example.androidbase.presentation.extensions.click


class SpeciesAdapter(private val clickOnPeople: (ResultSpecie) -> Unit) :
    RecyclerView.Adapter<SpeciesAdapter.ViewHolder>() {

    private var listOfCategories: List<ResultSpecie> = arrayListOf()

    fun setData(lista: List<ResultSpecie>) {
        listOfCategories = lista
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(people: ResultSpecie, clickOnPeople: (ResultSpecie) -> Unit) = with(binding) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPeopleBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfCategories[position], clickOnPeople)
    }

    override fun getItemCount(): Int {
        return listOfCategories.size
    }


}