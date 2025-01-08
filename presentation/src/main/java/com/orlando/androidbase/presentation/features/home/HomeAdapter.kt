package com.orlando.androidbase.presentation.features.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.orlando.androidbase.databinding.ItemMenuBinding
import com.orlando.androidbase.presentation.extensions.click

class HomeAdapter(private val clickOnMenu: (ItemMenu) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var listOfCategories: List<ItemMenu> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(homeItems: List<ItemMenu>) {
        listOfCategories = homeItems
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemMenu) = with(binding) {
            imageMenu.setImageResource(item.image)
            txtNombreMenu.text = binding.root.context.getString(item.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMenuBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listOfCategories[position]
        holder.bind(item)
        holder.itemView.click {
            clickOnMenu(item)
        }
    }

    override fun getItemCount(): Int {
        return listOfCategories.size
    }

    data class ItemMenu(
        @DrawableRes
        val image: Int,
        val title: Int
    )


}
