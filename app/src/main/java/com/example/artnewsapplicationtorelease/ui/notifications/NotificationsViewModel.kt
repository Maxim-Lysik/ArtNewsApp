package com.example.artnewsapplicationtorelease.ui.notifications

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.artnewsapplicationtorelease.models.DayData
import com.example.artnewsapplicationtorelease.ui.NewsViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
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

    }


    var days_in_month = Calendar.getInstance()
    var monthMaxDays = days_in_month.getActualMaximum(Calendar.DAY_OF_MONTH)


    var dayData = mutableListOf<Entry>()
    var _lineDataSet = MutableLiveData(LineDataSet(dayData, CHART_LABEL))
    var lineDataSet: LiveData<LineDataSet> = _lineDataSet


    /* val dayData1: DayData = DayData(1, "Sunday", 0)
     val dayData2: DayData = DayData(2, "Monday", 0)
     val dayData3: DayData = DayData(3, "Tuesday", 0)
     val dayData4: DayData = DayData(4, "Wednesday", 0)
     val dayData5: DayData = DayData(5, "Thursday", 0)
     val dayData6: DayData = DayData(6, "Friday", 0)*/
    // val dayData7: DayData = DayData(7, "Sunday", 0)


    init {

        val dayData1: DayData = DayData(1, "Sunday", 4)
        val dayData2: DayData = DayData(2, "Monday", 10)
        val dayData3: DayData = DayData(3, "Tuesday", 9)
        val dayData4: DayData = DayData(4, "Wednesday", 8)
        val dayData5: DayData = DayData(5, "Thursday", 7)
        val dayData6: DayData = DayData(6, "Friday", 6)
        val dayData7: DayData = DayData(7, "Saturday", 5)

        /*   viewModel.putDate(dayData1)
           viewModel.putDate(dayData2)
           viewModel.putDate(dayData3)
           viewModel.putDate(dayData4)
           viewModel.putDate(dayData5)
           viewModel.putDate(dayData6)
           viewModel.putDate(dayData7)

   */

        /*   dayData.add(Entry(dayData1.id.toFloat(), dayData1.clicks_today?.toFloat() ?: 0f))
           dayData.add(Entry(dayData2.id.toFloat(), dayData2.clicks_today?.toFloat() ?: 0f))
           dayData.add(Entry(dayData3.id.toFloat(), dayData3.clicks_today?.toFloat() ?: 0f))
           dayData.add(Entry(dayData4.id.toFloat(), dayData4.clicks_today?.toFloat() ?: 0f))
           dayData.add(Entry(dayData5.id.toFloat(), dayData5.clicks_today?.toFloat() ?: 0f))
           dayData.add(Entry(dayData6.id.toFloat(), dayData6.clicks_today?.toFloat() ?: 0f))
           //dayData.add(Entry(dayData7.id.toFloat(), dayData7.clicks_today?.toFloat() ?: 0f))*/

        dayData.add(Entry(dayData1.id.toFloat(), dayData1.clicks_today!!.toFloat()))
        dayData.add(Entry(dayData2.id.toFloat(), dayData2.clicks_today!!.toFloat()))
        dayData.add(Entry(dayData3.id.toFloat(), dayData3.clicks_today!!.toFloat()))
        dayData.add(Entry(dayData4.id.toFloat(), dayData4.clicks_today!!.toFloat()))
        dayData.add(Entry(dayData5.id.toFloat(), dayData5.clicks_today!!.toFloat()))
        dayData.add(Entry(dayData6.id.toFloat(), dayData6.clicks_today!!.toFloat()))
        dayData.add(Entry(dayData7.id.toFloat(), dayData7.clicks_today!!.toFloat()))
       // dayData.add(Entry(8f, 0f))
        //  dayData.add(Entry(3f, 0f))
        //  dayData.add(Entry(7f, 0f))
        //  dayData.add(Entry(3f, 0f))

        //  dayData.add(Entry(4f, 0f))
        //  dayData.add(Entry(5f, 0f))
        //  dayData.add(Entry(6f, 0f))
        //dayData.add(Entry(7f, 0f))
        // dayData.add(Entry(7f, 0f))


        // dayData.add(Entry(monthMaxDays.toFloat(), 7f))

        _lineDataSet.value = LineDataSet(dayData, CHART_LABEL)

        Log.d(ContentValues.TAG, "NUMBER OF DAYS IS ${monthMaxDays}");

    }

}