package ptit.vuong.phongtro.presentation.search.dialog

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import ptit.vuong.phongtro.extension.onClick
import ptit.vuong.phongtro.databinding.FragmentFilterBottomSheetBinding
import ptit.vuong.phongtro.extension.addSquareMeter
import ptit.vuong.phongtro.extension.toVietnameseCurrency
import ptit.vuong.phongtro.presentation.base.BaseBottomSheetDialogFragment

data class Filter(
    val minPrice: String,
    val maxPrice: String,
    val minSize: String,
    val maxSize: String,
)

class FilterBottomSheet :
    BaseBottomSheetDialogFragment<FragmentFilterBottomSheetBinding>(FragmentFilterBottomSheetBinding::inflate) {
    var onApplyClick: (Filter) -> Unit = {}

    @SuppressLint("SetTextI18n")
    override fun initView() {
        binding.run {
            priceRangeTv.text = "$${priceRangeSlider.values[0].toInt()} triệu- $${priceRangeSlider.values[1].toInt()} triệu"
            sizeTv.text = "${areaRangeSlider.values[0].toInt().toString().addSquareMeter()}- ${areaRangeSlider.values[1].toInt().toString().addSquareMeter()}"
            tvCancel.onClick {
                dismiss()
            }
            tvApply.onClick {
                val minPrice = priceRangeSlider.values[0].toDouble().toString().toVietnameseCurrency()
                val maxPrice = priceRangeSlider.values[1].toDouble().toString().toVietnameseCurrency()
                val minSize = areaRangeSlider.values[0].toDouble().toString()
                val maxSize = areaRangeSlider.values[1].toDouble().toString()
                onApplyClick.invoke(Filter(minPrice, maxPrice, minSize, maxSize))
                dismiss()
            }

        }
    }

    override fun initActions() {
        binding.run {
            priceRangeSlider.addOnChangeListener { rangeSlider, _, _ ->
                val priceText =
                    "$${rangeSlider.values[0].toInt()} triệu- $${rangeSlider.values[1].toInt()} triệu"
                priceRangeTv.text = priceText
            }
            areaRangeSlider.addOnChangeListener { rangeSlider, _, _ ->
                val areaText =
                    "${
                        rangeSlider.values[0].toInt().toString().addSquareMeter()
                    }- ${rangeSlider.values[1].toInt().toString().addSquareMeter()}"
                sizeTv.text = areaText
            }
        }
    }

    override fun isDraggable(): Boolean = false

    override fun isExpanded(): Boolean = false

    companion object{
        fun showBottomSheet(fragment: Fragment): FilterBottomSheet{
            val bottomSheet = FilterBottomSheet()
            bottomSheet.show(fragment.childFragmentManager, bottomSheet.tag)
            return bottomSheet
        }
    }

}