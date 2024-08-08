package ptit.vuong.phongtro.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.activity.addCallback
import androidx.annotation.CallSuper
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import ptit.vuong.phongtro.extension.gone
import ptit.vuong.phongtro.extension.visible
import ptit.vuong.phongtro.R
import ptit.vuong.phongtro.databinding.FragmentBaseBinding

abstract class BaseFragment<VB : ViewBinding> :
    Fragment(),
    IBaseFragment {
    override val baseActivity: BaseActivity<*>?
        get() = activity as? BaseActivity<*>

    private var baseBinding: FragmentBaseBinding? = null

    lateinit var binding: VB
        private set

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData(arguments)
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseBinding = FragmentBaseBinding.inflate(inflater, container, false)
        binding = bindingInflater.invoke(inflater, baseBinding?.contentContainer, true)
        initViews()
        initActions()
        return baseBinding?.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        (requireActivity() as OnBackPressedDispatcherOwner)
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner) {
                onBackPressed()
            }
    }

    override fun onDestroyView() {
        baseBinding = null
        super.onDestroyView()
    }

    override fun showLoading() {
        baseBinding?.loadingView?.root?.visible()
    }

    override fun hideLoading() {
        baseBinding?.loadingView?.root?.gone()
    }

    override fun onBackPressed() {
        if (!findNavController(binding.root).popBackStack()) {
        }
    }

    fun navigateTo(
        id: Int,
        bundle: Bundle? = null,
        popUpToId: Int? = null,
        isInclusive: Boolean? = null,
        animUse: Boolean = true,
        navBuilder: NavOptions.Builder? = null,
    ) {
        val options = navBuilder
            ?: if (animUse) {
                NavOptions.Builder()
                    .setEnterAnim(R.anim.slide_in_right)
                    .setExitAnim(R.anim.slide_out_left)
                    .setPopEnterAnim(R.anim.slide_in_left)
                    .setPopExitAnim(R.anim.slide_out_right)
            } else {
                NavOptions.Builder()
            }
        if (popUpToId != null && isInclusive != null) {
            options.setPopUpTo(popUpToId, isInclusive)
        }
        findNavController().navigate(id, bundle, options.build())
    }


    fun popBackStack(id: Int? = null, isInclusive: Boolean? = null) {
        runCatching {
            if (id == null || isInclusive == null) {
                findNavController().popBackStack()
                return
            }
            findNavController().popBackStack(id, isInclusive)
        }
    }

    fun setStatusBarColor(color: Int, isLightStatusBar: Boolean = true) {
        val window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = requireContext().getColor(color)
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
            isLightStatusBar
    }
}
