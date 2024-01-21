package com.orlando.androidbase.presentation.ui.vehicles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.orlando.androidbase.entities.remote.ResultVehicle
import com.orlando.androidbase.presentation.extensions.click
import com.orlando.androidbase.presentation.extensions.loadUrl
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getVehiclesImages
import com.orlando.androidbase.databinding.ItemVehicleBinding


class VehiclesAdapter(private val clickOnVehicle: (ResultVehicle) -> Unit) :
    PagingDataAdapter<ResultVehicle, VehiclesAdapter.ViewHolder>(VehicleComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemVehicleBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: ItemVehicleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(vehicle: ResultVehicle) = with(binding) {
            root.click {
                clickOnVehicle(vehicle)
            }
            tvName.text = vehicle.name
            tvModel.text = vehicle.model
            tvManufacturer.text = vehicle.manufacturer
            image.loadUrl(getImageFromJson(vehicle.name, getVehiclesImages()))
        }
    }


    object VehicleComparator : DiffUtil.ItemCallback<ResultVehicle>() {
        override fun areItemsTheSame(oldItem: ResultVehicle, newItem: ResultVehicle): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ResultVehicle, newItem: ResultVehicle): Boolean {
            return oldItem == newItem
        }
    }
}