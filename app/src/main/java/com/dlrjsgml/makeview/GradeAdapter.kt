package com.dlrjsgml.makeview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dlrjsgml.makeview.databinding.RvgradeitemBinding

class GradeAdapter(val gradeList: List<GradeData>): RecyclerView.Adapter<GradeAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeAdapter.Holder {
        val binding = RvgradeitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: GradeAdapter.Holder, position: Int) {
        holder.title.text = gradeList[position].grade

    }

    override fun getItemCount(): Int {
        return gradeList.size
    }

    inner class Holder(val binding : RvgradeitemBinding) : RecyclerView.ViewHolder(binding.root){
        val title = binding.rvstitle
    }
}