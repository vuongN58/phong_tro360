package ptit.vuong.phongtro.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ptit.vuong.phongtro.databinding.ItemRoomBinding
import ptit.vuong.phongtro.domain.model.AdvertModel
import ptit.vuong.phongtro.extension.addSquareMeter
import ptit.vuong.phongtro.extension.convertToVietnamDong
import ptit.vuong.phongtro.extension.loadImageFromUrlWithLoading
import ptit.vuong.phongtro.extension.onClick

class FavoriteAdapter(
    private val onClick: (AdvertModel) -> Unit
) : ListAdapter<AdvertModel, FavoriteAdapter.AdvertViewHolder>(DIFF_CALLBACK) {

    inner class AdvertViewHolder(private val binding : ItemRoomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(advert: AdvertModel) {
            binding.tvPrice.text = advert.price.convertToVietnamDong()
            binding.tvAddress.text = advert.address
            binding.tvSize.text = advert.size.toString().addSquareMeter()
            binding.ivRoom.loadImageFromUrlWithLoading(advert.image)
            itemView.onClick {
                onClick(advert)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.AdvertViewHolder {
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdvertViewHolder(binding)
    }


    override fun onBindViewHolder(holder: FavoriteAdapter.AdvertViewHolder, position: Int) {
        val advert = getItem(position)
        if (advert != null) {
            holder.bind(advert)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AdvertModel>() {
            override fun areItemsTheSame(oldItem: AdvertModel, newItem: AdvertModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AdvertModel, newItem: AdvertModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}