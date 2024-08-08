package ptit.vuong.phongtro.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ptit.vuong.phongtro.databinding.ItemSearchHistoryBinding
import ptit.vuong.phongtro.domain.model.SearchHistoryModel
import ptit.vuong.phongtro.extension.onClick

class SearchHistoryAdapter(
    private val onClick: (SearchHistoryModel) -> Unit
) : ListAdapter<SearchHistoryModel, SearchHistoryAdapter.SearchHistoryViewHolder>(
    searchHistoryDiffUtil
) {

    inner class SearchHistoryViewHolder(
        private val binding: ItemSearchHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchHistoryModel) {
            binding.run {
                tvSearch.text = item.content
            }
            itemView.onClick { onClick.invoke(item) }
        }
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder =
        SearchHistoryViewHolder(
            ItemSearchHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    companion object {
        val searchHistoryDiffUtil = object : DiffUtil.ItemCallback<SearchHistoryModel>() {
            override fun areContentsTheSame(
                oldItem: SearchHistoryModel,
                newItem: SearchHistoryModel
            ): Boolean = oldItem.time == newItem.time

            override fun areItemsTheSame(
                oldItem: SearchHistoryModel,
                newItem: SearchHistoryModel
            ): Boolean = oldItem == newItem
        }
    }
}