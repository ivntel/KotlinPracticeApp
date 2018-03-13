package com.example.ivantelisman.kotlinpracticeapp


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * A simple [Fragment] subclass.
 */
class OnFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_on, container, false)
        // Inflate the layout for this fragment
        return view
    }

    private fun switchFragment(){
        fragmentManager
                .beginTransaction()
                .replace(R.id.content, OffFragment())
                .commit()
    }

}
