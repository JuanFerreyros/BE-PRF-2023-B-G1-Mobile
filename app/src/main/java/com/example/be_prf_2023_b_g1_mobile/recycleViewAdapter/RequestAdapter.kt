package com.example.be_prf_2023_b_g1_mobile.recycleViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.be_prf_2023_b_g1_mobile.R
import com.example.be_prf_2023_b_g1_mobile.fragments.RequestsFragmentDirections
import com.example.be_prf_2023_b_g1_mobile.fragments.StationsFragmentDirections
import com.example.be_prf_2023_b_g1_mobile.model.RequestResponse

class RequestAdapter (
    private var requestList: List<RequestResponse>
    ) : RecyclerView.Adapter<RequestAdapter.RequestViewHolder>() {

    class RequestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val requestName: TextView = itemView.findViewById(R.id.requestName)
        val requestStatus: TextView = itemView.findViewById(R.id.requestStatus)
        val requestDetails: ImageView = itemView.findViewById(R.id.img_request_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestAdapter.RequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.request_item, parent, false)
        return RequestAdapter.RequestViewHolder(view)
    }

    override fun getItemCount(): Int = requestList.size

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val request = requestList[position]

        holder.requestName.text = request.name
        holder.requestStatus.text = request.status

        holder.requestDetails.setOnClickListener {
            val action = RequestsFragmentDirections.actionRequestsFragmentToRequestFragment(request)
            it.findNavController().navigate(action)
        }
    }
}