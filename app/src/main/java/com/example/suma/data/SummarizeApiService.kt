package com.example.suma.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Header

interface SummarizeApiService {
    @GET("summarize")
    suspend fun getSummary(
        @Query("url") url: String,
        @Header("x-api-key") apiKey: String = "123456ABCDEF"
    ): Response<String>
}
