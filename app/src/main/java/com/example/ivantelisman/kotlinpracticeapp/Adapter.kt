package com.example.ivantelisman.kotlinpracticeapp

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.widget.Button


/**
 * Created by ivantelisman on 2/15/18.
 */

class Adapter(private val mContext: Context, private val stringList: List<String>,  var mColorList: HashMap<Int, Int>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {
    val nextActivity = NextActivity()
    //var mList = HashMap<Int, String>()
    //var mColorList = HashMap<Int, Int>()

    class MyViewHolder(view: View  ) : RecyclerView.ViewHolder(view) , View.OnClickListener {
        override fun onClick(p0: View?) {
            //Log.d("Tag", "")
            //Toast.makeText(mContext, "Toast", Toast.LENGTH_LONG).show()
        }

        var title: TextView = view.findViewById<TextView>(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_item, parent, false)

        return MyViewHolder(itemView )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val title = stringList[position]
        val tempString = "Selected"
        var stringSent = ""

        holder.title.text = title

        if (mColorList!!.containsKey(position)) {
            //holder.title.text = tempString
            holder.itemView.setBackgroundColor(Color.CYAN)

        } else {
            //holder.title.text = title
            holder.itemView.setBackgroundColor(Color.WHITE)
        }

        //choose one item at a time
        holder.itemView.setOnClickListener { v ->
            Toast.makeText(mContext, title, Toast.LENGTH_LONG).show()
            stringSent = title
            if (mColorList!!.containsKey(position)) {
                //mList!!.remove(position)
                mColorList!!.remove(position)
                holder.title.text = title
                stringSent = title
            } else {
                mColorList.clear()
                notifyDataSetChanged()
                //mColorList!!.put(position, title)
                mColorList!!.put(position, Color.CYAN)
                //holder.title.text = tempString

                val layoutInflater = LayoutInflater.from(mContext)
                val promptView = layoutInflater.inflate(R.layout.layout_yes_no, null)
                val alertD = AlertDialog.Builder(mContext).create()
                val message = promptView.findViewById(R.id.textViewMessage) as TextView
                message.setText("Select this title?")
                val btnOk = promptView.findViewById(R.id.buttonOk) as Button
                btnOk.setText("Yes")
                val btnCancel = promptView.findViewById(R.id.buttonCancel) as Button
                btnCancel.setText("No")
                btnOk.setOnClickListener(View.OnClickListener {
                    alertD.dismiss()
                    nextActivity.sharedPreferences.edit().putString("Tag", "hello").apply()
                    val intent = Intent(mContext,ThirdActivity::class.java)
                    intent.putExtra("text", stringSent)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    mContext.startActivity(intent)
                })
                btnCancel.setOnClickListener(View.OnClickListener {
                    alertD.dismiss()
                })
                alertD.setCanceledOnTouchOutside(false)
                alertD.setView(promptView)
                alertD.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertD.show()
            }
            //holder.title.text = tempString
        }

    }

    override fun getItemCount(): Int {
        return stringList.size
    }
}
