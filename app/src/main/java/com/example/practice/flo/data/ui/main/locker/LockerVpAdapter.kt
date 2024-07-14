package com.example.practice.flo.data.ui.main.locker

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.practice.flo.data.ui.MusicfileFragment

class LockerVpAdapter(fragment: Fragment):FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int =3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SavedSongFragment()
            1 -> com.example.practice.flo.data.ui.MusicfileFragment()
            else -> SavedAlbumFragment()
        }
    }

}