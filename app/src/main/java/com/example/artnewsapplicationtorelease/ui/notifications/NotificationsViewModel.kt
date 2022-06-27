package com.example.artnewsapplicationtorelease.ui.notifications

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import java.util.*

class NotificationsViewModel : ViewModel() {


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

    companion object{

        private const val CHART_LABEL = "DAY_CHART"

    }


    var days_in_month = Calendar.getInstance()
    var monthMaxDays = days_in_month.getActualMaximum(Calendar.DAY_OF_MONTH)






    var dayData = mutableListOf<Entry>()
    var _lineDataSet = MutableLiveData(LineDataSet(dayData, CHART_LABEL))
    var lineDataSet: LiveData<LineDataSet> = _lineDataSet

    init {
        dayData.add(Entry(0f, 5f))
        dayData.add(Entry(1f, 3f))
        //dayData.add(Entry(2f, 5f))
       // dayData.add(Entry(3f, 7f))
      //  dayData.add(Entry(4f, 8f))
      //  dayData.add(Entry(5f, 10f))
      //  dayData.add(Entry(6f, 2f))

       // dayData.add(Entry(monthMaxDays.toFloat(), 7f))

        _lineDataSet.value = LineDataSet(dayData, CHART_LABEL)

        Log.d(ContentValues.TAG, "NUMBER OF DAYS IS ${monthMaxDays}");

    }

}