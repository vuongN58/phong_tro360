package ptit.vuong.phongtro.presentation.main

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ptit.vuong.phongtro.extension.gone
import ptit.vuong.phongtro.extension.visible
import ptit.vuong.phongtro.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import ptit.vuong.phongtro.R
import ptit.vuong.phongtro.databinding.ActivityMainBinding

/**
 * Main activity of the app.
 * This activity is the entry point of the app and hosts the navigation graph.
 * It also sets up the bottom navigation view with the navigation controller.
 * The bottom navigation view is shown only on the home, search, articles and account screens.
 * The bottom navigation view is hidden on all other screens.
 */

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel by viewModels<MainViewModel>()

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun onViewCreated() {
        super.onViewCreated()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

//        if (viewModel.isLogin)
//            navController.navigate(R.id.homeFragment)
//        else
//            navController.navigate(R.id.loginFragment)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.searchFragment, R.id.articlesFragment, R.id.accountFragment -> {
                    binding.bottomNavigationView.visible()
                }

                else -> {
                    binding.bottomNavigationView.gone()
                }
            }
        }
    }
}