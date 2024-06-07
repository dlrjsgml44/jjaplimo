package com.dlrjsgml.makeview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dlrjsgml.makeview.databinding.MainrvitemBinding

class AlimAdapter(val contentList: List<AlimData>) : RecyclerView.Adapter<AlimAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlimAdapter.Holder {
        val binding = MainrvitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: AlimAdapter.Holder, position: Int) {
        val item = contentList[position]
        holder.itemTitle.text = item.title
        holder.itemName.text = item.names
        holder.itemCon.text = item.contents
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