package ead.ca2.quotemagnet.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import ead.ca2.quotemagnet.R
import ead.ca2.quotemagnet.R.menu.menu_about
import ead.ca2.quotemagnet.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MenuProvider {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addMenuProvider(this)

        // linking view to activity with binding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // nav controller
        val navController = binding.navHostFragment.getFragment<NavHostFragment>().navController
        binding.bottomNavigationView as NavigationBarView
        binding.bottomNavigationView.setupWithNavController(navController)

        setSupportActionBar(binding.toolbar)


        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.settingsFragment,
                R.id.favouritesFragment,
                R.id.newQuotationFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(menu_about, menu)

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.aboutMenuItem -> {
                findNavController(R.id.navHostFragment).navigate(R.id.aboutDialogFragment)
                true
            }
            else -> false
        }

    }


}