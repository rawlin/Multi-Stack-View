package com.rawlin.bottomsheet.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.rawlin.bottomsheet.R
import com.rawlin.bottomsheet.databinding.EmiItemBinding
import com.rawlin.bottomsheet.models.EMICard

class EMIAdapter : RecyclerView.Adapter<EMIAdapter.EMIViewHolder>() {

    private var indexOfSelectedItem = 0

    private val differCallback = object : DiffUtil.ItemCallback<EMICard>() {

        override fun areItemsTheSame(oldItem: EMICard, newItem: EMICard): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EMICard, newItem: EMICard): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<EMICard>) {
        differ.submitList(list)
    }

    inner class EMIViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = EmiItemBinding.bind(itemView)
        @SuppressLint("SetTextI18n")
        fun bind(item: EMICard) = with(binding) {
            itemCheckbox.isSelected = item.isSelected
            price.text = "${item.perMonthPrice} /mo"
            months.text = "for ${item.duration} months"
            container.setBackgroundColor(item.cardColor)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EMIViewHolder {

        return EMIViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.emi_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EMIViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(item, position) }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((EMICard, Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (EMICard, Int) -> Unit) {
        onItemClickListener = listener
    }

}

