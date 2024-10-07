package com.example.fetchproject.presentation.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchproject.R
import com.example.fetchproject.databinding.ItemViewBinding
import com.example.fetchproject.domain.model.Item

class ItemAdapter(
    private val context: Context, // Add context to the adapter constructor
    private val items: List<Item>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item, context: Context) {
            binding.listId.text = buildString {
                append(context.getString(R.string.list_id))
                append(" ")
                append(item.listId.toString())
            }
            binding.name.text = buildString {
                append(context.getString(R.string.item_name))
                append(" ")
                append(item.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position], context)
    }

    override fun getItemCount(): Int = items.size
}
