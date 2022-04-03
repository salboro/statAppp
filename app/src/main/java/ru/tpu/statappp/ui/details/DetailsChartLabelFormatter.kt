package ru.tpu.statappp.ui.details

import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import java.text.SimpleDateFormat
import java.util.*

class DetailsChartLabelFormatter(private val lineDataSet: ILineDataSet) : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        var entryIndex = value.toInt()

        if (entryIndex >= lineDataSet.entryCount) {
            entryIndex = lineDataSet.entryCount - 1
        }

        val entryData = lineDataSet.getEntryForIndex(entryIndex).data
        return if (entryData is String) {
            val dateParser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
            val dateFormatter = SimpleDateFormat("dd MM yyyy", Locale.US)

            dateParser.parse(entryData)
                ?.let(dateFormatter::format)
                ?: super.getFormattedValue(value)
        } else {
            super.getFormattedValue(value)
        }
    }
}