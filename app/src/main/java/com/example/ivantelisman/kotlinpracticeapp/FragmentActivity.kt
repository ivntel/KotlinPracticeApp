package com.example.ivantelisman.kotlinpracticeapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import butterknife.BindView
import butterknife.ButterKnife
import android.widget.CompoundButton



class FragmentActivity : AppCompatActivity() {
    @BindView(R.id.switch1)
    lateinit var mSwitch: Switch


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        ButterKnife.bind(this)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, OffFragment())
                .commit()

        mSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            // do something, the isChecked will be
            // true if the switch is in the On position
            if (isChecked){
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content, OnFragment())
                        .commit()
            }
            else{
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content, OffFragment())
                        .commit()

            }
        })
    }
}
