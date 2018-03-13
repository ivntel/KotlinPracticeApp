package com.example.ivantelisman.kotlinpracticeapp

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.ivantelisman.kotlinpracticeapp.retrofit.NetworkService

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_next.*
import retrofit2.Response


class NextActivity : AppCompatActivity() {
    @BindView(R.id.recView)
    lateinit var mRecyclerView: RecyclerView

    @BindView(R.id.editText1)
    lateinit var mEditText1: EditText

    @BindView(R.id.editText2)
    lateinit var mEditText2: EditText

    lateinit var example: Example
    private var stringList = ArrayList<String>()
    private var mAdapter: Adapter? = null
    private var mList = HashMap<Int, Int>()
    lateinit var sharedPreferences: SharedPreferences
    private var tempString: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
        ButterKnife.bind(this)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        if(savedInstanceState != null){
            stringList = savedInstanceState.getSerializable("list") as ArrayList<String>
            mList = savedInstanceState.getSerializable("hashmap") as HashMap<Int, Int>
            mAdapter = Adapter(this, stringList, mList)
            tempString = sharedPreferences.getString("Tag", "")
            Log.d("Tag", tempString)
        }else{
            networkCall()
        }

        mAdapter = Adapter(this, stringList, mList)
        val mLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.itemAnimator = DefaultItemAnimator()
        mRecyclerView!!.adapter = mAdapter
        //networkCall()
    }

    //saves all content after screen rotation
    public override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putSerializable("list", stringList)
        mList = mAdapter!!.mColorList
        outState!!.putSerializable("hashmap", mList)
    }

    @OnClick(R.id.buttonGrabText)
    fun grabText() {
        var editText1 = mEditText1.text
        var editText2 = mEditText2.text

        stringList.add(editText1.toString())
        stringList.add(editText2.toString())
        mAdapter!!.notifyDataSetChanged()
        mEditText1.text = null
        mEditText2.text = null

        //mTextView.text = editText1.toString() + editText2.toString()
    }

    private fun networkCall() {
        // var  networkService:NetworkService = NetworkService()
        NetworkService
                .serviceInstance
                .getApiCallContents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :  Observer <Response<List<Example>>>{


                    override fun onSubscribe(d: Disposable?) {

                    }
                    override fun onComplete() {

                    }

                    override fun onNext(value: Response<List<Example>>?) {
                        if (value != null) {
                            handleResponse(value)
                            //textView2.text = value.body()!!.body
                        }
                    }

                    override fun onError(e: Throwable?) {

                    }
                })

    }

    private fun handleResponse(response: Response<List<Example>>){
        //textView2.text = response

        for (i in response.body()!!.indices) {
            stringList.add(response.body()!![i].title)
            Log.d("Titles", stringList[i])
        }
        //stringList.add(response.body()!!.title)
        //stringList.add(response.body()!!.body)
        //stringList.add(response.body()!!.id.toString())
        //stringList.add(response.body()!!.userId.toString())
        mAdapter!!.notifyDataSetChanged()
    }
}
