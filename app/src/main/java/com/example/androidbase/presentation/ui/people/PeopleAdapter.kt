package com.example.androidbase.presentation.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbase.databinding.ItemPeopleBinding
import com.example.domain.entities.remote.ResultPeople


class PeopleAdapter :
    RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    private var listOfCategories: List<ResultPeople> = arrayListOf()

    fun setData(lista: List<ResultPeople>) {
        listOfCategories = lista
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(people: ResultPeople) = with(binding) {
            tvName.text = people.name
            tvSpecie.text = people.gender
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPeopleBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfCategories[position])
    }

    override fun getItemCount(): Int {
        return listOfCategories.size
    }


}
