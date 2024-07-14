package com.example.practice.flo.data.ui.main.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BannerVPAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private val fragmentlist : ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int = fragmentlist.size

//    override fun getItemCount(): Int {
//        return fragmentlist.size
//    }

    override fun createFragment(position: Int): Fragment =fragmentlist[position]//getItemCount의 값이 4라면 0,1,2,3까지

    fun addFragment(fragment: Fragment){
        fragmentlist.add(fragment)
        notifyItemInserted(fragmentlist.size-1)//list안에 새로운 값이 추가가 되었을때 뷰 페이저한테 알려줘야함
    // 그래서 새로운 값이 리스트에 추가되는 것을 맗함 list에 값이 들어가면 인덱스 0번째에 들어가서 사이즈가 1이될테니

    }

}