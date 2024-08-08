package ptit.vuong.phongtro.presentation.room.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import ptit.vuong.phongtro.R
import ptit.vuong.phongtro.extension.onClick
import ptit.vuong.phongtro.presentation.base.BaseBottomSheetDialogFragment
import ptit.vuong.phongtro.databinding.FragmentOptionBottomSheetBinding
import ptit.vuong.phongtro.extension.setTintColor


class OptionBottomSheet : BaseBottomSheetDialogFragment<FragmentOptionBottomSheetBinding>(
    FragmentOptionBottomSheetBinding::inflate
) {
    private var isFavorite: Boolean = false
    var onShareClick: () -> Unit = {}
    var onPhoneCallClick: () -> Unit = {}
    var onMessageClick: () -> Unit = {}
    var onPositionClick: () -> Unit = {}
    var onFavoriteClick: () -> Unit = {}

    override fun initView() {
        isFavorite = requireArguments().getBoolean(IS_FAVORITE)
        binding.run {

            ivFavorite.setTintColor(
                if (isFavorite) R.color.gray else R.color.main
            )
            tvFavorite.text =
                getString(if (isFavorite) R.string.remove_favorite else R.string.favorite)
            tvFavorite.setTextColor(
                resources.getColor(
                    if (isFavorite) R.color.black else R.color.colorPrimary,
                    null
                )
            )
            llFavorite.onClick {
                onFavoriteClick.invoke()
                dismiss()
            }
            llShare.onClick {
                onShareClick.invoke()
                dismiss()
            }
            llCall.onClick {
                onPhoneCallClick.invoke()
                dismiss()
            }
            llMessage.onClick {
                onMessageClick.invoke()
                dismiss()
            }
            llLead.onClick {
                onPositionClick.invoke()
                dismiss()
            }
        }
    }

    override fun isDraggable(): Boolean = false

    override fun isExpanded(): Boolean = false

    companion object {

        const val IS_FAVORITE = "IS_FAVORITE"

        fun showBottomSheet(
            fragment: Fragment,
            isFavorite: Boolean
        ): OptionBottomSheet {
            val bottomSheet = OptionBottomSheet()
            val args = fragment.arguments ?: Bundle()
            args.putBoolean(IS_FAVORITE, isFavorite)
            bottomSheet.arguments = args
            bottomSheet.show(fragment.childFragmentManager, bottomSheet.tag)
            return bottomSheet
        }
    }

}