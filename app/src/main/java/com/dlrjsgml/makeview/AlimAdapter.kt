package com.dlrjsgml.makeview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.dlrjsgml.makeview.databinding.MainrvitemBinding

class AlimAdapter(val contentList: List<AlimData>) : RecyclerView.Adapter<AlimAdapter.Holder>() {

    interface OnItemClickListener {
        fun onItemClick(item: AlimData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlimAdapter.Holder {
        val binding = MainrvitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: AlimAdapter.Holder, position: Int) {
        val item = contentList[position]
        holder.itemTitle.text = item.title
        holder.itemName.text = item.names
        holder.itemCon.text = item.contents


        holder.itemView.setOnClickListener {
            val context = it.context
            if (context is AppCompatActivity) {
                val fragmentManager = context.supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()

                // Create a new fragment and set arguments
                val fragment = RvClickedFragment().apply {
                    arguments = Bundle().apply {
                        putString("title", item.title)
                        putString("name", item.names)
                        putString("content", item.contents)
                    }
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    fragmentTransaction.replace(R.id.main_container, fragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }, 200)
            }
        }
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    inner class Holder(val binding: MainrvitemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemTitle: TextView = itemView.findViewById(R.id.rvTitle)
        val itemName: TextView = itemView.findViewById(R.id.rvName)
        val itemCon: TextView = itemView.findViewById(R.id.rvContent)


    }
}