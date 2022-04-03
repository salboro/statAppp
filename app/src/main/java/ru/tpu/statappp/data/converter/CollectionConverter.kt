package ru.tpu.statappp.data.converter

import ru.tpu.statappp.data.model.Collection
import ru.tpu.statappp.domain.entity.CryptoCurrency
import ru.tpu.statappp.domain.entity.Currency
import ru.tpu.statappp.domain.entity.Stock
import ru.tpu.statappp.domain.entity.Topic
import javax.inject.Inject

class CollectionConverter @Inject constructor() {

    fun convert(from: Collection): List<Topic> =
        from.run {
            cryptoCurrencies.map {
                CryptoCurrency(
                    it.key,
                    it.value
                )
            } + currencies.map {
                Currency(
                    it.value.name,
                    it.key,
                    it.value.value / it.value.nominal
                )
            } + stocks.map {
                Stock(it.key, it.value)
            }
        }

}