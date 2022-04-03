package ru.tpu.statappp.data.api

import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateConverterFactory @Inject constructor() : Converter.Factory() {
    override fun stringConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<Date, String>? =
        if (type != Date::class.java) {
            null
        } else {
            DateToStringConverter()
        }
}

private class DateToStringConverter : Converter<Date, String> {

    override fun convert(value: Date): String? =
        SimpleDateFormat("y-M-d", Locale.US).format(value)
}