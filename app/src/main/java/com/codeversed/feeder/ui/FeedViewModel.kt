package com.codeversed.feeder.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeversed.feeder.Constants
import com.codeversed.feeder.api.FeedService
import com.codeversed.feeder.models.FeedItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/** FeedViewModel is setup for demo and best practice would be to inject the needed [FeedService] */
class FeedViewModel : ViewModel() {

    /** Not injected for example purposes */
    private val service: FeedService

    /** Ensures [feedItems] are only modified by ViewModel */
    private val _feedItems = MutableStateFlow(mutableListOf<FeedItem>())
    val feedItems = _feedItems.asStateFlow()

    /** Ensures [loadingState] are only modified by ViewModel */
    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    init {
        service = createService()
        fetchFeed()
    }

    /** Returns a new FoodService instance.
     *  Note: This would normally be injected and setup in the app/feature models using Hilt */
    private fun createService(): FeedService {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.EXAMPLE_ENDPOINT)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(FeedService::class.java)
    }

    /** Fetches sample data from [Constants.EXAMPLE_ENDPOINT] + posts to demonstrate a list view
     *  in Jetpack Compose. */
    fun fetchFeed() = viewModelScope.launch {
        _loadingState.value = true

        // More elaborate error handling can be done with a NetworkResponse wrapper to better
        // handle `ServerError`, `NetworkError`, or `UnknownError` types.
        val response = withContext(Dispatchers.IO) { service.getItems() }

        // Currently only handling isSuccessful (range [200..300]) or Fail
        when {
            response.isSuccessful -> {
                _feedItems.value = response.body()?.toMutableList() ?: mutableListOf()
            }
            // Basic example does not display errors to user
            else -> Log.e("Feed error", response.message() ?: "An error occurred")
        }

        _loadingState.value = false
    }
}