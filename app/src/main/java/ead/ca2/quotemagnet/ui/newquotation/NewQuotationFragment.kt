package ead.ca2.quotemagnet.ui.newquotation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ead.ca2.quotemagnet.R
import ead.ca2.quotemagnet.R.id.quotationMenuItem
import ead.ca2.quotemagnet.databinding.FragmentNewQuotationBinding
import utils.NoInternetException
import java.io.IOException
import androidx.core.view.MenuProvider as MenuProvider
@AndroidEntryPoint
class NewQuotationFragment : Fragment(R.layout.fragment_new_quotation), MenuProvider {

    private var _binding : FragmentNewQuotationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewQuotationViewModel by viewModels()



    // constructor


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewQuotationBinding.bind(view)
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        // listeners
        binding.swipeRefresh.setOnRefreshListener { viewModel.getNewQuotation() }
        binding.favButton.setOnClickListener{viewModel.addToFavourites()}

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.error.collect { error ->
                    error?.let {
                        val message = when (it) {
                            is IOException -> R.string.network_error_message
                            is NoInternetException -> R.string.no_internet_error_message
                            else -> R.string.unexplainable_error_message
                        }
                        Snackbar.make(binding.swipeRefresh, message, Snackbar.LENGTH_SHORT)
                            .show()
                        viewModel.resetError()
                    }

                }
            }
        }



        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userName.collect { userName ->

                    binding.welcomeText.text = getString(
                        R.string.welcomeText,
                        userName.ifEmpty { getString(R.string.anonymous) })

                }

            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.addFav.collect {
                        addFav ->
                    binding.favButton.isVisible = addFav
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.loadingState.collect {
                        loadingState ->
                    binding.swipeRefresh.isRefreshing = loadingState
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newQuotation.collect{newQuotation ->
                    if (newQuotation != null) {
                        binding.welcomeText.isVisible = false
                        binding.quotationAuthor.text = newQuotation.author.ifEmpty { getString(R.string.anonymous) }
                        binding.quotationText.text = newQuotation.text
                    }
                }
            }
        }



    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_new_quotation, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            quotationMenuItem -> {
                viewModel.getNewQuotation()

                true
            }
            else -> false
        }
    }

    // deconstructor
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}