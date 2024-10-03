package com.orlando.androidbase.presentation.features.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.orlando.androidbase.entities.remote.ResultPeople
import com.orlando.androidbase.presentation.extensions.click
import com.orlando.androidbase.presentation.extensions.loadUrl
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getPeopleImages
import com.orlando.androidbase.databinding.ItemPeopleBinding


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