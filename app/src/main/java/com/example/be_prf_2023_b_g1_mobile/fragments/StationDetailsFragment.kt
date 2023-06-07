package com.example.be_prf_2023_b_g1_mobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.be_prf_2023_b_g1_mobile.R


class StationDetailsFragment : Fragment() {

    lateinit var vista: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vista = inflater.inflate(R.layout.fragment_station_details, container, false)

        val station = StationDetailsFragmentArgs.fromBundle(requireArguments()).stationSerial

        vista.findViewById<TextView>(R.id.txt_station_name).text = station.name
        vista.findViewById<TextView>(R.id.txt_email).text = station.user.mail
        vista.findViewById<TextView>(R.id.txt_created_at).text = station.created_at
        vista.findViewById<TextView>(R.id.txt_brand).text = station.brand
        vista.findViewById<TextView>(R.id.txt_serial_number).text = station.serial_number
        vista.findViewById<TextView>(R.id.txt_status).text = station.status
        vista.findViewById<TextView>(R.id.txt_user).text = station.user.user_name
        vista.findViewById<TextView>(R.id.txt_longitude).text = station.longitude.toString()
        vista.findViewById<TextView>(R.id.txt_latitude).text = station.latitude.toString()
        vista.findViewById<TextView>(R.id.txt_model).text = station.model

        val btn_back = vista.findViewById<ImageButton>(R.id.btn_back_stations)

        btn_back.setOnClickListener{
            val action = StationDetailsFragmentDirections.actionStationDetailsFragmentToStationsFragment()
            it.findNavController().navigate(action)
        }

        return vista
    }

}