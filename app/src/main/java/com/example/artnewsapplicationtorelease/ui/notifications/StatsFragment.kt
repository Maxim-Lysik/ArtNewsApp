package com.example.artnewsapplicationtorelease.ui.notifications

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artnewsapplicationtorelease.ArtNewsActivity
import com.example.artnewsapplicationtorelease.LineGraphStyle
import com.example.artnewsapplicationtorelease.adapters.DaysAdapter
import com.example.artnewsapplicationtorelease.databinding.FragmentStatsBinding
import com.example.artnewsapplicationtorelease.models.DayData
import com.example.artnewsapplicationtorelease.ui.NewsViewModel
import com.github.mikephil.charting.data.*
import kotlinx.android.synthetic.main.fragment_stats.*
import java.util.*
import javax.inject.Inject


class StatsFragment : Fragment() {


    @Inject
    lateinit var chartStyle: LineGraphStyle

    private var _binding: FragmentStatsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var viewModel: NewsViewModel
    lateinit var viewModel_local: NotificationsViewModel
    lateinit var daysAdapter: DaysAdapter
    lateinit var newList: ArrayList<String>



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


        newList = arrayListOf("MN", "TU", "WE", "TH", "FR", "ST", "SN")

        setupDaysRecyclerView(newList)


        viewModel_local = ViewModelProviders.of(this).get(NotificationsViewModel::class.java)




        /// ТУТ ПОПЫТАЕМСЯ НАПИСАТЬ


       // val dayData1 = viewModel.getdayDataByID(1)?: DayData(1, "Sunday", 0)  // putdata here
        val dayData1 = viewModel.getdayDataByID(1)?: viewModel.putDate(DayData(1, "Sunday", 0))
        val dayData2 = viewModel.getdayDataByID(2)?: viewModel.putDate(DayData(2, "Monday", 0))
        val dayData3 = viewModel.getdayDataByID(3)?: viewModel.putDate(DayData(3, "Tuesday", 0))
        val dayData4 = viewModel.getdayDataByID(4)?: viewModel.putDate(DayData(4, "Wednesday", 0))
        val dayData5 = viewModel.getdayDataByID(5)?: viewModel.putDate(DayData(5, "Thursday", 0))
        val dayData6 = viewModel.getdayDataByID(6)?: viewModel.putDate(DayData(6, "Friday", 0))
        val dayData7 = viewModel.getdayDataByID(7)?: viewModel.putDate(DayData(7, "Saturday", 0))
        val dayData8 = viewModel.getdayDataByID(7)?: viewModel.putDate(DayData(8, "Test", 0))
        val dayData9 = viewModel.getdayDataByID(7)?: viewModel.putDate(DayData(0, "Test", 0))

      /*  if( viewModel.getdayDataByID(1) == null){
            viewModel.putDate(DayData(1, "Sunday", 0))

        }else if(viewModel.getdayDataByID(2) == null){viewModel.putDate(DayData(2, "Monday", 0))}
        else if(viewModel.getdayDataByID(3) == null){viewModel.putDate(DayData(3, "Tuesday", 0))}
*/



        //viewModel.putDate(dayData1)
       // viewModel.putDate(dayData2)
        //viewModel.putDate(dayData3)
        //viewModel.putDate(dayData4 )
        //viewModel.putDate(dayData5)
       // viewModel.putDate(dayData6)
       // viewModel.putDate(dayData7)



        /*

        ПРОСТО СОХРАНЮ КУСОК КОДА

        val dayData1 = viewModel.getdayDataByID(1)?: DayData(1, "Sunday", 0)  // putdata here
        val dayData1 = viewModel.getdayDataByID(1)?: viewModel.putDate(DayData(1, "Sunday", 0))
        val dayData2 = viewModel.getdayDataByID(2)?: DayData(2, "Monday", 0)
        val dayData3 = viewModel.getdayDataByID(3)?: DayData(3, "Tuesday", 0)
        //val dayData4: DayData = DayData(4, "Wednesday", 0)
        val dayData4 = viewModel.getdayDataByID(4)?: DayData(4, "Wednesday", 0)

        //val l = b?.length ?: -1

        val dayData5 = viewModel.getdayDataByID(5)?: DayData(5, "Thursday", 0)
        val dayData6 = viewModel.getdayDataByID(6)?: DayData(6, "Friday", 0)
        val dayData7 = viewModel.getdayDataByID(7)?: DayData(7, "Saturday", 0)

        */




