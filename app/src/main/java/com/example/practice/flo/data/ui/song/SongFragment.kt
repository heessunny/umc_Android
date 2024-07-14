package com.example.practice.flo.data.ui.song

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practice.flo.databinding.FragmentSongBinding

class SongFragment:Fragment() {

    lateinit var binding: FragmentSongBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater,container,false)

        binding.albumPreferenceMixToggleOff.setOnClickListener{
            setToggleStatus(false)
        }
        binding.albumPreferenceMixToggleOn.setOnClickListener{
            setToggleStatus(true)
        }

        return binding.root
    }


    fun setToggleStatus(isPlaying : Boolean){

        if(isPlaying){
            binding.albumPreferenceMixToggleOff.visibility= View.VISIBLE
            binding.albumPreferenceMixToggleOn.visibility=View.INVISIBLE
        }
        else{
            binding.albumPreferenceMixToggleOff.visibility= View.INVISIBLE
            binding.albumPreferenceMixToggleOn.visibility=View. VISIBLE

        }
    }
}