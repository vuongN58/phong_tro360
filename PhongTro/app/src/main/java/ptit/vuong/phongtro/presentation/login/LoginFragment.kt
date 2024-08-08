package ptit.vuong.phongtro.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.util.PatternsCompat
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ptit.vuong.phongtro.R
import ptit.vuong.phongtro.presentation.base.BaseFragment
import ptit.vuong.phongtro.databinding.FragmentLoginBinding
import ptit.vuong.phongtro.extension.launchAndRepeatStarted
import ptit.vuong.phongtro.extension.onClick
import ptit.vuong.phongtro.extension.toaster
import ptit.vuong.phongtro.extension.visibleIf

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
    }

    override fun initActions() {
        binding.run {
            btLogin.onClick {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                if (email.isEmpty() || password.isEmpty()) {
                    requireContext().toaster("Please enter email and password")
                    return@onClick
                }
                if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
                    requireContext().toaster("Please enter a valid email")
                    return@onClick
                }
                if (password.length < 6) {
                    requireContext().toaster("Password should be at least 6 characters")
                    return@onClick
                }
                viewModel.login(email, password)
            }
            ivClose.onClick {
                popBackStack()
            }
            tvRegister.onClick {
                navigateTo(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

    override fun initObservers() {
        launchAndRepeatStarted({
            viewModel.eventFlow.collect(::renderEvent)
        })
    }


    private fun renderEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.ShowLoading -> showLoading()
            is LoginEvent.HideLoading -> hideLoading()
            is LoginEvent.NavigateToHome -> navigateToHome()
            is LoginEvent.ShowError -> {
                requireContext().toaster("Invalid email or password")
            }
        }
    }

    private fun navigateToHome() {
        navigateTo(R.id.action_loginFragment_to_homeFragment)
    }
}