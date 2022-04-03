package ru.tpu.statappp.presentation.details

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.tpu.statappp.domain.GetCryptoDetailedUseCase
import ru.tpu.statappp.domain.GetCurrencyDetailedUseCase
import ru.tpu.statappp.domain.GetStockDetailedUseCase
import ru.tpu.statappp.ui.details.DetailsFragment
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    arguments: SavedStateHandle,
    private val getCurrencyDetailedUseCase: GetCurrencyDetailedUseCase,
    private val getCryptoDetailedUseCase: GetCryptoDetailedUseCase,
    private val getStockDetailedUseCase: GetStockDetailedUseCase,
) : ViewModel() {

    private companion object {

        const val CURRENCY_TOPIC = "Валюта"
        const val STOCK_TOPIC = "Акции"
        const val CRYPTO_CURRENCY_TOPIC = "Криптовалюта"
    }

    private val _state = MutableLiveData<DetailsState>(DetailsState.Initial)
    val state: LiveData<DetailsState> = _state

    private val topic: String = requireNotNull(arguments[DetailsFragment.TOPIC_KEY])
    private val name: String = requireNotNull(arguments[DetailsFragment.NAME_KEY])

    init {
        loadData()
    }

    private fun loadData() {
        _state.value = DetailsState.Loading
        viewModelScope.launch {
            when (topic) {
                CURRENCY_TOPIC -> {}

                CRYPTO_CURRENCY_TOPIC -> {}

                STOCK_TOPIC -> {}
            }
        }
    }
}