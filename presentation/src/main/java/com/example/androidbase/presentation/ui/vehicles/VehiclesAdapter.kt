package com.example.androidbase.presentation.ui.vehicles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbase.databinding.ItemVehicleBinding
import com.example.androidbase.entities.remote.ResultVehicle
import com.example.androidbase.presentation.extensions.click
import com.example.androidbase.presentation.extensions.loadUrl
import com.example.androidbase.presentation.util.getImageFromJson
import com.example.androidbase.presentation.util.utilimages.data.getVehiclesImages


class VehiclesAdapter(private val clickOnVehicle: (ResultVehicle) -> Unit) :
    RecyclerView.Adapter<VehiclesAdapter.ViewHolder>() {

    private var listOfCategories: List<ResultVehicle> = arrayListOf()

    fun setData(lista: List<ResultVehicle>) {
        listOfCategories = lista
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemVehicleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(vehicle: ResultVehicle, clickOnVehicle: (ResultVehicle) -> Unit) = with(binding) {
            root.click {
                clickOnVehicle(vehicle)
            }
            tvName.text = vehicle.name
            tvModel.text = vehicle.model
            tvManufacturer.text = vehicle.manufacturer
            image.loadUrl(getImageFromJson(vehicle.name, getVehiclesImages()))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemVehicleBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfCategories[position], clickOnVehicle)
    }

    override fun getItemCount(): Int {
        return listOfCategories.size
    }


}