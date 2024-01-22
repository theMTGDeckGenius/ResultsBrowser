package com.example.resultsbrowser.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resultsbrowser.ui.composables.DisplayedResultsRow
import com.example.resultsbrowser.ui.theme.Black
import com.example.resultsbrowser.ui.theme.Dark20
import com.example.resultsbrowser.ui.theme.ResultsBrowserTheme
import com.example.resultsbrowser.ui.theme.Yellow40
import com.example.resultsbrowser.viewmodels.SportsResultsViewModel

@Composable
fun SportsResultsScreen(
    modifier: Modifier = Modifier,
    forcedIsReload: Boolean = false,  //for preview
    forcedIsLoading: Boolean = false, //for preview
    sportsResultsViewModel: SportsResultsViewModel? = null,
) {
    val isLoading = sportsResultsViewModel?.getIsLoading()?.observeAsState()
    val displayedResults = sportsResultsViewModel?.displaySportsResultsLiveData?.observeAsState()
    val allResults = sportsResultsViewModel?.allSportsResultsLiveData?.observeAsState()
    var isReload by rememberSaveable { mutableStateOf(false) }
    var showAll by rememberSaveable { mutableStateOf(false) }
    var showBigListButton by rememberSaveable { mutableStateOf(true) }


    val buttonModifier = Modifier
        .width(150.dp)
        .height(60.dp)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        if (forcedIsReload || isReload) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            ) {
                Button(
                    onClick = {
                        sportsResultsViewModel?.requestSportsResults()
                        showAll = false
                        showBigListButton = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Yellow40),
                    border = BorderStroke(3.dp, Black),
                    contentPadding = PaddingValues((1).dp),
                    modifier = buttonModifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Reset",
                        fontSize = 14.sp,
                        color = Black,
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    )
                }
                if (showBigListButton) {
                    Spacer(modifier = Modifier.size(10.dp))
                    Button(
                        onClick = {
                            sportsResultsViewModel?.fillUpList()
                            showAll = false
                            showBigListButton = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Dark20),
                        border = BorderStroke(3.dp, Black),
                        contentPadding = PaddingValues((1).dp),
                        modifier = buttonModifier
                            .weight(1f),
                    ) {
                        Text(
                            text = "Big List",
                            fontSize = 14.sp,
                            color = Black,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                    }
                }
                if (!showAll) {
                    Spacer(modifier = Modifier.size(10.dp))
                    Button(
                        onClick = {
                            showAll = !showAll
                            showBigListButton = true
                        },
                        border = BorderStroke(3.dp, Black),
                        contentPadding = PaddingValues((1).dp),
                        modifier = buttonModifier
                            .weight(1f),
                    ) {
                        Text(
                            text = "Show All",
                            fontSize = 14.sp,
                            color = Black,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                    }
                }
            }
        } else {
            Button(
                onClick = {
                    sportsResultsViewModel?.requestSportsResults()
                    isReload = true
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
                border = BorderStroke(3.dp, Black),
                contentPadding = PaddingValues((1).dp),
                modifier = buttonModifier,
            ) {
                Text(
                    text = "Fetch Results",
                    fontSize = 14.sp,
                    color = Black,
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
            }
        }

        if ((forcedIsLoading || isLoading?.value == true) && !showAll) {
            Spacer(modifier = Modifier.size(20.dp))
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

        if (isLoading?.value != true && !showAll) {
            LazyColumn(
                modifier = Modifier.padding(
                    top = 20.dp,
                    end = 30.dp,
                    start = 30.dp
                )
            ) {
                displayedResults?.value?.size?.let {
                    items(it) { item ->
                        DisplayedResultsRow(displayedResults = displayedResults.value!![item])
                    }
                }
            }
        }
        if (isLoading?.value != true && showAll) {
            LazyColumn(
                modifier = Modifier.padding(
                    top = 20.dp,
                    end = 30.dp,
                    start = 30.dp
                )
            ) {
                allResults?.value?.size?.let {
                    items(it) { item ->
                        DisplayedResultsRow(displayedResults = allResults.value!![item])
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

