package com.example.practice.flo.data.ui.main.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.practice.flo.data.ui.main.MainActivity
import com.example.practice.flo.R
import com.example.practice.flo.data.entities.Album
import com.example.practice.flo.data.local.SongDatabase
import com.example.practice.flo.data.ui.main.album.AlbumFragment
import com.example.practice.flo.data.ui.main.album.AlbumRVAdapter
import com.example.practice.flo.databinding.FragmentHomeBinding
import com.google.gson.Gson
import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate

class HomeFragment : Fragment() {
    private val timer = Timer()
    private val handler = Handler(Looper.getMainLooper())
    lateinit var binding: FragmentHomeBinding
    private var albumDatas = ArrayList<Album>()
    private lateinit var songDB: SongDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater,container,false)

//        binding.homeAlbumImg1.setOnClickListener{
//            (context as MainActivity).supportFragmentManager.beginTransaction()
//            .replace(R.id.main_frm,AlbumFragment())
//            .commitNowAllowingStateLoss()
//        }


        songDB = SongDatabase.getInstance(requireContext())!!
        albumDatas.addAll(songDB.albumDao().getAlbums())
        val albumRVAdapter = AlbumRVAdapter(albumDatas)
        binding.homeTodayMusicAlbumRv.adapter =albumRVAdapter
        binding.homeTodayMusicAlbumRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        albumRVAdapter.setPlayClickListener(object : AlbumRVAdapter.MyPlayClickListener{
            override fun onPlayAlbum(album: Album) {
               sendSongData(album)
            }
        })
        albumRVAdapter.setMyItemClickListener(object: AlbumRVAdapter.MyItemClickListener {
            override fun onItemClick(album: Album) {
                changeAlbumFragment(album)
            }

        })

        val bgAdapter = HomeBGVPAdapter(this)
        binding.homePannelBackgroundVp.adapter = bgAdapter
        binding.homePannelIndicator.setViewPager(binding.homePannelBackgroundVp)

        startAutoSlide(bgAdapter)
        val bannerAdapter= BannerVPAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        binding.homeBannerVp.adapter =bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        return binding.root
    }

    private fun changeAlbumFragment(album: Album) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, AlbumFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val albumJson = gson.toJson(album)
                    putString("album", albumJson)
                }
            })
            .commitNowAllowingStateLoss()
    }

    private fun sendSongData(album: Album) {
        val mainActivity = activity as MainActivity
        val songList = songDB.songDao().getSongsInAlbum(album.id)
        songList?.firstOrNull()?.let {
            mainActivity.updateMiniPlayer(it)
        }
    }


    private fun startAutoSlide(adpater : HomeBGVPAdapter) {
        // 일정 간격으로 슬라이드 변경 (3초마다)
        timer.scheduleAtFixedRate(3000, 3000) {
            handler.post {
                val nextItem = binding.homePannelBackgroundVp.currentItem + 1
                if (nextItem < adpater.itemCount) {
                    binding.homePannelBackgroundVp.currentItem = nextItem
                } else {
                    binding.homePannelBackgroundVp.currentItem = 0 // 마지막 페이지에서 첫 페이지로 순환
                }
            }
        }
    }
}