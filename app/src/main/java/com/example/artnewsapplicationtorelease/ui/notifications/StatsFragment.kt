package com.example.artnewsapplicationtorelease.ui.notifications

import android.R
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.artnewsapplicationtorelease.ArtNewsActivity
import com.example.artnewsapplicationtorelease.DataPoint
import com.example.artnewsapplicationtorelease.GraphView
import com.example.artnewsapplicationtorelease.databinding.FragmentStatsBinding
import com.example.artnewsapplicationtorelease.models.DayData
import com.example.artnewsapplicationtorelease.ui.NewsViewModel
import com.example.artnewsapplicationtorelease.ui.dashboard.DashboardViewModel
import com.example.artnewsapplicationtorelease.ui.newslist.HomeViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_stats.*
import java.util.*


class StatsFragment : Fragment() {




    private var _binding: FragmentStatsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var viewModel: NewsViewModel
    lateinit var viewModel_local: NotificationsViewModel



    companion object{

        private const val CHART_LABEL = "DAY_CHART"

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentStatsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it


        }

       /* val ggraphView: GraphView = binding.graphView
        ggraphView.setData(generateDataPoints())*/




        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as ArtNewsActivity).viewModel



        viewModel_local = ViewModelProviders.of(this).get(NotificationsViewModel::class.java)




        /// ТУТ ПОПЫТАЕМСЯ НАПИСАТЬ


       val dayData1: DayData = DayData(1, "Sunday", 0)
        val dayData2: DayData = DayData(2, "Monday", 0)
        val dayData3: DayData = DayData(3, "Tuesday", 0)
        //val dayData4: DayData = DayData(4, "Wednesday", 0)
        val dayData4 = viewModel.getdayDataByID(4)?: DayData(4, "Wednesday", 0)

        //val l = b?.length ?: -1

        val dayData5: DayData = DayData(5, "Thursday", 0)
        val dayData6: DayData = DayData(6, "Friday", 0)
        val dayData7: DayData = DayData(7, "Saturday", 0)
        viewModel.putDate(dayData1)
        viewModel.putDate(dayData2)
        viewModel.putDate(dayData3)
        //viewModel.putDate(dayData4 )
        viewModel.putDate(dayData5)
        viewModel.putDate(dayData6)
        viewModel.putDate(dayData7)



        viewModel.getAllDays().observe(viewLifecycleOwner){

            it.forEach {
                viewModel_local.dayData.add(Entry(it.id.toFloat(), it.clicks_today!!.toFloat()))
                Log.d(TAG, "DAY FROM LIST: ${it}")
            }

            var list1 = viewModel_local.dayData
            viewModel_local._lineDataSet.value = LineDataSet(list1, CHART_LABEL)

        }




        viewModel_local.lineDataSet.observe(viewLifecycleOwner) { lineDataSet ->

            binding.dayChart.data = LineData(lineDataSet)
            binding.dayChart.invalidate()
        }







        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_WEEK].toInt()

        //  Log.d(TAG, "DAY OF WEEK ${dayOfTheWeek}")
        Log.d(TAG, "DAY VIA CALENDAR ${day}")


        val sharedPreference =  this.activity!!.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        sharedPreference.getInt("Ass", 20)


        when (day) {
            1 -> {Log.d(TAG, "SUKAAAA ${day}")}
            2 -> {Log.d(TAG, "SUKAAAA ${day}")}
            3 -> {
                Log.d(TAG, "SUKAAAA ${day}")
                 var clicks_tuesday = sharedPreference.getInt("Tuesday", 0)
                 var dayData3: DayData = DayData(3, "Tuesday", clicks_tuesday)
                 viewModel.putDate(dayData3)


            }
            4 -> {Log.d(TAG, "SUKAAAA ${day}")
                var clicks_wednesday = sharedPreference.getInt("Wednesday", 0)
                var dayData4: DayData = DayData(4, "Wednesday", clicks_wednesday)
                viewModel.putDate(dayData4)

            }
            5 -> {Log.d(TAG, "SUKAAAA ${day}")

                var clicks_thursday = sharedPreference.getInt("Thursday", 0)
                var dayData5: DayData = DayData(5, "Thursday", clicks_thursday)
                viewModel.putDate(dayData5)


            }
            6 -> {Log.d(TAG, "SUKAAAA ${day}")}
            7 -> {Log.d(TAG, "SUKAAAA ${day}")}
        }







        ////

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}


