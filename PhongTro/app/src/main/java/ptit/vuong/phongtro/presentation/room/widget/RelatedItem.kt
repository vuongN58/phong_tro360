package ptit.vuong.phongtro.presentation.room.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import ptit.vuong.phongtro.extension.onClick
import ptit.vuong.phongtro.databinding.ItemRoomBinding
import ptit.vuong.phongtro.domain.model.RelatedModel
import ptit.vuong.phongtro.extension.addSquareMeter
import ptit.vuong.phongtro.extension.convertToVietnamDong
import ptit.vuong.phongtro.extension.loadImageFromUrlWithLoading
import ptit.vuong.phongtro.extension.withThueTroUrl

/**
 * Make use of this class to create a custom view for related item
 * @param context: Context
 * @param attrs: AttributeSet?
 * @param defStyleAttr: Int
 */

class RelatedItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(
    context,
    attrs,
    defStyleAttr
) {
    private var item: RelatedModel? = null
    private val binding: ItemRoomBinding =
        ItemRoomBinding.inflate(
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
            this,
            true
        )

    init {
        orientation = VERTICAL
    }

    fun init(
        item: RelatedModel
    ) {
        this.item = item
        binding.run {
            ivRoom.loadImageFromUrlWithLoading(item.image.withThueTroUrl())
            tvAddress.text = item.address
            tvPrice.text = item.price.convertToVietnamDong()
            tvSize.text = item.size.toString().addSquareMeter()
        }
    }

    fun setClickListener(onClick: (RelatedModel) -> Unit) {
        binding.root.onClick {
            onClick.invoke(this.item ?: return@onClick)
        }
    }

}