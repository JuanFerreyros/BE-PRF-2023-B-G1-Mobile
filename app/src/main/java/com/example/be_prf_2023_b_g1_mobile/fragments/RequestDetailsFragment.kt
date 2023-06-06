package com.example.be_prf_2023_b_g1_mobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.be_prf_2023_b_g1_mobile.R


class RequestDetailsFragment : Fragment() {

    lateinit var vista: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vista = inflater.inflate(R.layout.fragment_request_details, container, false)

        val request = RequestDetailsFragmentArgs.fromBundle(requireArguments()).requestSerial

        vista.findViewById<TextView>(R.id.txt_serial_number).text = request.serial_number
        vista.findViewById<TextView>(R.id.txt_station_name).text = request.name
        vista.findViewById<TextView>(R.id.txt_longitude).text = request.longitude.toString()
        vista.findViewById<TextView>(R.id.txt_latitude).text = request.latitude.toString()
        vista.findViewById<TextView>(R.id.txt_brand).text = request.brand
        vista.findViewById<TextView>(R.id.txt_model).text = request.model
        vista.findViewById<TextView>(R.id.txt_created_at).text = request.created_at
        vista.findViewById<TextView>(R.id.txt_status).text = request.status
        vista.findViewById<TextView>(R.id.txt_user).text = request.user.user_name

        val btn_back = vista.findViewById<Button>(R.id.btn_back_requests)

        btn_back.setOnClickListener{
            val action = RequestDetailsFragmentDirections.actionRequestDetailsFragmentToRequestsFragment()
            it.findNavController().navigate(action)
        }

        return vista
    }
}