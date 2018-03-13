package com.example.ivantelisman.kotlinpracticeapp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.OnLongClick
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity() {
    @BindView(R.id.text1)
    lateinit var mTextView: TextView

    @BindView(R.id.buttonToast)
    lateinit var mButton: Button

    @BindView(R.id.buttonGoToFrag)
    lateinit var mButtonGoToFrag: Button

    var isClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        //val btn: Button = findViewById(R.id.buttonToast)
    }

    @OnClick(R.id.buttonToast)
    fun sayHello1() {
        if(isClicked){
            mTextView.text = "Goodbye"
            isClicked = false
            "I hate you".showToast(applicationContext)
            mButton.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        }
        else{
            mTextView.text = "Hello"
            isClicked = true
            "I love you".showToast(applicationContext)
            mButton.setBackgroundColor(resources.getColor(R.color.colorAccent))
        }

    }

    @OnClick(R.id.buttonNavigate)
    fun sayHello2() {
        val intent = Intent(this, NextActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent)
    }

    @OnClick(R.id.buttonGoToFrag)
    fun goToFrag() {
        val intent = Intent(this, FragmentActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent)
    }

    fun Any.showToast(context: Context) {
        Toast.makeText(context, this.toString(), Toast.LENGTH_LONG).show()
    }

}
