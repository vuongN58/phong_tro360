package ptit.vuong.phongtro.presentation.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ptit.vuong.phongtro.data.local.sharepref.SharePreferenceProvider
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharePreferenceProvider: SharePreferenceProvider,
) : ViewModel() {

    var isLogin: Boolean
        get() = sharePreferenceProvider.get(SharePreferenceProvider.IS_LOGIN, false) ?: false
        set(value) = sharePreferenceProvider.save<Boolean>(SharePreferenceProvider.IS_LOGIN, value)

}