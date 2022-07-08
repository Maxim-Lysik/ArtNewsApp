package com.example.artnewsapplicationtorelease.ui.notifications

import android.content.ContentValues
import android.graphics.Color
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.artnewsapplicationtorelease.models.DayData
import com.example.artnewsapplicationtorelease.ui.NewsViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.transition.MaterialSharedAxis
import java.util.*

class NotificationsViewModel : ViewModel() {

    lateinit var viewModel: NewsViewModel

    // private val newsRepository: NewsRepository
    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

// FUNCTIONS FOR DAYDATA


    /* fun putDate(dayData: DayData) = viewModelScope.launch {
         newsRepository.upsert(dayData)
     }

     fun getAllDays() = newsRepository.getAllDays()

     fun deleteDay(dayData: DayData) = viewModelScope.launch {
         newsRepository.deleteDay(dayData)
     }
 */

    //

    companion object {

        private const val CHART_LABEL = "DAY_CHART"
        private const val CHART_LABEL2 = "DAY_CHART3"
        private const val CHART_LABEL3 = "DAY_CHART444"

    }


    var days_in_month = Calendar.getInstance()
    var monthMaxDays = days_in_month.getActualMaximum(Calendar.DAY_OF_MONTH)


    var dayData = mutableListOf<Entry>()
    var _lineDataSet = MutableLiveData(LineDataSet(dayData, CHART_LABEL))
    var lineDataSet: LiveData<LineDataSet> = _lineDataSet



    // FOR THE SECOND GRAPH

    var _dayData2 = mutableListOf<BarEntry>()
    var _lineDataSet2 = MutableLiveData(BarDataSet(_dayData2, CHART_LABEL2))
    var barDataSet: LiveData<BarDataSet> = _lineDataSet2

// SECOND ENTRY

     var _second_b_entry = mutableListOf<BarEntry>()
    var _lineDataSet3 = MutableLiveData(BarDataSet(_second_b_entry, CHART_LABEL3))
    var lineDataSet3: LiveData<BarDataSet> = _lineDataSet3

    // FOR THE SECOND GRAPH


    //BarDataSet copied = new BarDataSet(entries, getLabel());

    //  FOR THE SECOND GRAPH
   // List<IBarDataSet>

    /* val dayData1: DayData = DayData(1, "Sunday", 0)
     val dayData2: DayData = DayData(2, "Monday", 0)
     val dayData3: DayData = DayData(3, "Tuesday", 0)
     val dayData4: DayData = DayData(4, "Wednesday", 0)
     val dayData5: DayData = DayData(5, "Thursday", 0)
     val dayData6: DayData = DayData(6, "Friday", 0)*/
    // val dayData7: DayData = DayData(7, "Sunday", 0)


    init {

        val dayData1: DayData = DayData(1, "Sunday", 0)
        val dayData2: DayData = DayData(2, "Monday", 0)
        val dayData3: DayData = DayData(3, "Tuesday", 0)
        val dayData4: DayData = DayData(4, "Wednesday", 0)
        val dayData5: DayData = DayData(5, "Thursday", 0)
        val dayData6: DayData = DayData(6, "Friday", 0)
        val dayData7: DayData = DayData(7, "Saturday", 0)


        dayData.add(Entry(dayData1.id.toFloat(), dayData1.clicks_today!!.toFloat()))
        dayData.add(Entry(dayData2.id.toFloat(), dayData2.clicks_today!!.toFloat()))
        dayData.add(Entry(dayData3.id.toFloat(), dayData3.clicks_today!!.toFloat()))
        dayData.add(Entry(dayData4.id.toFloat(), dayData4.clicks_today!!.toFloat()))
        dayData.add(Entry(dayData5.id.toFloat(), dayData5.clicks_today!!.toFloat()))
        dayData.add(Entry(dayData6.id.toFloat(), dayData6.clicks_today!!.toFloat()))
        dayData.add(Entry(dayData7.id.toFloat(), dayData7.clicks_today!!.toFloat()))

        _lineDataSet.value = LineDataSet(dayData, CHART_LABEL)



        Log.d(ContentValues.TAG, "NUMBER OF DAYS IS ${monthMaxDays}");



        // FOR THE SECOND GRAPH
            _dayData2.add(BarEntry(0f,6f, "SSS"))
        _dayData2.add(BarEntry(1f,6f))
        _dayData2.add(BarEntry(2f,9f))


        // Second entry

        _second_b_entry.add(BarEntry(1f,13f))
        _lineDataSet3.value = BarDataSet(_second_b_entry, CHART_LABEL3)
        _lineDataSet3.value!!.setColor(Color.RED)



         _lineDataSet2.value = BarDataSet(_dayData2, CHART_LABEL2)
        _lineDataSet2.value!!.setColor(-0x1000000)




        // FOR THE SECOND GRAPH

    }

}