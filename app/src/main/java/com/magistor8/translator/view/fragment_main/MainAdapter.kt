package com.magistor8.translator.view.fragment_main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.magistor8.translator.databinding.ActivityMainRecyclerviewItemBinding
import com.magistor8.core.domain.entities.DataModel

class MainAdapter(
    private val onItemClickListener: OnListItemClickListener,
    private val data: List<DataModel>
) : RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    //Пример теневого поля
    private var dataList = data
        set(value) {
            if (value == field) return
            field = value
            notifyDataSetChanged()
        }


    fun setData(newData: List<DataModel>) {
        this.dataList = newData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder =
        RecyclerItemViewHolder(ActivityMainRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class RecyclerItemViewHolder(private val binding: ActivityMainRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerTextviewRecyclerItem.text = data.text
                binding.descriptionTextviewRecyclerItem.text = data.meanings?.get(0)?.translation?.translation
                binding.root.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onItemClickListener.onItemClick(listItemData)
    }
    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }

}