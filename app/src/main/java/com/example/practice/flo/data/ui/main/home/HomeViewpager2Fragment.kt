package com.example.practice.flo.data.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practice.flo.databinding.FragmentHomeViewpager2Binding



class HomeViewpager2Fragment: Fragment() {

    lateinit var binding: FragmentHomeViewpager2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeViewpager2Binding.inflate(inflater,container,false)

        return binding.root
    }
}