        val sharedPreference =  this.activity!!.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        sharedPreference.getInt("Ass", 20)

        // SETTING GENERAL COUNTER TEXTVIEW

        val general_counter_number = sharedPreference.getInt("General", 0)
        general_counter.setText("Articles red in sum: ${general_counter_number.toString()}")


      /*  var sunday = sharedPreference.getInt("Sunday", 0)
        var monday = sharedPreference.getInt("Monday", 0)
        var tuesday = sharedPreference.getInt("Tuesday", 0)
        var wednesday = sharedPreference.getInt("Wednesday", 0)
        var thursday = sharedPreference.getInt("Thursday", 0)
        var friday = sharedPreference.getInt("Friday", 0)
        var saturday = sharedPreference.getInt("Saturday", 0)




        var listforchart = mutableListOf<Int>()

        listforchart.add(sunday)
        listforchart.add(monday)
        listforchart.add(tuesday)
        listforchart.add(wednesday)
        listforchart.add(thursday)
        listforchart.add(friday)
        listforchart.add(saturday)


        var count: Int = 0
        listforchart.forEach {
            viewModel_local.dayData.add(Entry(count.toFloat(), it.toFloat()))

            viewModel_local.dayData.set(count, Entry(count.toFloat(), it.toFloat()) )
                   // Log.d(TAG, "DAY FROM LIST: ${it}")

        }
*/





        viewModel.getAllDays().observe(viewLifecycleOwner){

            it.forEach {
               // viewModel_local.dayData.add(Entry(it.id.toFloat(), it.clicks_today!!.toFloat()))
                viewModel_local.dayData.set(it.id-1, Entry(it.id.toFloat(), it.clicks_today!!.toFloat()))

                Log.d(TAG, "DAY FROM LIST: ${it}")
            }








            var list1 = viewModel_local.dayData
            viewModel_local._lineDataSet.value = LineDataSet(list1, CHART_LABEL)

        }




        viewModel_local.lineDataSet.observe(viewLifecycleOwner) { lineDataSet ->

            binding.dayChart.data = LineData(lineDataSet)

            chartStyle.styleLineDataSet(lineDataSet)   // STYLING THE LINE HERE
            binding.dayChart.invalidate()
        }



        // STYLING HERE

        chartStyle = LineGraphStyle(requireActivity())

        chartStyle.styleChart(binding.dayChart)




        // SECOND GRAPH


        //viewModel_local.dayData.set(it.id-1, Entry(it.id.toFloat(), it.clicks_today!!.toFloat()))



        viewModel_local.morningDataSet.observe(viewLifecycleOwner) { barDataSet ->


            var clicks_morning = sharedPreference.getInt("Morning", 0)

            viewModel_local.morning.set(0, BarEntry(0f, clicks_morning.toFloat()))


            binding.dayChart2.data = BarData(barDataSet)

         //   chartStyle.styleLineDataSet(lineDataSet)   // STYLING THE LINE HERE
            binding.dayChart2.invalidate()
            binding.dayChart2.axisLeft.isEnabled = true
            binding.dayChart2.axisRight.isEnabled = false
            binding.dayChart2.xAxis.isEnabled = true

        }



        viewModel_local.afternoonDataSet.observe(viewLifecycleOwner){ barDataSet ->


            var clicks_afternoon = sharedPreference.getInt("Afternoon", 0)
            viewModel_local.afternoon.set(0, BarEntry(1f, clicks_afternoon.toFloat()))

            binding.dayChart2.data.addDataSet(barDataSet)
            binding.dayChart2.invalidate()
        }



        viewModel_local.eveningDataSet.observe(viewLifecycleOwner){ barDataSet ->



            var clicks_evening = sharedPreference.getInt("Evening", 0)
            viewModel_local.evening.set(0, BarEntry(2f, clicks_evening.toFloat()))

            binding.dayChart2.data.addDataSet(barDataSet)
            binding.dayChart2.invalidate()
        }


        viewModel_local.nightDataSet.observe(viewLifecycleOwner){ barDataSet ->

            var clicks_night = sharedPreference.getInt("Night", 0)
            viewModel_local.night.set(0, BarEntry(3f, clicks_night.toFloat()))

            binding.dayChart2.data.addDataSet(barDataSet)
            binding.dayChart2.invalidate()
        }





        // SECOND GRAPH


