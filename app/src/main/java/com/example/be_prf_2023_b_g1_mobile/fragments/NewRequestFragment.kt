package com.example.be_prf_2023_b_g1_mobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.be_prf_2023_b_g1_mobile.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NewRequestFragment : Fragment() {
    lateinit var thisView: View
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        thisView = inflater.inflate(R.layout.fragment_new_request, container, false)

        val  btnReject = thisView.findViewById<Button>(R.id.btn_cancel)

        btnReject.setOnClickListener{

            val action = NewRequestFragmentDirections.actionNewRequestFragmentToStationsFragment()
            thisView.findNavController().navigate(action)
        }

        val  btnapprove = thisView.findViewById<Button>(R.id.btn_request)

        btnapprove.setOnClickListener{

            //todo ageragr toda al logica de validacion y carga de la solictid via api

            val action = NewRequestFragmentDirections.actionNewRequestFragmentToStationsFragment()
            thisView.findNavController().navigate(action)
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
}