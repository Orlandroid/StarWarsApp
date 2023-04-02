package com.example.androidbase.presentation.ui.planets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbase.databinding.ItemPeopleBinding
import com.example.androidbase.entities.remote.ResultPeople
import com.example.androidbase.entities.remote.ResultPlanet
import com.example.androidbase.presentation.extensions.click


class PlanetsAdapter(private val clickOnPeople: (ResultPlanet) -> Unit) :
    RecyclerView.Adapter<PlanetsAdapter.ViewHolder>() {

    private var listOfCategories: List<ResultPlanet> = arrayListOf()

    fun setData(lista: List<ResultPlanet>) {
        listOfCategories = lista
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(people: ResultPlanet, clickOnPeople: (ResultPlanet) -> Unit) = with(binding) {

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