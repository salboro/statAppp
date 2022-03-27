package ru.tpu.statappp.domain.entity

data class Feed(
    val stocks: List<Stock>,
    val currencies: List<Currency>,
    val cryptoCurrencies: List<CryptoCurrency>
)
