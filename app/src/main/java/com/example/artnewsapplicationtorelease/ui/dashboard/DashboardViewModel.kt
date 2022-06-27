package com.example.artnewsapplicationtorelease.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

 /*   companion object{

        private const val CHART_LABEL = "DAY_CHART"

    }


     private val dayData = mutableListOf<Entry>()
    private val _lineDataSet = MutableLiveData(LineDataSet(dayData, CHART_LABEL))
    val lineDataSet: LiveData<LineDataSet> = _lineDataSet

    init {
        dayData.add(Entry(0f, 5f))
        dayData.add(Entry(1f, 3f))
        dayData.add(Entry(2f, 5f))
        dayData.add(Entry(3f, 7f))
        dayData.add(Entry(4f, 8f))
        dayData.add(Entry(5f, 10f))
        dayData.add(Entry(6f, 2f))
        dayData.add(Entry(7f, 5f))
        dayData.add(Entry(8f, 4f))
        dayData.add(Entry(9f, 5f))

        _lineDataSet.value = LineDataSet(dayData, CHART_LABEL)
    }
*/

}