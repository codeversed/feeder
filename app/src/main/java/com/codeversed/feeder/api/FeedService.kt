package com.codeversed.feeder.api

import com.codeversed.feeder.models.FeedItem
import retrofit2.Response
import retrofit2.http.GET

interface FeedService {

    @GET("posts")
    suspend fun getItems(): Response<List<FeedItem>>

}