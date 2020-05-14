package com.sdk.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sdk.weatherapp.R
import kotlinx.android.synthetic.main.item_test.view.*

class RecyclerListAdapter(private val dataLists: List<String>, private val onItemClicked: (item: String) -> Unit = {}) : RecyclerView.Adapter<RecyclerListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_test, parent, false)
        return ViewHolder(view,onItemClicked)
    }

    override fun getItemCount(): Int = dataLists.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(dataLists.get(position))
    }

    class ViewHolder(contentView: View, private val onItemClicked: (item: String) -> Unit = {}) : RecyclerView.ViewHolder(contentView) {
        fun bindData(str: String) {
            with(str) {
                itemView.item_tv.text = this
                itemView.item_ll.setOnClickListener {
                    println("onItemClicked")
                    onItemClicked(this)
                }
            }
        }
    }
}