package com.codeversed.feeder.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codeversed.feeder.Constants.EXAMPLE_FILTER
import com.codeversed.feeder.data.SampleFeedItemsProvider
import com.codeversed.feeder.models.FeedItem
import com.codeversed.feeder.ui.theme.ExampleTheme

/** A view composable [Box] demonstrating [LazyColumn]
 * @see [androidx.compose.foundation.lazy.LazyListScope.item]
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeedListView(
    modifier: Modifier = Modifier,
    loadingState: State<Boolean>,
    onRefresh: () -> Unit,
    feedItems: MutableList<FeedItem>,
) {

    val refreshing by loadingState
    val state = rememberPullRefreshState(refreshing, onRefresh)

    Box(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .pullRefresh(state)
    ) {

        LazyColumn(
            Modifier.wrapContentSize(align = Alignment.TopCenter)
        ) {
            if (!refreshing) {

                // Goal was to display feed items containing the text "optio"
                feedItems.filter { it.toString().contains(EXAMPLE_FILTER) }
                    .forEachIndexed { index, item ->
                        item {

                            // Used to display row items with alternating background colors
                            val evenRow = index % 2 == 0

                            FeedItemView(item, evenRow)
                        }
                    }
            }
        }

        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
    }
}

@Preview(showBackground = true, group = "FeedList")
@Composable
fun FeedListLightPreview() {
    ExampleTheme(darkTheme = false) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FeedListView(
                loadingState = remember { mutableStateOf(false) },
                feedItems = SampleFeedItemsProvider().values.toMutableList(),
                onRefresh = { }
            )
        }
    }
}

@Preview(showBackground = true, group = "FeedList")
@Composable
fun FeedListDarkPreview() {
    ExampleTheme(darkTheme = true) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FeedListView(
                loadingState = remember { mutableStateOf(false) },
                feedItems = SampleFeedItemsProvider().values.toMutableList(),
                onRefresh = { }
            )
        }
    }
}