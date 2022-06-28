package com.example.artnewsapplicationtorelease.ui.notifications

import android.R
import android.os.Bundle
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

       // viewModel_local.lineDataSet.observe(this)

        viewModel_local = ViewModelProviders.of(this).get(NotificationsViewModel::class.java)


        /// ТУТ ПОПЫТАЕМСЯ НАПИСАТЬ

        val dayData1: DayData = DayData(1, "Monday", 10)
        val dayData2: DayData = DayData(2, "Tuesday", 9)
        val dayData3: DayData = DayData(3, "Wednesday", 8)
        val dayData4: DayData = DayData(4, "Thursday", 7)
        val dayData5: DayData = DayData(5, "Friday", 6)
        val dayData6: DayData = DayData(6, "Saturday", 5)
        val dayData7: DayData = DayData(7, "Sunday", 4)
        viewModel.putDate(dayData1)
        viewModel.putDate(dayData2)
        viewModel.putDate(dayData3)
        viewModel.putDate(dayData4)
        viewModel.putDate(dayData5)
        viewModel.putDate(dayData6)
        viewModel.putDate(dayData7)



        viewModel.getAllDays().observe(viewLifecycleOwner){

            it.forEach {
                viewModel_local.dayData.add(Entry(it.id.toFloat(), it.clicks_today!!.toFloat()))
            }

            var list1 = viewModel_local.dayData
            viewModel_local._lineDataSet.value = LineDataSet(list1, CHART_LABEL)

        }





      /*  viewModel_local.dayData.add(Entry(17f, 5f))
        var list1 = viewModel_local.dayData                                         // ЭТО уже готовый варик
        viewModel_local._lineDataSet.value = LineDataSet(list1, CHART_LABEL)*/




        viewModel_local.lineDataSet.observe(viewLifecycleOwner) { lineDataSet ->

           // chartStyle.styleLineDataSet(lineDataSet)

            //viewModel_local.dayData.add(Entry(17f, 5f))
            binding.dayChart.data = LineData(lineDataSet)
            binding.dayChart.invalidate()
        }




      //  val graph_view: GraphView = binding.graphView


        //graph_view.setData(generateDataPoints())
      //  graph_view.setData(emptyList())
        //viewModel.generateDataPoints()


      //  if(today == monday){
            viewModel_local.dayData.add(Entry(17f, 5f))
        //viewModel_local.lineDataSet

      //  }




    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

  /*private fun generateDataPoints():List<DataPoint>{

        val random = Random()
        return (0..20).map {
            DataPoint(it, random.nextInt(50)+1)
        }


    }*/



}


