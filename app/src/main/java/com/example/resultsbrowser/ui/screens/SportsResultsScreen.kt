package com.example.resultsbrowser.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resultsbrowser.viewmodels.SportsResultsViewModel

@Composable
fun SportsResultsScreen(
    modifier: Modifier = Modifier,
    isReload: Boolean = false,
//    isLoading: Boolean = false,
    sportsResultsViewModel: SportsResultsViewModel,
) {

//    val isLoading = sportsResultsViewModel.isLoadingLiveData.
    val isLoading = sportsResultsViewModel.getIsLoading().observeAsState()

    val displayData = sportsResultsViewModel.sportsResultsLiveData.observeAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.LightGray)
    ) {

        if (isReload) {
            OutlinedButton(
                onClick = {
                    //TODO fetch results
                    sportsResultsViewModel.requestSportsResults()
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
                    sportsResultsViewModel.requestSportsResults()
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

        if (isLoading.value == true) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }

    }


}

//@Preview(showBackground = true)
//@Composable
//fun FetchResultsNotLoadingPreview() {
//    ResultsBrowserTheme {
//        SportsResultsScreen(
//            isReload = false,
////            isLoading = false
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun FetchResultsLoadingPreview() {
//    ResultsBrowserTheme {
//        SportsResultsScreen(
//            isReload = false,
////            isLoading = true
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ReloadResultsNotLoadingPreview() {
//    ResultsBrowserTheme {
//        SportsResultsScreen(
//            isReload = true,
////            isLoading = false
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ReloadResultsLoadingPreview() {
//    ResultsBrowserTheme {
//        SportsResultsScreen(
//            isReload = true,
////            isLoading = true
//        )
//    }
//}

