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
            axisMinimum = -3.9F  // Need this

            granularity = 1f
        }

        xAxis.apply {
            axisMinimum = 1F
            //axisMaximum = 24f        dfsdfs

            isGranularityEnabled = true // Need this
            granularity = 1f  // Need this
            isEnabled = false





            setDrawGridLines(true)   // mainhere

            setDrawAxisLine(false)  // Need this

             position = XAxis.XAxisPosition.BOTTOM



        }


        setTouchEnabled(true)  // Also think we need this
        isDragEnabled = true
        setScaleEnabled(true)
        setPinchZoom(true)


        // Descriptions

        description = null
        legend.isEnabled = false

    }


    fun styleLineDataSet(lineDataSet: LineDataSet) = lineDataSet.apply {
        color = ContextCompat.getColor(context, R.color.black)
        valueTextColor = ContextCompat.getColor(context, R.color.black)
        // setDrawValues(false) // Deleting circles with numbers
        valueTextSize = 12f  // setting value size right
        lineWidth = 3f
        isHighlightEnabled = true
        // setDrawCircles(false) // Deleting circles\
        mode = CUBIC_BEZIER

        setDrawFilled(true)
        fillDrawable = ContextCompat.getDrawable(context, R.drawable.bg_gradient_line)



    }

}


/*

class LineGraphStyle @Inject constructor(private val context: Context) {

    fun styleChart(lineChart: LineChart) = lineChart.apply {

        axisRight.isEnabled = false
        axisLeft.apply {
            isEnabled = false
            axisMinimum = -0.3F  // Need this
        }

        xAxis.apply {
            axisMinimum = 1F
            //axisMaximum = 24f

            isGranularityEnabled = true // Need this
            granularity = 7f  // Need this


            setDrawGridLines(false)

            setDrawAxisLine(false)  // Need this

            position = XAxis.XAxisPosition.BOTTOM
        }


        setTouchEnabled(false)  // Also think we need this
        isDragEnabled = true
        setScaleEnabled(false)
        setPinchZoom(false)

        // Descriptions

        description = null
        legend.isEnabled = false

    }


    fun styleLineDataSet(lineDataSet: LineDataSet) = lineDataSet.apply {
        color = ContextCompat.getColor(context, R.color.black)
        valueTextColor = ContextCompat.getColor(context, R.color.black)
        // setDrawValues(false) // Deleting circles with numbers
        valueTextSize = 12f  // setting value size right
        lineWidth = 3f
        isHighlightEnabled = true
        // setDrawCircles(false) // Deleting circles\
        mode = CUBIC_BEZIER

        setDrawFilled(true)
        fillDrawable = ContextCompat.getDrawable(context, R.drawable.bg_gradient_line)



    }

}

*/