package ptit.vuong.phongtro.presentation.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ptit.vuong.phongtro.databinding.ItemPhotoBinding
import ptit.vuong.phongtro.domain.model.PhotoModel
import ptit.vuong.phongtro.extension.loadImageFromUrlWithLoading
import ptit.vuong.phongtro.extension.withThueTroUrl

class PhotoAdapter : ListAdapter<PhotoModel, PhotoAdapter.PhotoViewHolder>(PhotoDiffUtil) {

    inner class PhotoViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PhotoModel) {
            binding.imageView.loadImageFromUrlWithLoading(item.image.withThueTroUrl())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(p0: PhotoViewHolder, p1: Int) {
        p0.bind(getItem(p1))
    }

    companion object {
        val PhotoDiffUtil = object : DiffUtil.ItemCallback<PhotoModel>() {
            override fun areItemsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean =
                oldItem == newItem
        }
    }

}