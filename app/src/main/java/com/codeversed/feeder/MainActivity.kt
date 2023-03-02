package com.codeversed.feeder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.codeversed.feeder.data.SampleFeedItemsProvider
import com.codeversed.feeder.extensions.collectAsStateWithLifecycle
import com.codeversed.feeder.ui.FeedViewModel
import com.codeversed.feeder.ui.theme.ExampleTheme
import com.codeversed.feeder.ui.views.FeedListView

/** MainActivity is simple example to show theming with [MaterialTheme], basic networking via
 * [ViewModel], and a [LazyColumn] demonstrating best practices for Jetpack Compose vertical
 * scrolling list with [androidx.compose.foundation.lazy.LazyListScope.item]
 *
 * * StateFlow extension [FlowExtension.collectAsStateWithLifecycle] is not added until 2.6.0-alpha04
 * **See Also:** [v2.6.0-alpha04](https://developer.android.com/jetpack/androidx/releases/lifecycle#2.6.0-alpha04)
 * **and:** [reference](https://medium.com/androiddevelopers/consuming-flows-safely-in-jetpack-compose-cde014d0d5a3)*/
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel = FeedViewModel()

            val feedItems by viewModel.feedItems.collectAsStateWithLifecycle()
            val loadingState = viewModel.loadingState.collectAsStateWithLifecycle()

            // Very basic theme setup to demonstrate Light/Dark modes for surface and text colors
            ExampleTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    FeedListView(
                        loadingState = loadingState,
                        feedItems = feedItems,
                        onRefresh = { viewModel.fetchFeed() }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExampleTheme {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        ) {
            FeedListView(
                loadingState = remember { mutableStateOf(false) },
                feedItems = SampleFeedItemsProvider().values.toMutableList(),
                onRefresh = { }
            )
        }
    }
}