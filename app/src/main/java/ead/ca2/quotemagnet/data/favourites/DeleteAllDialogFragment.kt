package ead.ca2.quotemagnet.data.favourites

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import ead.ca2.quotemagnet.R
import ead.ca2.quotemagnet.ui.favourites.FavouritesViewModel

@AndroidEntryPoint
class DeleteAllDialogFragment : DialogFragment(){

    private val viewModel: FavouritesViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.delete_all_favourite_quotations))
        builder.setMessage(getString(R.string.are_you_sure_you_want_to_delete_all_quotations))
        builder.setPositiveButton(getString(R.string.yes)) { _, _ -> viewModel.deleteAllQuotations()} // deleteall
        builder.setNegativeButton(getString(R.string.no)) { _, _ -> dismiss()}
        return builder.create()
    }


}