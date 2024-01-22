package com.example.resultsbrowser.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resultsbrowser.R
import com.example.resultsbrowser.ResultsBrowserApplication
import com.example.resultsbrowser.model.DisplayedResults
import com.example.resultsbrowser.model.F1Results
import com.example.resultsbrowser.model.NbaResults
import com.example.resultsbrowser.model.TennisResults
import com.example.resultsbrowser.network.SportsResultsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SportsResultsViewModel @Inject constructor(
    private val sportsResultsRepository: SportsResultsRepository,
    private val application: ResultsBrowserApplication
) : ViewModel() {

    val allSportsResultsLiveData: MutableLiveData<ArrayList<DisplayedResults>> =
        MutableLiveData<ArrayList<DisplayedResults>>(
            ArrayList()
        )
    val displaySportsResultsLiveData: MutableLiveData<ArrayList<DisplayedResults>> =
        MutableLiveData<ArrayList<DisplayedResults>>(
            ArrayList()
        )

    fun getIsLoading(): LiveData<Boolean> {
        return sportsResultsRepository.isLoading
    }

    fun requestSportsResults() {
        viewModelScope.launch {
            try {
                sportsResultsRepository.fetchPostInfoList()
                convertResultsForDisplay()
            } catch (throwable: Throwable) {
                Log.e("SportsResultsViewModel", "requestSportsResults: " + throwable.message)
            }
        }
    }

    fun fillUpList() {
        viewModelScope.launch {
            sportsResultsRepository.createBigList()
            convertResultsForDisplayBigList()
        }
    }

    private fun convertResultsForDisplay() {
        val data = sportsResultsRepository.sportsResultsMutableList.value
        val formatter = SimpleDateFormat("MMM dd, yyyy hh:mm:ss a", Locale.ENGLISH)
        allSportsResultsLiveData.value?.clear()
        displaySportsResultsLiveData.value?.clear()

        val f1Data = data?.f1Results
        if (f1Data != null) {
            for (item in f1Data) {
                val displayedResults = processF1results(item, formatter)
                allSportsResultsLiveData.value?.add(displayedResults)
            }
        }

        val tennisData = data?.tennisResults
        if (tennisData != null) {
            for (item in tennisData) {
                val displayedResults = processTennis1results(item, formatter)
                allSportsResultsLiveData.value?.add(displayedResults)
            }
        }

        val nbaData = data?.nbaResults
        if (nbaData != null) {
            for (item in nbaData) {
                val displayedResults = processNbaResults(item, formatter)
                allSportsResultsLiveData.value?.add(displayedResults)
            }
        }
        allSportsResultsLiveData.value?.sortByDescending { it.publicationDate }
        val mostRecentCalendar: Calendar = Calendar.getInstance()
        mostRecentCalendar.time = allSportsResultsLiveData.value?.get(0)?.publicationDate!!

        allSportsResultsLiveData.value?.forEach() { displayedResults ->
            val calendar: Calendar = Calendar.getInstance()
            displayedResults.publicationDate?.let { calendar.time = it }
            if (calendar.get(Calendar.DAY_OF_YEAR) == mostRecentCalendar.get(Calendar.DAY_OF_YEAR)) {
                displaySportsResultsLiveData.value?.add(displayedResults)
            }
        }

        Log.d("SportsResutsViewModel", "Finished parsing display list")
    }

    private fun convertResultsForDisplayBigList() {
        val data = sportsResultsRepository.sportsResultsMutableList.value
        val formatter = SimpleDateFormat("MMM dd, yyyy hh:mm:ss a", Locale.ENGLISH)
        displaySportsResultsLiveData.value?.clear()

        val f1Data = data?.f1Results
        if (f1Data != null) {
            for (item in f1Data) {
                val displayedResults = processF1results(item, formatter)

                displaySportsResultsLiveData.value?.add(displayedResults)
                displaySportsResultsLiveData.value?.add(displayedResults)
                displaySportsResultsLiveData.value?.add(displayedResults)
                displaySportsResultsLiveData.value?.add(displayedResults)
                displaySportsResultsLiveData.value?.add(displayedResults)
                displaySportsResultsLiveData.value?.add(displayedResults)
            }
        }

        val tennisData = data?.tennisResults
        if (tennisData != null) {
            for (item in tennisData) {
                val displayedResults = processTennis1results(item, formatter)

                displaySportsResultsLiveData.value?.add(displayedResults)
                displaySportsResultsLiveData.value?.add(displayedResults)
                displaySportsResultsLiveData.value?.add(displayedResults)
                displaySportsResultsLiveData.value?.add(displayedResults)
                displaySportsResultsLiveData.value?.add(displayedResults)
                displaySportsResultsLiveData.value?.add(displayedResults)
            }
        }

        val nbaData = data?.nbaResults
        if (nbaData != null) {
            for (item in nbaData) {
                val displayedResults = processNbaResults(item, formatter)

                displaySportsResultsLiveData.value?.add(displayedResults)
                displaySportsResultsLiveData.value?.add(displayedResults)
                displaySportsResultsLiveData.value?.add(displayedResults)
                displaySportsResultsLiveData.value?.add(displayedResults)
                displaySportsResultsLiveData.value?.add(displayedResults)
            }
        }

        Log.d("SportsResutsViewModel", "Finished parsing display list")
    }

    private fun processF1results(item: F1Results, formatter: SimpleDateFormat): DisplayedResults {
        val displayMessage = application.resources.getString(R.string.fOneResultsMessage)
            .replace("##WINNER##", item.winner)
            .replace("##TOURNAMENT##", item.tournament)
            .replace("##SECONDS##", item.seconds.toString())

        return DisplayedResults(
            publicationDate = formatter.parse(item.publicationDate),
            displayMessage = displayMessage,
            f1Results = item
        )
    }

    private fun processNbaResults(item: NbaResults, formatter: SimpleDateFormat): DisplayedResults {
        val displayMessage = application.resources.getString(R.string.nbaResultsMessage)
            .replace("##MVP##", item.mvp)
            .replace("##WINNER##", item.winner)
            .replace("##GAME_NUMBER##", item.gameNumber.toString())
            .replace("##TOURNAMENT##", item.tournament)

        return DisplayedResults(
            publicationDate = formatter.parse(item.publicationDate),
            displayMessage = displayMessage,
            nbaResults = item
        )
    }

    private fun processTennis1results(
        item: TennisResults,
        formatter: SimpleDateFormat
    ): DisplayedResults {
        val displayMessage = application.resources.getString(R.string.tennisResultsMessage)
            .replace("##TOURNAMENT##", item.tournament)
            .replace("##WINNER##", item.winner)
            .replace("##LOOSER##", item.looser)
            .replace("##SETS##", item.numberOfSets.toString())

        return DisplayedResults(
            publicationDate = formatter.parse(item.publicationDate),
            displayMessage = displayMessage,
            tennisResults = item
        )
    }
}