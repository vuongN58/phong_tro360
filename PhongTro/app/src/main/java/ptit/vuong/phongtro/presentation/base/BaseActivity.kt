package ptit.vuong.phongtro.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

  lateinit var binding: VB
    private set
  abstract val bindingInflater: (LayoutInflater) -> VB

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = bindingInflater.invoke(layoutInflater)

    setContentView(binding.root)
    onViewCreated()
    // Init immediately after create activity
  }

  open fun onViewCreated() {}
}
