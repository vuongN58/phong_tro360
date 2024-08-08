package ptit.vuong.phongtro.presentation.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ptit.vuong.phongtro.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import ptit.vuong.phongtro.R
import ptit.vuong.phongtro.databinding.FragmentAccountBinding
import ptit.vuong.phongtro.extension.launchAndRepeatStarted
import ptit.vuong.phongtro.extension.onClick
import ptit.vuong.phongtro.extension.visible
import ptit.vuong.phongtro.extension.visibleIf

@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>() {

    private val viewModel by viewModels<AccountViewModel>()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAccountBinding
        get() = FragmentAccountBinding::inflate

    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
    }

    override fun initActions() {
        binding.run {
            btnSignOut.setOnClickListener {
                viewModel.signOut()
            }
            btnLogin.onClick {
                navigateTo(R.id.action_accountFragment_to_loginFragment)
            }
            btnFavorite.onClick {
                navigateTo(R.id.action_accountFragment_to_favoriteFragment)
            }
        }
    }

    override fun initObservers() {
        launchAndRepeatStarted({
            viewModel.accountUiState.collect(::renderUi)
        })
    }

    private fun renderUi(uiState: AccountUiState) {
        binding.run {
            tvAccountName.text = uiState.profile?.name
            ivAvatar.visibleIf { uiState.isLoggedIn }
            tvAccountName.visibleIf { uiState.isLoggedIn }
            btnSignOut.visibleIf { uiState.isLoggedIn }
            btnLogin.visibleIf { !uiState.isLoggedIn }
        }
    }

}