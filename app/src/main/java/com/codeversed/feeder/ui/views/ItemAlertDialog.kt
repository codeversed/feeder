package com.codeversed.feeder.ui.views

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.codeversed.feeder.models.FeedItem

@Composable
fun ItemAlertDialog(
    feedItem: FeedItem,
    onClose: () -> Unit
) {
    AlertDialog(
        backgroundColor = MaterialTheme.colorScheme.surface,
        onDismissRequest = onClose,
        title = {
            Text(
                text = feedItem.title,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        text = {
            Text(
                text = feedItem.body,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        confirmButton = {
            Button(onClick = onClose) {
                Text("OK")
            }
        },
    )
}