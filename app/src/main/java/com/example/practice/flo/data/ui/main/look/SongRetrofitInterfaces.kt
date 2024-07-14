package com.example.practice.flo.data.ui.main.look

import com.example.practice.flo.data.ui.song.SongResponse
import retrofit2.Call
import retrofit2.http.GET

interface SongRetrofitInterfaces {
    @GET("https://run.mocky.io/v3/cdd93d6b-4567-4a80-93a4-98e53a9e8353")
    fun getSongs(): Call<SongResponse>
}