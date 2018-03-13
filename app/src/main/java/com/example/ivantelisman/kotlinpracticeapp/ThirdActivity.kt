package com.example.ivantelisman.kotlinpracticeapp

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.ivantelisman.kotlinpracticeapp.retrofit.NetworkService
import com.squareup.picasso.Picasso
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class ThirdActivity : AppCompatActivity() {
    @BindView(R.id.act3TV)
    lateinit var mTextView: TextView
    @BindView(R.id.picIV)
    lateinit var mImageView: ImageView
    private var picUrl: String = ""
    private var ss: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        ButterKnife.bind(this)
        getPhotoCall()
        ss = intent.getStringExtra("text")
    }

    private fun getPhotoCall(){
        NetworkService
                .serviceInstance
                .getApiCallPhoto()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: Observer <Response<Example>>{
                    override fun onComplete() {

                    }

                    override fun onNext(value: Response<Example>?) {
                        processResponse(value)
                        //picUrl = value!!.body()!!.thumbnailUrl
                    }

                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onError(e: Throwable?) {

                    }

                })

    }

    private fun processResponse(value: Response<Example>?) {
        picUrl = value!!.body()!!.thumbnailUrl
        setUpUi()


    }

    private fun setUpUi(){
        mTextView.text = ss

        Picasso
                .with(this)
                .load(Uri.parse(picUrl))
                //.load(picUrl)
                .into(mImageView)
    }

}
