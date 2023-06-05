package com.example.be_prf_2023_b_g1_mobile.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.be_prf_2023_b_g1_mobile.APIServiceBuilder.APIServiceBuilder
import com.example.be_prf_2023_b_g1_mobile.model.StationResponse
import com.example.be_prf_2023_b_g1_mobile.R
import com.example.be_prf_2023_b_g1_mobile.recycleViewAdapter.StationAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StationsFragment : Fragment() {
    lateinit var thisView: View
    lateinit var recycleStations: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        thisView = inflater.inflate(R.layout.fragment_stations, container, false)
        recycleStations = thisView.findViewById(R.id.recycleStations)

        val  btnGoToNewRequest = thisView.findViewById<FloatingActionButton>(R.id.goToNewRequest)

        btnGoToNewRequest.setOnClickListener{

            val action = StationsFragmentDirections.actionStationsFragmentToNewRequestFragment()
            thisView.findNavController().navigate(action)
        }

        val service = APIServiceBuilder.createStationService()

        service.getStations().enqueue(
            object : Callback<List<StationResponse>> {
                override fun onResponse(
                    call: Call<List<StationResponse>>,
                    response: Response<List<StationResponse>>
                ) {
                    showData(response.body()!!)
                }

                override fun onFailure(call: Call<List<StationResponse>>, t: Throwable) {
                    Log.e("RETROFIT",
                        "An error occurred while requesting stations. ERROR: ${t.message}")
                }
            }
        )

        return thisView
    }

    private fun showData(stationList: List<StationResponse>){
        recycleStations.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(context)
        recycleStations.layoutManager = linearLayoutManager
        recycleStations.adapter = StationAdapter(stationList)
    }

}