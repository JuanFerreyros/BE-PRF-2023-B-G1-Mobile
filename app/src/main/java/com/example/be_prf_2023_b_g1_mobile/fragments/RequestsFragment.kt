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
import com.example.be_prf_2023_b_g1_mobile.APIServiceBuilder.RequestService
import com.example.be_prf_2023_b_g1_mobile.R
import com.example.be_prf_2023_b_g1_mobile.model.RequestResponse
import com.example.be_prf_2023_b_g1_mobile.recycleViewAdapter.RequestAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestsFragment : Fragment() {
    lateinit var thisView: View
    lateinit var recycleRequests: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        thisView = inflater.inflate(R.layout.fragment_requests, container, false)
        recycleRequests = thisView.findViewById(R.id.recycleRequests)

        val  btnGoToNewRequest = thisView.findViewById<FloatingActionButton>(R.id.goToNewRequest)

        btnGoToNewRequest.setOnClickListener{

            val action = RequestsFragmentDirections.actionRequestsFragmentToNewRequestFragment()
            thisView.findNavController().navigate(action)
        }

        val service = APIServiceBuilder.createRequestService()

        service.getRequests().enqueue(
            object : Callback<List<RequestResponse>> {
                override fun onResponse(
                    call: Call<List<RequestResponse>>,
                    response: Response<List<RequestResponse>>
                ) {
                    showData(response.body()!!)
                }

                override fun onFailure(call: Call<List<RequestResponse>>, t: Throwable) {
                    Log.e("RETROFIT",
                        "An error occurred while requesting requests. ERROR: ${t.message}")
                }
            }
        )

        return thisView
    }

    private fun showData(requestList: List<RequestResponse>){
        recycleRequests.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(context)
        recycleRequests.layoutManager = linearLayoutManager
        recycleRequests.adapter = RequestAdapter(requestList)
    }
}