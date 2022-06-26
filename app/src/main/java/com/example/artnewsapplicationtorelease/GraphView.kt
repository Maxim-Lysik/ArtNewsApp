package com.example.artnewsapplicationtorelease

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class GraphView(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {

    private val dataSet = mutableSetOf<DataPoint>()

    private var xMin = 0
    private var xMax = 0
    private var yMin = 0
    private var yMax = 0




    private val dataPointPaint = Paint().apply{
        color = Color.BLUE
    }

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)

        dataSet.forEach { dataPoint ->
            val realX = dataPoint.xVal.toFloat() / xMax * width
            val realY = dataPoint.yVal.toFloat() / yMax * height

            canvas.drawCircle(realX, realY, 10f, dataPointPaint)
        }


    }

    fun setData(newDataSet: List<DataPoint>){
        xMin = newDataSet.minByOrNull { it.xVal }?.xVal ?: 0
        xMax = newDataSet.maxByOrNull { it.xVal }?.xVal ?: 0
        yMin = newDataSet.minByOrNull { it.yVal }?.yVal ?: 0
        yMax = newDataSet.maxByOrNull { it.yVal }?.yVal ?: 0


        dataSet.clear()
        dataSet.addAll(newDataSet)
    }
}

data class DataPoint(val xVal: Int, val yVal: Int)