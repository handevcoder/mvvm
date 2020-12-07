package com.example.mvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.databinding.ItemCommentBinding
import com.example.mvvm.model.CommentModel

class AdapterComment(private val context: Context) :
    RecyclerView.Adapter<AdapterComment.ViewHolder>() {
    var list = mutableListOf<CommentModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(commentModel: CommentModel) {
            binding.run {
                tvName.text = commentModel.name
                tvEmail.text = commentModel.email
                tvBody.text = commentModel.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCommentBinding.inflate(LayoutInflater.from(context), parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelcomen by lazy {
            list[position]
        }
        holder.bindData(modelcomen)
    }

    override fun getItemCount(): Int = list.size
}