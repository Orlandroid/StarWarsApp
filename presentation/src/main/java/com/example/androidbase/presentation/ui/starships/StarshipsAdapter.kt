package com.example.androidbase.presentation.ui.starships

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbase.databinding.ItemPeopleBinding
import com.example.androidbase.entities.remote.ResultPeople
import com.example.androidbase.entities.remote.ResultStarship
import com.example.androidbase.presentation.extensions.click


class StarshipsAdapter(private val clickOnPeople: (ResultStarship) -> Unit) :
    RecyclerView.Adapter<StarshipsAdapter.ViewHolder>() {

    private var listOfCategories: List<ResultStarship> = arrayListOf()

    fun setData(lista: List<ResultStarship>) {
        listOfCategories = lista
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(people: ResultStarship, clickOnPeople: (ResultStarship) -> Unit) = with(binding) {

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