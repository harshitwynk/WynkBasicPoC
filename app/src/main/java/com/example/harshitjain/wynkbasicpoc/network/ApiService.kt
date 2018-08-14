package com.example.harshitjain.wynkbasicpoc.network

import android.arch.lifecycle.LiveData
import com.example.harshitjain.wynkbasicpoc.db.Item
import com.example.harshitjain.wynkbasicpoc.repo.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


const val CONTEN_URL = "https://content.wynk.in/music/v4/content?id=srch_bsb_1520319897059&type=playlist&count=50&offset=0&lang=en"

interface ApiService {

    @GET("music/v4/content?lang=en")
    fun getItem(@Query("id") id: String, @Query("type") type: String, @Query("count") count: Int, @Query("offset") offset: Int): LiveData<ApiResponse<Item>>
}