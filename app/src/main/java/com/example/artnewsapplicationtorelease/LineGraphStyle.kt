package com.example.artnewsapplicationtorelease

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.core.graphics.scaleMatrix
import androidx.core.view.isVisible
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineDataSet.Mode.*
import javax.inject.Inject

class LineGraphStyle @Inject constructor(private val context: Context) {


    fun styleChart(lineChart: LineChart) = lineChart.apply {


        axisRight.apply {
            isEnabled = true
            granularity = 1f
        }


        axisLeft.apply {
            isEnabled = false
            axisMinimum = -3.9F
            granularity = 1f
        }

        xAxis.apply {
            axisMinimum = 1F
            isGranularityEnabled = true
            granularity = 1f
            isEnabled = false
            setDrawGridLines(true)
            setDrawAxisLine(false)
            position = XAxis.XAxisPosition.BOTTOM
        }


        setTouchEnabled(true)
        isDragEnabled = true
        setScaleEnabled(true)
        setPinchZoom(true)
        description = null
        legend.isEnabled = false

    }


    fun styleLineDataSet(lineDataSet: LineDataSet) = lineDataSet.apply {
        color = ContextCompat.getColor(context, R.color.black)
        valueTextColor = ContextCompat.getColor(context, R.color.black)
        valueTextSize = 10f
        lineWidth = 1f
        isHighlightEnabled = true
        mode = CUBIC_BEZIER
        setDrawFilled(true)
        fillDrawable = ContextCompat.getDrawable(context, R.drawable.bg_gradient_line)
    }

}
