package com.example.androidbase.presentation.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbase.databinding.ItemPeopleBinding
import com.example.androidbase.entities.remote.ResultPeople
import com.example.androidbase.presentation.extensions.click
import com.example.androidbase.presentation.extensions.loadUrl
import com.example.androidbase.presentation.util.getImageFromJson
import com.example.androidbase.presentation.util.utilimages.data.getFilmsImages
import com.example.androidbase.presentation.util.utilimages.data.getPeopleImages


class PeopleAdapter(private val clickOnPeople: (ResultPeople) -> Unit) :
    RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    private var listOfCategories: List<ResultPeople> = arrayListOf()

    fun setData(lista: List<ResultPeople>) {
        listOfCategories = lista
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(people: ResultPeople, clickOnPeople: (ResultPeople) -> Unit) = with(binding) {
            root.click {
                clickOnPeople(people)
            }
            tvName.text = people.name
            tvSpecie.text = people.gender
            image.loadUrl(getImageFromJson(people.name, getPeopleImages()))
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