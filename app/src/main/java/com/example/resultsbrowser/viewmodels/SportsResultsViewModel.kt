package com.example.resultsbrowser.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resultsbrowser.model.DisplayedResults
import com.example.resultsbrowser.network.SportsResultsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SportsResultsViewModel @Inject constructor(
    private val sportsResultsRepository: SportsResultsRepository
) : ViewModel() {

    //TODO Change to converted display data and store here

    val _allSportsResultsLiveData: MutableLiveData<ArrayList<DisplayedResults>> =
        MutableLiveData<ArrayList<DisplayedResults>>(
            ArrayList()
        )
    val _displaySportsResultsLiveData: MutableLiveData<ArrayList<DisplayedResults>> =
        MutableLiveData<ArrayList<DisplayedResults>>(
            ArrayList()
        )

//    val sportsResultsLiveData: LiveData<DisplayedResults> = _sportsResultsLiveData

    fun getIsLoading(): LiveData<Boolean> {
        return sportsResultsRepository.isLoading
    }

    fun requestSportsResults() {
        viewModelScope.launch {
            try {
                sportsResultsRepository.fetchPostInfoList()

                convertResultsForDisplay()
            } catch (throwable: Throwable) {
                //TODO handle error
            }
        }
    }

    fun convertResultsForDisplay() {
        val data = sportsResultsRepository.sportsResultsMutableList.value
        val formatter = SimpleDateFormat("MMM dd, yyyy hh:mm:ss a", Locale.ENGLISH)
        _allSportsResultsLiveData.value?.clear()
        _displaySportsResultsLiveData.value?.clear()

        val f1Data = data?.f1Results
        if (f1Data != null) {
            for (item in f1Data) {
                val displayMessage = item.winner.plus(" wins ")
                    .plus(item.tournament)
                    .plus(" by ")
                    .plus(item.seconds)
                    .plus(" seconds")
                val displayedResults = DisplayedResults(
                    publicationDate = item.publicationDate?.let { formatter.parse(it) },
                    displayMessage = displayMessage
                )

                _allSportsResultsLiveData.value?.add(displayedResults)
            }
        }

        val tennisData = data?.tennis
        if (tennisData != null) {
            for (item in tennisData) {
                val displayMessage = item.tournament.plus(": ")
                    .plus(item.winner)
                    .plus(" wins against ")
                    .plus(item.looser)
                    .plus(" in ")
                    .plus(item.numberOfSets)
                    .plus(" sets")
                val displayedResults = DisplayedResults(
                    publicationDate = item.publicationDate?.let { formatter.parse(it) },
                    displayMessage = displayMessage
                )

                _allSportsResultsLiveData.value?.add(displayedResults)
            }
        }

        val nbaData = data?.nbaResults
        if (nbaData != null) {
            for (item in nbaData) {
                val displayMessage = item.mvp.plus(" leads ")
                    .plus(item.winner)
                    .plus(" to game ")
                    .plus(item.gameNumber)
                    .plus(" win in the ")
                    .plus(item.tournament)
                val displayedResults = DisplayedResults(
                    publicationDate = item.publicationDate?.let { formatter.parse(it) },
                    displayMessage = displayMessage
                )

                _allSportsResultsLiveData.value?.add(displayedResults)
            }
        }
        _allSportsResultsLiveData.value?.sortByDescending { it.publicationDate }
        val mostRecentCalendar: Calendar = Calendar.getInstance()
        mostRecentCalendar.time = _allSportsResultsLiveData.value?.get(0)?.publicationDate!!

        _allSportsResultsLiveData.value?.forEach() { displayedResults ->
            val calendar: Calendar = Calendar.getInstance()
            displayedResults.publicationDate?.let { calendar.time = it }
            if (calendar.get(Calendar.DAY_OF_YEAR) == mostRecentCalendar.get(Calendar.DAY_OF_YEAR)) {
                _displaySportsResultsLiveData.value?.add(displayedResults)
            }
        }


        Log.d("SportsResutsViewModel", "Finished parsing display list")
    }
}