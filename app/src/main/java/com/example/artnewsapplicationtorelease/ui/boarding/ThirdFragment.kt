package com.example.artnewsapplicationtorelease.ui.boarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.artnewsapplicationtorelease.ArtNewsActivity
import com.example.artnewsapplicationtorelease.R
import com.example.artnewsapplicationtorelease.databinding.FragmentFirstBinding
import com.example.artnewsapplicationtorelease.databinding.FragmentSecondBinding
import com.example.artnewsapplicationtorelease.databinding.FragmentThirdBinding


/*class ThirdFragment : Fragment() {

    //private var _binding: FragmentFirstBinding? = null
    private var _binding: FragmentThirdBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    companion object {
    }
}*/


class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         binding.thirdNextButton.setOnClickListener {

             val intent = Intent(activity, ArtNewsActivity::class.java)
             startActivity(intent)

         }


      /*  walking_button.setOnClickListener {

            val intent2 = Intent(activity, CitiesActivity::class.java)
            startActivity(intent2)


        }*/



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}