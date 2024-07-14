package com.example.practice.flo.data.ui.main.look

import com.example.practice.flo.data.ui.song.FloChartResult

interface LookView {
    fun onGetSongLoading()
    fun onGetSongSuccess(code: Int, result: FloChartResult)
    fun onGetSongFailure(code: Int, message: String)
}