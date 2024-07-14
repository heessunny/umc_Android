package com.example.practice.flo.data.ui.main.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeBGVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int =3
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeViewpager1Fragment()
            1 -> HomeViewpager2Fragment()
            else -> HomeViewpager3Fragment()
        }
    }
}

