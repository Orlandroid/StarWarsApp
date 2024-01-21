package com.orlando.androidbase.presentation.ui.vehicles

import androidx.navigation.fragment.navArgs
import com.orlando.androidbase.entities.remote.ResultVehicle
import com.orlando.androidbase.presentation.base.BaseFragment
import com.orlando.androidbase.presentation.extensions.fromJson
import com.orlando.androidbase.presentation.extensions.loadUrl
import com.orlando.androidbase.presentation.ui.MainActivity
import com.orlando.androidbase.presentation.util.getImageFromJson
import com.orlando.androidbase.presentation.util.utilimages.data.getVehiclesImages
import com.orlando.androidbase.R
import com.orlando.androidbase.databinding.FragmentVehicleDetailBinding


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