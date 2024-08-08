package ptit.vuong.phongtro.presentation.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ptit.vuong.phongtro.presentation.base.BaseFragment
import ptit.vuong.phongtro.databinding.FragmentRegisterBinding
import ptit.vuong.phongtro.extension.launchAndRepeatStarted
import ptit.vuong.phongtro.extension.onClick
import ptit.vuong.phongtro.extension.toaster

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private val viewModel: RegisterViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    override fun initData(data: Bundle?) {
    }

    override fun initViews() {
    }

    override fun initActions() {
        binding.run {
            btnRegister.onClick {
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                val confirmPassword = edtRePassword.text.toString()
                val phone = edtPhone.text.toString()
                val fullName = edtFullName.text.toString()
                viewModel.register(email, password, confirmPassword, phone, fullName)
            }
        }
    }

    override fun initObservers() {
        launchAndRepeatStarted({
            viewModel.eventFlow.collect(::listenEvents)
        })
    }

    private fun listenEvents(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.RegisterSuccess -> {
                popBackStack()
            }

            is RegisterEvent.ShowLoading -> {
                showLoading()
            }

            is RegisterEvent.HideLoading -> {
                hideLoading()
            }

            is RegisterEvent.ShowError -> {
                requireContext().toaster(
                    event.errorText
                )
            }
        }
    }

}