package ead.ca2.quotemagnet.ui.newquotation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ead.ca2.quotemagnet.domain.model.Quotation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ead.ca2.quotemagnet.data.favourites.FavouritesRepository
import ead.ca2.quotemagnet.data.newquotation.NewQuotationRepository
import ead.ca2.quotemagnet.data.settings.SettingsRepository
import javax.inject.Inject

@HiltViewModel
class NewQuotationViewModel @Inject constructor(
    private val newQuotationRepository: NewQuotationRepository,
    settingsRepository: SettingsRepository,
    private val favouritesRepository: FavouritesRepository
) : ViewModel() {




    val userName=
        settingsRepository.getUserName().stateIn(
            scope = viewModelScope,
            initialValue = "",
            started = SharingStarted.WhileSubscribed()
        )


    private val _error = MutableStateFlow<Throwable?>(null)
    val error = _error.asStateFlow()

    private val _newQuotation = MutableStateFlow<Quotation?>(null)
    val newQuotation = _newQuotation.asStateFlow()


    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    val addFav = newQuotation.flatMapLatest { currentQuotation ->
        if (currentQuotation == null) flowOf(false)
        else favouritesRepository.getQuotationById(currentQuotation.id)
            .map { quotationInDatabase ->
                quotationInDatabase == null
            }
    }.stateIn(
        scope = viewModelScope,
        initialValue = false,
        started = SharingStarted.WhileSubscribed()
    )


    fun getNewQuotation() {
        _loadingState.value = true
        viewModelScope.launch {
            newQuotationRepository.getNewQuotation().fold(
                onSuccess = { quotation ->
                    _newQuotation.update {
                        Quotation(id=quotation.id, text=quotation.text, author = quotation.author )
                    }

                },
                onFailure = {throwable ->
                    _error.value = throwable
                }
            )
        }

        _loadingState.value = false

    }


    fun addToFavourites() {
        viewModelScope.launch {
            _newQuotation.value?.let { favouritesRepository.addQuotation(it) }

        }


    }


    fun resetError() {
        _error.value = null
    }


}