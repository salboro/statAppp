package ru.tpu.statappp.presentation.selectdetails

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.tpu.statappp.domain.GetCryptoCurrencyNamesUseCase
import ru.tpu.statappp.domain.GetCurrencyNamesUseCase
import ru.tpu.statappp.domain.GetStockNamesUseCase
import ru.tpu.statappp.domain.entity.SelectDetail
import ru.tpu.statappp.ui.selectdetails.SelectDetailsFragment
import ru.tpu.statappp.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class SelectDetailsViewModel @Inject constructor(
    arguments: SavedStateHandle,
    private val getCurrencyNamesUseCase: GetCurrencyNamesUseCase,
    private val getStockNamesUseCase: GetStockNamesUseCase,
    private val getCryptoCurrencyNamesUseCase: GetCryptoCurrencyNamesUseCase,
) : ViewModel() {

    companion object {

        private const val CURRENCY_TOPIC = "Валюта"
        private const val STOCK_TOPIC = "Акции"
        private const val CRYPTO_CURRENCY_TOPIC = "Криптовалюта"
    }

    private val _state = MutableLiveData<SelectDetailsState>(SelectDetailsState.Initial)
    val state: LiveData<SelectDetailsState> = _state

    private val _navigateToDetailsEvent = SingleLiveEvent<Pair<String, String>>()
    val navigateToDetailsEvent: LiveData<Pair<String, String>> = _navigateToDetailsEvent

    private val topic: String = requireNotNull(arguments[SelectDetailsFragment.TOPIC_KEY])

    init {
        loadData()
    }

    private fun loadData() {
        _state.value = SelectDetailsState.Loading
        viewModelScope.launch {
            when (topic) {
                CURRENCY_TOPIC -> {
                    val currencyNames =
                        getCurrencyNamesUseCase().map { SelectDetail(it.key, it.value) }

                    _state.value = SelectDetailsState.Content(currencyNames)
                }

                CRYPTO_CURRENCY_TOPIC -> {
                    val cryptoCurrencyNames = getCryptoCurrencyNamesUseCase().map { SelectDetail(it, it) }

                    _state.value = SelectDetailsState.Content(cryptoCurrencyNames)
                }

                STOCK_TOPIC -> {
                    val stockNames = getStockNamesUseCase().map { SelectDetail(it, it) }

                    _state.value = SelectDetailsState.Content(stockNames)
                }
            }
        }

    }

    fun selectDetails(item: SelectDetail) {
        _navigateToDetailsEvent.value = item.id to topic
    }
}