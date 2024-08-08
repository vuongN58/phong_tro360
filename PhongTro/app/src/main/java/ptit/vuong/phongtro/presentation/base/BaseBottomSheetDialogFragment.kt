package ptit.vuong.phongtro.presentation.base

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<VB : ViewBinding>(
  private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB,
) : BottomSheetDialogFragment() {

  private var _binding: VB? = null
  val binding get() = _binding!!

  abstract fun isDraggable(): Boolean

  abstract fun isExpanded(): Boolean

  @CallSuper
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initData(arguments)
  }

  @CallSuper
  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
    dialog.setOnShowListener { setupBottomSheet(it) }
    return dialog
  }

  @CallSuper
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    inflate(
      inflater,
      container,
      false,
    ).also { _binding = it }.root

  @CallSuper
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView()
    initObservers()
    initActions()
  }

  private fun setupBottomSheet(dialogInterface: DialogInterface) {
    val bottomSheetDialog = dialogInterface as BottomSheetDialog
    val bottomSheet = bottomSheetDialog.findViewById<View>(
      com.google.android.material.R.id.design_bottom_sheet,
    ) ?: return

    val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet)
    behavior.isDraggable = isDraggable()

    if (isExpanded()) {
      behavior.state = BottomSheetBehavior.STATE_EXPANDED
      behavior.peekHeight = 0
    }

    bottomSheet.setBackgroundColor(Color.TRANSPARENT)
  }

  protected open fun initData(data: Bundle?) {}

  protected open fun initActions() {}

  protected open fun initView() {}

  protected open fun initObservers() {}

  @CallSuper
  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }
}
