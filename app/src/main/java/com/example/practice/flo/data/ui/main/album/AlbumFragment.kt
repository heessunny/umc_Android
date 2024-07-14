package com.example.practice.flo.data.ui.main.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.practice.flo.R
import com.example.practice.flo.data.entities.Album
import com.example.practice.flo.data.entities.Like
import com.example.practice.flo.data.local.SongDatabase
import com.example.practice.flo.data.ui.main.home.HomeFragment
import com.example.practice.flo.data.ui.main.MainActivity
import com.example.practice.flo.databinding.FragmentAlbumBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson


class AlbumFragment: Fragment() {
    lateinit var binding: FragmentAlbumBinding
    private var gson: Gson = Gson()

    private val information = arrayListOf("수록곡","상세정보","영상")

    private  var isLiked : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ViewGroup? {
        binding = FragmentAlbumBinding.inflate(inflater,container,false)

        val albumJson = arguments?.getString("album")
        val album = gson.fromJson(albumJson, Album::class.java)
        isLiked = isLikedAlbum(album.id)
        setInit(album)
        setOnClickListener(album)

        binding.albumHeaderBtnBack.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(
                R.id.main_frm,
                HomeFragment()
            ).commitNowAllowingStateLoss()
        }

        val albumAdapter = AlbumVpAdapter(this)
        binding.albumContentVp.adapter = albumAdapter
        TabLayoutMediator(binding.albumContentTb, binding.albumContentVp){
            tab, position ->
            tab.text =information[position]
        }.attach()

        return binding.root
    }

    private fun setInit(album: Album){
        binding.albumTitle.text = album.title.toString()
        binding.albumSinger.text= album.singer.toString()
        binding.albumImg.setImageResource(album.coverImg!!)
        if(isLiked){
            binding.albumLikeIv.setImageResource(R.drawable.ic_my_like_on)
        }
        else{
            binding.albumLikeIv.setImageResource(R.drawable.ic_my_like_off)

        }
    }
    private fun getJwt():Int{
        val spf = activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return  spf!!.getInt("jwt",0)
    }
    private fun likeAlbum(userId : Int, albumId : Int){
        val songDB = SongDatabase.getInstance(requireContext())!!
        val like = Like(userId, albumId)

        songDB.albumDao().likeAlbum(like)
    }
    private fun isLikedAlbum(albumId: Int): Boolean{
        val songDB = SongDatabase.getInstance(requireContext())!!
        val userId = getJwt()
        val likeId : Int? = songDB.albumDao().isLikedAlbum(userId, albumId)

        return likeId != null
    }

    private fun disLikedAlbum(albumId: Int){
        val songDB = SongDatabase.getInstance(requireContext())!!
        val userId = getJwt()

    }

    private fun setOnClickListener(album: Album){
        val userId = getJwt()
        binding.albumLikeIv.setOnClickListener{
            if(isLiked){
                binding.albumLikeIv.setImageResource(R.drawable.ic_my_like_off)
                disLikedAlbum(album.id)

            }
            else{
                binding.albumLikeIv.setImageResource(R.drawable.ic_my_like_on)
                likeAlbum(userId,album.id)

            }
        }
    }
}