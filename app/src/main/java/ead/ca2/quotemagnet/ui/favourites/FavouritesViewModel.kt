package ead.ca2.quotemagnet.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ead.ca2.quotemagnet.data.favourites.FavouritesRepository
import javax.inject.Inject


@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val favouritesRepository: FavouritesRepository
) : ViewModel() {


    val favouritesList = favouritesRepository.getAllQuotations().stateIn(
        scope = viewModelScope,
        initialValue = listOf(),
        started = SharingStarted.WhileSubscribed()
    )


    val isDeleteAllMenuVisible: StateFlow<Boolean> = favouritesList.map { list ->
        list.isNotEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = true
    )


    fun deleteAllQuotations() {
        viewModelScope.launch {
            favouritesRepository.deleteAllQuotations()
        }
    }

    fun deleteQuotationAtPosition(position: Int) {
        viewModelScope.launch {
            favouritesRepository.deleteQuotation(favouritesList.value[position])

        }

    }

}