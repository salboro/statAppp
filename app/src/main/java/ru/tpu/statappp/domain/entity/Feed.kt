package ru.tpu.statappp.domain.entity

data class Feed(
    val favorites: List<Topic>,
    val stocks: List<Stock>,
    val currencies: List<Currency>,
    val cryptoCurrencies: List<CryptoCurrency>
)
