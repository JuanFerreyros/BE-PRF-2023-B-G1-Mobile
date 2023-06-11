package com.example.be_prf_2023_b_g1_mobile.fragments

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import androidx.navigation.findNavController
import com.example.be_prf_2023_b_g1_mobile.APIServiceBuilder.APIServiceBuilder
import com.example.be_prf_2023_b_g1_mobile.R
import com.example.be_prf_2023_b_g1_mobile.model.NewRequestResponse
import com.example.be_prf_2023_b_g1_mobile.model.StationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NewRequestFragment : Fragment() {

    lateinit var thisView: View
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var popup: PopupWindow
    private lateinit var shadowOverlay: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        thisView = inflater.inflate(R.layout.fragment_new_request, container, false)
        shadowOverlay = thisView.findViewById(R.id.shadowOverlay)
        shadowOverlay.visibility = View.GONE

        val btnReject = thisView.findViewById<Button>(R.id.btn_cancel)

        btnReject.setOnClickListener {

            val action = NewRequestFragmentDirections.actionNewRequestFragmentToStationsFragment()
            thisView.findNavController().navigate(action)
        }


        val btnApprove = thisView.findViewById<Button>(R.id.btn_create)

        btnApprove.setOnClickListener {

            val serialNumber =
                thisView.findViewById<EditText>(R.id.txt_rqt_serial_number).text.toString()
            val name = thisView.findViewById<EditText>(R.id.txt_rqt_name).text.toString()
            val longitude =
                thisView.findViewById<EditText>(R.id.txt_rqt_longitude).text.toString().toDouble()
            val latitude =
                thisView.findViewById<EditText>(R.id.txt_rqt_latitude).text.toString().toDouble()
            val brand = thisView.findViewById<EditText>(R.id.txt_rqt_brand).text.toString()
            val model = thisView.findViewById<EditText>(R.id.txt_rqt_model).text.toString()

            val requestParams =
                NewRequestResponse(serialNumber, name, longitude, latitude, brand, model)

            val service = APIServiceBuilder.createRequestService()

            service.createNewRequest(requestParams).enqueue(
                object : Callback<NewRequestResponse> {
                    override fun onResponse(
                        call: Call<NewRequestResponse>,
                        response: Response<NewRequestResponse>
                    ) {
                        if (response.isSuccessful) {
                            showPopUpRequestSent()
                        }

                    }

                    override fun onFailure(call: Call<NewRequestResponse>, t: Throwable) {
                        Log.e(
                            "RETROFIT",
                            "An error occurred while requesting stations. ERROR: ${t.message}"
                        )
                    }

                })

        }

        return thisView
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewRequestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun showPopUpRequestSent() {
        val inflater = LayoutInflater.from(requireContext())
        val popupView = inflater.inflate(R.layout.fragment_request_sent, null)

        val btn_accept = popupView.findViewById<Button>(R.id.btn_accept_sent)

        shadowOverlay.visibility = View.VISIBLE

        btn_accept.setOnClickListener {
            popup.dismiss()
        }

        popup = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

        popup.setOnDismissListener {
            shadowOverlay.visibility = View.GONE
            val action = NewRequestFragmentDirections.actionNewRequestFragmentToStationsFragment()
            thisView.findNavController().navigate(action)
        }

        popup.showAtLocation(view, Gravity.CENTER, 0, 0)
    }
}