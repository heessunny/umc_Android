package com.example.practice.flo.data.ui.main.home
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.practice.flo.data.ui.DetailFragment
import com.example.practice.flo.data.ui.VideoFragment
import com.example.practice.flo.data.ui.song.SongFragment


class HomeVPAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int =3
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SongFragment()
            1 -> com.example.practice.flo.data.ui.DetailFragment()
            else -> com.example.practice.flo.data.ui.VideoFragment()
        }
    }


}