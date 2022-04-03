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
            val items = when (topic) {
                CURRENCY_TOPIC -> getCurrencyNamesUseCase().map { SelectDetail(it.key, it.value) }
                CRYPTO_CURRENCY_TOPIC -> getCryptoCurrencyNamesUseCase().map { SelectDetail(it, it) }
                STOCK_TOPIC -> getStockNamesUseCase().map { SelectDetail(it, it) }
                else -> throw IllegalStateException("Illegal topic")
            }

            _state.value = SelectDetailsState.Content(items)
        }

    }

    fun selectDetails(item: SelectDetail) {
        _navigateToDetailsEvent.value = topic to item.id
    }
}