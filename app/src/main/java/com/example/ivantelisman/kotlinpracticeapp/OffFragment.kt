package com.example.ivantelisman.kotlinpracticeapp


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * A simple [Fragment] subclass.
 */
class OffFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_off, container, false)

        /*fragmentManager
                .beginTransaction()
                .replace(R.id.content, OnFragment())
                .commit()*/
        return view
    }

}
