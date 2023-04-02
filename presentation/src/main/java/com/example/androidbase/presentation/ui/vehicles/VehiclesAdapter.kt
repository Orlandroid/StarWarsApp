package com.example.androidbase.presentation.ui.vehicles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbase.databinding.ItemPeopleBinding
import com.example.androidbase.entities.remote.ResultVehicle


class VehiclesAdapter(private val clickOnPeople: (ResultVehicle) -> Unit) :
    RecyclerView.Adapter<VehiclesAdapter.ViewHolder>() {

    private var listOfCategories: List<ResultVehicle> = arrayListOf()

    fun setData(lista: List<ResultVehicle>) {
        listOfCategories = lista
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(people: ResultVehicle, clickOnPeople: (ResultVehicle) -> Unit) = with(binding) {

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