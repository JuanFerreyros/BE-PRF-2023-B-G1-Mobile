package com.example.be_prf_2023_b_g1_mobile.recycleViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.be_prf_2023_b_g1_mobile.R
import com.example.be_prf_2023_b_g1_mobile.fragments.StationsFragmentDirections
import com.example.be_prf_2023_b_g1_mobile.model.StationResponse


class StationAdapter (
    private var stationList: List<StationResponse>
)  : RecyclerView.Adapter<StationAdapter.StationViewHolder>(){

    class StationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val stationName: TextView = itemView.findViewById(R.id.requestName)
        val stationStatus: TextView = itemView.findViewById(R.id.requestStatus)
        val stationDetails: ImageView = itemView.findViewById(R.id.img_station_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.station_item, parent, false)
        return StationViewHolder(view)
    }

    override fun getItemCount(): Int = stationList.size

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val station = stationList[position]

        holder.stationName.text = station.name
        holder.stationStatus.text = station.status

        holder.stationDetails.setOnClickListener {
            val action = StationsFragmentDirections.actionStationsFragmentToStationDetailsFragment(station)
            it.findNavController().navigate(action)
        }
    }
}