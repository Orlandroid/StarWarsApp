package com.example.androidbase.presentation.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbase.databinding.ItemPeopleBinding
import com.example.androidbase.entities.remote.ResultPeople
import com.example.androidbase.presentation.extensions.click
import com.example.androidbase.presentation.extensions.loadUrl
import com.example.androidbase.presentation.util.getImageFromJson
import com.example.androidbase.presentation.util.utilimages.data.getPeopleImages


class PeopleAdapter(private val clickOnPeople: (ResultPeople) -> Unit) :
    PagingDataAdapter<ResultPeople, PeopleAdapter.ViewHolder>(PeopleComparator) {

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
        fun bind(people: ResultPeople) = with(binding) {
            root.click {
                clickOnPeople(people)
            }
            tvName.text = people.name
            tvSpecie.text = people.gender
            image.loadUrl(getImageFromJson(people.name, getPeopleImages()))
        }
    }


    object PeopleComparator : DiffUtil.ItemCallback<ResultPeople>() {
        override fun areItemsTheSame(oldItem: ResultPeople, newItem: ResultPeople): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ResultPeople, newItem: ResultPeople): Boolean {
            return oldItem == newItem
        }
    }


}