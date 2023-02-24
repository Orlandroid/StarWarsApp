package com.example.androidbase.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbase.databinding.ItemMenuBinding
import com.example.androidbase.presentation.extensions.click

class HomeAdapter(private val clickOnMenu: (ItemMenu) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var listOfCategories: List<ItemMenu> = arrayListOf()

    fun setData(lista: List<ItemMenu>) {
        listOfCategories = lista
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemMenu) = with(binding) {
            imageMenu.setImageResource(item.image)
            txtNombreMenu.text = item.title
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
        val title: String
    )


}
