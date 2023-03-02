package com.codeversed.feeder.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedItem(val id: Int, val userId: Int, val title: String, val body: String)


