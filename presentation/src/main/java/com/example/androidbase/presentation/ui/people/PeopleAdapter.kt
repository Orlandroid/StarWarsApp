package com.example.androidbase.presentation.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbase.databinding.ItemPeopleBinding
import com.example.androidbase.presentation.extensions.click
import com.example.androidbase.presentation.extensions.loadUrl
import com.example.androidbase.entities.remote.PeopleResponseItem


class PeopleAdapter(private val clickOnPeople: (PeopleResponseItem) -> Unit) :
    RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    private var listOfCategories: List<PeopleResponseItem> = arrayListOf()

    fun setData(lista: List<PeopleResponseItem>) {
        listOfCategories = lista
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(people: PeopleResponseItem) = with(binding) {
            tvName.text = people.name
            tvSpecie.text = people.gender
            image.loadUrl(people.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPeopleBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listOfCategories[position]
        holder.bind(item)
        holder.itemView.click {
            clickOnPeople(item)
        }
    }

    override fun getItemCount(): Int {
        return listOfCategories.size
    }


}
