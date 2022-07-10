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
        private const val CHART_MORNING = "MORNING"
        private const val CHART_AFTERNOON = "AFTERNOON"
        private const val CHART_EVENING = "EVENING"
        private const val CHART_NIGHT = "NIGHT"

    }


    var days_in_month = Calendar.getInstance()
    var monthMaxDays = days_in_month.getActualMaximum(Calendar.DAY_OF_MONTH)


    var dayData = mutableListOf<Entry>()
    var _lineDataSet = MutableLiveData(LineDataSet(dayData, CHART_LABEL))
    var lineDataSet: LiveData<LineDataSet> = _lineDataSet


    //    S_E_C_O_N_D    P_A_R_T               //


    // MORNING

    var morning = mutableListOf<BarEntry>()
    var _morningDataSet = MutableLiveData(BarDataSet(morning, CHART_MORNING))
    var morningDataSet: LiveData<BarDataSet> = _morningDataSet

    // AFTERNOON

    var afternoon = mutableListOf<BarEntry>()
    var _afternoonDataSet = MutableLiveData(BarDataSet(afternoon, CHART_AFTERNOON))
    var afternoonDataSet: LiveData<BarDataSet> = _afternoonDataSet

    // EVENING

    var evening = mutableListOf<BarEntry>()
    var _eveningDataSet = MutableLiveData(BarDataSet(evening, CHART_EVENING))
    var eveningDataSet: LiveData<BarDataSet> = _eveningDataSet

    // NIGHT

    var night = mutableListOf<BarEntry>()
    var _nightDataSet = MutableLiveData(BarDataSet(night, CHART_NIGHT))
    var nightDataSet: LiveData<BarDataSet> = _nightDataSet




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


        // MORNING
        morning.add(BarEntry(3f, 1f,))
        _morningDataSet.value = BarDataSet(morning, CHART_MORNING)
        _morningDataSet.value!!.setColor(-0x1000000)



        // AFTERNOON

        afternoon.add(BarEntry(2f, 1f))
        _afternoonDataSet.value = BarDataSet(afternoon, CHART_AFTERNOON)
        _afternoonDataSet.value!!.setColor(Color.RED)

        // EVENING

        evening.add(BarEntry(1f, 1f))
        _eveningDataSet.value = BarDataSet(evening, CHART_EVENING)
        _eveningDataSet.value!!.setColor(Color.GREEN)

        // NIGHT

        night.add(BarEntry(0f, 1f))
        _nightDataSet.value = BarDataSet(night, CHART_NIGHT)
        _nightDataSet.value!!.setColor(Color.BLUE)







        // FOR THE SECOND GRAPH

    }

}