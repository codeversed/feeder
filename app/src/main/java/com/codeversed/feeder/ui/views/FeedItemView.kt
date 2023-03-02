package com.codeversed.feeder.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codeversed.feeder.models.FeedItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeedItemView(
    feedItem: FeedItem,
    evenRow: Boolean = true,
) {

    val openDialog = remember { mutableStateOf(false) }

    if (openDialog.value) {
        ItemAlertDialog(feedItem = feedItem) {
            openDialog.value = false
        }
    }

    ListItem(
        modifier = Modifier
            .clickable { openDialog.value = true }
            .wrapContentSize()
            .background(
                color = when {
                    evenRow -> MaterialTheme.colorScheme.surface
                    else -> MaterialTheme.colorScheme.surfaceVariant
                }
            ),
        text = {
            Text(
                text = feedItem.title,
                modifier = Modifier
                    .wrapContentSize(align = Alignment.TopStart)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
                color = when {
                    evenRow -> MaterialTheme.colorScheme.onSurface
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                },
            )
        },
        secondaryText = {
            Text(
                text = feedItem.body,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .wrapContentSize()
                    .fillMaxWidth(),
                style = MaterialTheme.typography.bodySmall,
                color = when {
                    evenRow -> MaterialTheme.colorScheme.onSurface
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                },
            )
        }
    )
}