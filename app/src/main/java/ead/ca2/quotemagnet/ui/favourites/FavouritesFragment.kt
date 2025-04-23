package ead.ca2.quotemagnet.ui.favourites

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ead.ca2.quotemagnet.R
import ead.ca2.quotemagnet.databinding.FragmentFavouritesBinding
import ead.ca2.quotemagnet.data.favourites.QuotationListAdapter

@AndroidEntryPoint
class FavouritesFragment : Fragment(R.layout.fragment_favourites), MenuProvider {

    private var _binding : FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavouritesViewModel by activityViewModels()






    // constructor
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouritesBinding.bind(view)
        binding.favList.layoutManager = LinearLayoutManager(requireContext())


        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        itemTouchHelper.attachToRecyclerView(binding.favList)


        val adapter = QuotationListAdapter { author ->
            if (author == "") {
                Snackbar.make(binding.root,
                    getString(R.string.anonymous_author_no_wiki_page), Snackbar.LENGTH_SHORT).show()
            } else {
                try {
                    val wikiUri = Uri.parse("https://en.wikipedia.org/wiki/Special:Search?search=$author")
                    val intent = Intent(Intent.ACTION_VIEW, wikiUri)
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Snackbar.make(binding.root,
                        getString(R.string.no_activity_found), Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        binding.favList.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favouritesList.collect { quotations ->

                adapter.submitList(quotations)
            }

        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isDeleteAllMenuVisible.collect {
                requireActivity().invalidateMenu()}

        }

    }
    // deconstructor
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPrepareMenu(menu: Menu) {

        val deleteMenuItem = menu.findItem(R.id.favouritesDialogFragment)
        deleteMenuItem.isVisible = viewModel.isDeleteAllMenuVisible.value
    }



    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_favourites, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.favouritesDialogFragment -> {
                findNavController().navigate(R.id.favouritesDialogFragment)
                true
            }
            else -> false
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun isLongPressDragEnabled(): Boolean {
            return false
        }

        override fun isItemViewSwipeEnabled(): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            viewModel.deleteQuotationAtPosition(viewHolder.adapterPosition)
        }
    })
}