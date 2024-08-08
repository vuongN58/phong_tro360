package ptit.vuong.phongtro.presentation.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ptit.vuong.phongtro.extension.onClick
import ptit.vuong.phongtro.extension.loadImageFromUrlWithLoading
import ptit.vuong.phongtro.extension.withThueTroUrl
import ptit.vuong.phongtro.databinding.ItemArticleBinding
import ptit.vuong.phongtro.domain.model.ArticleModel

class ArticleAdapter(
    private val onItemClicked: (ArticleModel) -> Unit
) : ListAdapter<ArticleModel, ArticleAdapter.ArticleViewHolder>(diffUtil) {

    inner class ArticleViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(articleModel: ArticleModel) {
            binding.tvTitle.text = articleModel.title
            binding.tvDescription.text = articleModel.shortDescription
            binding.ivArticle.loadImageFromUrlWithLoading(articleModel.image.withThueTroUrl())
            itemView.onClick {
                onItemClicked(articleModel)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(viewHolder: ArticleViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<ArticleModel>() {
            override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}