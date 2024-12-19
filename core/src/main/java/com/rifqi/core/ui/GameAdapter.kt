package com.rifqi.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rifqi.core.databinding.ItemListGameBinding
import com.rifqi.core.utils.DateUtils

class GameAdapter :
    ListAdapter<com.rifqi.core.domain.model.Game, GameAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((com.rifqi.core.domain.model.Game) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            ItemListGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private var binding: ItemListGameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: com.rifqi.core.domain.model.Game) {
            Glide.with(itemView.context)
                .load(data.imageUrl)
                .into(binding.ivItemImage)
            binding.tvItemTitle.text = data.name
            binding.tvItemRating.text = String.format("Rating: ${data.rating}")
            binding.tvItemReleaseDate.text = DateUtils.formatDate(data.releaseDate)
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<com.rifqi.core.domain.model.Game> =
            object : DiffUtil.ItemCallback<com.rifqi.core.domain.model.Game>() {
                override fun areItemsTheSame(
                    oldItem: com.rifqi.core.domain.model.Game,
                    newItem: com.rifqi.core.domain.model.Game
                ): Boolean {
                    return oldItem.gameId == newItem.gameId
                }

                override fun areContentsTheSame(
                    oldItem: com.rifqi.core.domain.model.Game,
                    newItem: com.rifqi.core.domain.model.Game
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}