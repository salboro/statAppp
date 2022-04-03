package ru.tpu.statappp.presentation.details

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.tpu.statappp.domain.*
import ru.tpu.statappp.domain.entity.DateResolution
import ru.tpu.statappp.domain.entity.Favorite
import ru.tpu.statappp.ui.details.DetailsFragment
import ru.tpu.statappp.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    arguments: SavedStateHandle,
    private val getCurrencyDetailedUseCase: GetCurrencyDetailedUseCase,
    private val getCryptoDetailedUseCase: GetCryptoDetailedUseCase,
    private val getStockDetailedUseCase: GetStockDetailedUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
) : ViewModel() {

    private companion object {

        const val CURRENCY_TOPIC = "Валюта"
        const val STOCK_TOPIC = "Акции"
        const val CRYPTO_CURRENCY_TOPIC = "Криптовалюта"
        const val CURRENCY_TYPE = "currency"
        const val STOCK_TYPE = "stock"
        const val CRYPTO_CURRENCY_TYPE = "cryptocurrency"
    }

    private val _state = MutableLiveData<DetailsState>(DetailsState.Initial)
    val state: LiveData<DetailsState> = _state

    val resolution = SingleLiveEvent<DateResolution>()

    private val topic: String = requireNotNull(arguments[DetailsFragment.TOPIC_KEY])
    private val name: String = requireNotNull(arguments[DetailsFragment.NAME_KEY])

    private var dataJob: Job? = null

    init {
        resolution.observeForever(::handleResolution)
    }

    private fun handleResolution(resolution: DateResolution) {
        loadData(resolution)
    }

    private fun loadData(resolution: DateResolution) {
        _state.value = DetailsState.Loading
        dataJob?.cancel()
        dataJob = viewModelScope.launch {
            val details = when (topic) {
                CURRENCY_TOPIC -> getCurrencyDetailedUseCase(name, resolution)
                CRYPTO_CURRENCY_TOPIC -> getCryptoDetailedUseCase(name, resolution)
                STOCK_TOPIC -> getStockDetailedUseCase(name, resolution)
                else -> throw IllegalStateException("Not supported topic: $topic")
            }

            _state.value = DetailsState.Content(
                data = details,
                favorite = isFavorite(convertTopicToType(topic), name)
            )
        }
    }

    private fun isFavorite(type: String, name: String): Boolean =
        isFavoriteUseCase(Favorite(type, name))

    fun changeFavoriteStatus() {
        val currentContent = getCurrencyContent()
        if (currentContent.favorite) {
            deleteFavoriteUseCase(Favorite(convertTopicToType(topic), name))
            _state.value = currentContent.copy(favorite = false)
        } else {
            addFavoriteUseCase(Favorite(convertTopicToType(topic), name))
            _state.value = currentContent.copy(favorite = true)
        }
    }

    private fun getCurrencyContent(): DetailsState.Content =
        if (_state.value is DetailsState.Content) {
            _state.value as DetailsState.Content
        } else {
            throw IllegalStateException("State is not content")
        }

    private fun convertTopicToType(from: String): String =
        when (from) {
            CURRENCY_TOPIC -> CURRENCY_TYPE
            STOCK_TOPIC -> STOCK_TYPE
            CRYPTO_CURRENCY_TOPIC -> CRYPTO_CURRENCY_TYPE
            else -> throw IllegalArgumentException("Illegal topic")
        }
}