package com.example.resultsbrowser.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resultsbrowser.ui.theme.ResultsBrowserTheme
import com.example.resultsbrowser.viewmodels.SportsResultsViewModel

@Composable
fun SportsResultsScreen(
    modifier: Modifier = Modifier,
    forcedIsReload: Boolean = false,  //for preview
    forcedIsLoading: Boolean = false, //for preview
    sportsResultsViewModel: SportsResultsViewModel? = null,
) {

    val isLoading = sportsResultsViewModel?.getIsLoading()?.observeAsState()
    val displayedResults = sportsResultsViewModel?._displaySportsResultsLiveData?.observeAsState()
    var isReload by remember { mutableStateOf(false) }

//    val displayData = sportsResultsViewModel?.sportsResultsLiveData?.observeAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.LightGray)
    ) {

        if (forcedIsReload || isReload) {
            OutlinedButton(
                onClick = {
                    //TODO fetch results
                    sportsResultsViewModel?.requestSportsResults()

                },
                colors = ButtonDefaults.outlinedButtonColors(),
                border = BorderStroke(1.dp, Color.Black),
                contentPadding = PaddingValues((1).dp),
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Text(
                    text = "Reload Results",
                    fontSize = 14.sp,
                    color = Color.Blue,
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
            }
        } else {
            OutlinedButton(
                onClick = {
                    //TODO fetch results
                    sportsResultsViewModel?.requestSportsResults()
                    isReload = true
                },
                colors = ButtonDefaults.outlinedButtonColors(),
                border = BorderStroke(1.dp, Color.Black),
                contentPadding = PaddingValues((1).dp),
                modifier = Modifier.padding(top = 10.dp),
            ) {
                Text(
                    text = "Fetch Results",
                    fontSize = 14.sp,
                    color = Color.Blue,
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
            }
        }

        if (forcedIsLoading || isLoading?.value == true) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),

                ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }

        if (isLoading?.value != true) {
            LazyColumn {
                displayedResults?.value?.size?.let {
                    items(it) { item ->
                        displayedResults.value!!.get(item).displayMessage?.let { it1 ->
                            Text(
                                text = it1,
                                modifier = Modifier,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun FetchResultsNotLoadingPreview() {
    ResultsBrowserTheme {
        SportsResultsScreen(
            forcedIsReload = false,
            forcedIsLoading = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FetchResultsLoadingPreview() {
    ResultsBrowserTheme {
        SportsResultsScreen(
            forcedIsReload = false,
            forcedIsLoading = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReloadResultsNotLoadingPreview() {
    ResultsBrowserTheme {
        SportsResultsScreen(
            forcedIsReload = true,
            forcedIsLoading = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReloadResultsLoadingPreview() {
    ResultsBrowserTheme {
        SportsResultsScreen(
            forcedIsReload = true,
            forcedIsLoading = true
        )
    }
}