       //day_chart2.axisLeft.isEnabled = false
       // day_chart.axisRight.isEnabled = false


        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_WEEK].toInt()



        updateDateText(day)

        //  Log.d(TAG, "DAY OF WEEK ${dayOfTheWeek}")
        Log.d(TAG, "DAY VIA CALENDAR ${day}")



        when (day) {
            1 -> {Log.d(TAG, "SUKAAAA ${day}")

                var clicks_sunday = sharedPreference.getInt("Sunday", 0)
                var dayData1: DayData = DayData(1, "Sunday", clicks_sunday)
                viewModel.putDate(dayData1)

                viewModel_local.dayData.set(0,Entry(dayData1.id.toFloat(), dayData1.clicks_today!!.toFloat()) )


            }
            2 -> {Log.d(TAG, "SUKAAAA ${day}")

                Log.d(TAG, "SUKAAAA ${day}")
                var clicks_monday = sharedPreference.getInt("Monday", 0)
                var dayData2: DayData = DayData(2, "Monday", clicks_monday)
                viewModel.putDate(dayData2)

               // viewModel_local.dayData.set(1,Entry(dayData2.id.toFloat(), dayData2.clicks_today!!.toFloat()) )




            }
            3 -> {
                Log.d(TAG, "SUKAAAA ${day}")
                 var clicks_tuesday = sharedPreference.getInt("Tuesday", 0)
                 var dayData3: DayData = DayData(3, "Tuesday", clicks_tuesday)
                 viewModel.putDate(dayData3)

            //    viewModel_local.dayData.set(1,Entry(dayData3.id.toFloat(), dayData3.clicks_today!!.toFloat()) )


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


               // viewModel_local.dayData.set(4,Entry(dayData5.id.toFloat(), dayData5.clicks_today!!.toFloat()) )
                viewModel_local.dayData.set(4,Entry(5f, clicks_thursday.toFloat()) )


            }
            6 -> {Log.d(TAG, "SUKAAAA ${day}")


                var clicks_friday = sharedPreference.getInt("Friday", 0)
                var dayData6: DayData = DayData(6, "Friday", clicks_friday)
                viewModel.putDate(dayData6)

               // viewModel_local.dayData.set(5,Entry(dayData6.id.toFloat(), dayData6.clicks_today!!.toFloat()) )
                viewModel_local.dayData.set(5,Entry(6f, clicks_friday.toFloat()) )


            }
            7 -> {Log.d(TAG, "SUKAAAA ${day}")

                var clicks_saturday = sharedPreference.getInt("Saturday", 0)
                var dayData7: DayData = DayData(7, "Saturday", clicks_saturday)
                viewModel.putDate(dayData7)


            }
        }







        ////

    }



    private fun setupDaysRecyclerView(newList: ArrayList<String>) {
        daysAdapter = DaysAdapter(this.requireActivity(), newList )
        our_recycler.apply {
            adapter = daysAdapter
            val linearLayoutManager = LinearLayoutManager(activity)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
           // layoutManager = LinearLayoutManager(activity)
            layoutManager = linearLayoutManager
           // addOnScrollListener(this@NewsListFragment.scrollListener)
        }
    }


    private fun updateDateText(day: Int) {

        when (day) {
            1 -> {
                Log.d(TAG, "SUKAAAA ${day}")
                sunday.setTypeface(null, Typeface.BOLD)
                sunday.setTextColor(Color.RED);

            }
            2 -> {
                Log.d(TAG, "SUKAAAA ${day}")
                monday.setTypeface(null, Typeface.BOLD)
                monday.setTextColor(Color.RED);

            }
            3 -> {
                Log.d(TAG, "SUKAAAA ${day}")
                tuesday.setTypeface(null, Typeface.BOLD)
                tuesday.setTextColor(Color.RED);

            }
            4 -> {
                Log.d(TAG, "SUKAAAA ${day}")
                wednesday.setTypeface(null, Typeface.BOLD)
                wednesday.setTextColor(Color.RED);

            }
            5 -> {
                Log.d(TAG, "SUKAAAA ${day}")
                thursday.setTypeface(null, Typeface.BOLD)
                thursday.setTextColor(Color.RED);

            }
            6 -> {
                Log.d(TAG, "SUKAAAA ${day}")
                friday.setTypeface(null, Typeface.BOLD)
                friday.setTextColor(Color.RED);

            }
            7 -> {
                Log.d(TAG, "SUKAAAA ${day}")
                saturday.setTypeface(null, Typeface.BOLD)
                saturday.setTextColor(Color.RED);

            }

        }




    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}


