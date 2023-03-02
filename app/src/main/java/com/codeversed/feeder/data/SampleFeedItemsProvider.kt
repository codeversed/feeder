package com.codeversed.feeder.data

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.codeversed.feeder.models.FeedItem

class SampleFeedItemsProvider : PreviewParameterProvider<FeedItem> {
    override val values: Sequence<FeedItem> = sequenceOf(
        FeedItem(
            id = 1,
            userId = 1,
            title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
        ), FeedItem(
            id = 2,
            userId = 1,
            title = "qui est esse",
            body = "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
        ),
        FeedItem(
            id = 3,
            userId = 1,
            title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
        ), FeedItem(
            id = 4,
            userId = 1,
            title = "qui est esse",
            body = "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
        )
    )
}