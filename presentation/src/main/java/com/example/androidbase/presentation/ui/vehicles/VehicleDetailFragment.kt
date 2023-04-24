package com.example.androidbase.presentation.ui.vehicles

import androidx.navigation.fragment.navArgs
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentVehicleDetailBinding
import com.example.androidbase.entities.remote.ResultVehicle
import com.example.androidbase.presentation.base.BaseFragment
import com.example.androidbase.presentation.extensions.fromJson
import com.example.androidbase.presentation.extensions.loadUrl
import com.example.androidbase.presentation.ui.MainActivity
import com.example.androidbase.presentation.util.getImageFromJson
import com.example.androidbase.presentation.util.utilimages.data.getVehiclesImages


class VehicleDetailFragment :
    BaseFragment<FragmentVehicleDetailBinding>(R.layout.fragment_vehicle_detail) {


    private val args: VehicleDetailFragmentArgs by navArgs()

    override fun configureToolbar() = MainActivity.ToolbarConfiguration(
        showToolbar = true,
        toolbarTitle = "Detail"
    )

    override fun setUpUi() {
        bind(args.vehicle.fromJson())
    }

    private fun bind(vehicle: ResultVehicle) = with(binding) {
        tvName.text = vehicle.name
        tvManufacturer.text = vehicle.manufacturer
        tvSpeed.text = vehicle.max_atmosphering_speed
        tvConsumables.text = vehicle.consumables
        imageVehicle.loadUrl(getImageFromJson(vehicle.name, getVehiclesImages()))
    }

}