package com.example.practice.flo.data.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practice.flo.databinding.FragmentMusicfileBinding


class MusicfileFragment: Fragment() {

    lateinit var binding: FragmentMusicfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusicfileBinding.inflate(inflater,container,false)

        return binding.root
    }
}