package com.example.resultsbrowser.ui.composables

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.resultsbrowser.model.DisplayedResults
import com.example.resultsbrowser.ui.theme.Orange20
import com.example.resultsbrowser.ui.theme.Purple200
import com.example.resultsbrowser.ui.theme.ResultsBrowserTheme
import com.example.resultsbrowser.ui.theme.Teal200

@Composable
fun DisplayedResultsRow(
    modifier: Modifier = Modifier,
    displayedResults: DisplayedResults
) {
    val textModifier = Modifier
        .padding(vertical = 10.dp)
        .width(intrinsicSize = IntrinsicSize.Max)
        .padding(horizontal = 10.dp)
    val cardModifier = Modifier.padding(vertical = 5.dp)

    Row(
        modifier = modifier
    ) {
        displayedResults.displayMessage?.let {

            if (displayedResults.f1Results != null) {
                OutlinedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = Orange20,
                    ),
                    modifier = cardModifier
                ) {
                    Text(
                        text = it,
                        modifier = textModifier
                    )
                }
            }

            if (displayedResults.tennisResults != null) {
                OutlinedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = Teal200,
                    ),
                    modifier = cardModifier
                ) {
                    Text(
                        text = it,
                        modifier = textModifier
                    )
                }
            }

            if (displayedResults.nbaResults != null) {
                OutlinedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = Purple200,
                    ),
                    modifier = cardModifier
                ) {
                    Text(
                        text = it,
                        modifier = textModifier
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReloadResultsLoadingPreview() {
    ResultsBrowserTheme {
        DisplayedResultsRow(
            modifier = Modifier,
            displayedResults = DisplayedResults(
                null,
                "I look cool"
            )
        )
    }
}