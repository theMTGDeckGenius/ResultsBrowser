package com.example.resultsbrowser.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resultsbrowser.model.SportsResults
import com.example.resultsbrowser.network.SportsResultsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsResultsViewModel @Inject constructor(
    private val sportsResultsRepository: SportsResultsRepository
) : ViewModel() {

    //TODO Change to converted display data and store here
    private val _sportsResultsLiveData = MutableLiveData<SportsResults>()
    val sportsResultsLiveData: LiveData<SportsResults> = _sportsResultsLiveData

    fun getIsLoading(): LiveData<Boolean> {
        return sportsResultsRepository.isLoading
    }

    fun requestSportsResults() {
        viewModelScope.launch {
            try {
                sportsResultsRepository.fetchPostInfoList()
            } catch (throwable: Throwable) {
                //TODO handle error
            }
        }
    }
}