package com.example.be_prf_2023_b_g1_mobile.fragments

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import com.example.be_prf_2023_b_g1_mobile.APIServiceBuilder.APIServiceBuilder
import com.example.be_prf_2023_b_g1_mobile.APIServiceBuilder.JWTDecoderService
import com.example.be_prf_2023_b_g1_mobile.R
import com.example.be_prf_2023_b_g1_mobile.model.StationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StationDetailsFragment : Fragment() {

    lateinit var vista: View
    private lateinit var popup: PopupWindow
    private lateinit var shadowOverlay: View
    private lateinit var btn_cancel_edit: Button
    private lateinit var btn_save: Button
    private lateinit var btn_confirm_suspend: Button
    private lateinit var btn_cancel_suspend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vista = inflater.inflate(R.layout.fragment_station_details, container, false)
        shadowOverlay = vista.findViewById(R.id.shadowOverlay)
        shadowOverlay.visibility = View.GONE

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

        val btn_edit = vista.findViewById<Button>(R.id.btn_edit)
        btn_edit.setOnClickListener {
            mostrarPopupEditarEstacion(station, it)
        }

        val btn_suspend = vista.findViewById<Button>(R.id.btn_suspend)
        btn_suspend.setOnClickListener {
            mostrarPopupSuspenderEstacion(station, it)
        }

        if (station.status == "INACTIVE"){
            btn_suspend.isEnabled = false
            btn_edit.isEnabled = false
        }

        return vista
    }

    private fun mostrarPopupEditarEstacion(station: StationResponse, view: View) {
        val inflater = LayoutInflater.from(requireContext())
        val popupView = inflater.inflate(R.layout.fragment_station_edit, null)

        val editName = popupView.findViewById<EditText>(R.id.edit_txt_name)
        editName.setText(station.name)
        btn_save = popupView.findViewById(R.id.btn_save)
        btn_cancel_edit = popupView.findViewById(R.id.btn_cancel_edit)

        shadowOverlay.visibility = View.VISIBLE

        btn_cancel_edit.setOnClickListener {
            popup.dismiss()
        }

        btn_save.setOnClickListener {
            val textoIngresado = editName.text.toString()

            val requestBody = mapOf(
                "name" to textoIngresado
            )

            val service = APIServiceBuilder.createStationService()

            service.updateStation(station._id, requestBody, JWTDecoderService.context.id).enqueue(
                object : Callback<StationResponse> {
                    override fun onResponse(
                        call: Call<StationResponse>,
                        response: Response<StationResponse>
                    ) {
                        if (response.isSuccessful) {
                            val updatedStation = response.body()!!
                            vista.findViewById<TextView>(R.id.txt_station_name).text = updatedStation.name
                            popup.dismiss()
                        } else {
                            val errorBody = response.errorBody()
                            popup.dismiss()
                        }
                    }

                    override fun onFailure(call: Call<StationResponse>, t: Throwable) {
                        Log.e("RETROFIT",
                            "An error occurred while updating the station. ERROR: ${t.message}")
                    }
                }
            )
        }

        popup = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

        popup.setOnDismissListener {
            shadowOverlay.visibility = View.GONE
        }

        popup.showAtLocation(view, Gravity.CENTER, 0, 0)
    }

    private fun mostrarPopupSuspenderEstacion(station: StationResponse, view: View) {
        val inflater = LayoutInflater.from(requireContext())
        val popupView = inflater.inflate(R.layout.fragment_station_suspend, null)

        btn_confirm_suspend = popupView.findViewById(R.id.btn_confirm_suspend)
        btn_cancel_suspend = popupView.findViewById(R.id.btn_cancel_suspend)

        shadowOverlay.visibility = View.VISIBLE

        btn_cancel_suspend.setOnClickListener {
            popup.dismiss()
        }

        btn_confirm_suspend.setOnClickListener {
            val service = APIServiceBuilder.createStationService()

            service.suspendStation(station._id, JWTDecoderService.context.id).enqueue(
                object : Callback<StationResponse> {
                    override fun onResponse(
                        call: Call<StationResponse>,
                        response: Response<StationResponse>
                    ) {
                        if (response.isSuccessful) {
                            val suspendedStation = response.body()!!
                            vista.findViewById<TextView>(R.id.txt_status).text = suspendedStation.status
                            popup.dismiss()
                            val action = StationDetailsFragmentDirections.actionStationDetailsFragmentToStationsFragment()
                            vista.findNavController().navigate(action)

                        } else {
                            val errorBody = response.errorBody()
                            popup.dismiss()
                        }
                    }

                    override fun onFailure(call: Call<StationResponse>, t: Throwable) {
                        Log.e("RETROFIT",
                            "An error occurred while updating the station. ERROR: ${t.message}")
                    }
                }
            )
        }

        popup = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

        popup.setOnDismissListener {
            shadowOverlay.visibility = View.GONE
        }

        popup.showAtLocation(view, Gravity.CENTER, 0, 0)
    }

}