package com.towhid.attendance.fragments.fgStore.adapter.recycler

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.towhid.attendance.databinding.ItemStoreBinding
import com.towhid.attendance.fragments.fgStore.model.Store
import com.towhid.attendance.fragments.fgSubmission.view.SubmissionFragment
import com.towhid.attendance.utils.Utils.replaceFragmentWithBackStack

class RecyclerAdapterStore(
    var activity:Activity,
    var mList: MutableList<Store>
) : RecyclerView.Adapter<RecyclerAdapterStore.MyViewHolder>() {
    class MyViewHolder(val binding: ItemStoreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(
        ItemStoreBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvName.text = mList[position].name
        holder.binding.tvAddress.text = mList[position].address
        holder.binding.llItem.setOnClickListener {
            replaceFragmentWithBackStack(activity = activity, SubmissionFragment.newInstance())
        }
    }

    override fun getItemCount(): Int = mList.size
}