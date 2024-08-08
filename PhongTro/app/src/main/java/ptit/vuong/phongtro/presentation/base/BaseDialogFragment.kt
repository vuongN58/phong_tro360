package ptit.vuong.phongtro.presentation.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragment<VB : ViewBinding> : DialogFragment() {
  protected val binding: VB by lazy { getViewBinding() }

  abstract fun getViewBinding(): VB

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initData(arguments)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
    binding.root

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView()
    initObservers()
    initActions()
  }

  protected open fun initData(data: Bundle?) {}

  protected open fun initActions() {}

  protected open fun initView() {
    setWindowDialog()
  }

  protected open fun initObservers() {}

  private fun setWindowDialog() {
    dialog?.window?.run {
      setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT,
      )
      setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
  }
}
