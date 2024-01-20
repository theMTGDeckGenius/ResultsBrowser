package com.example.resultsbrowser.viewmodels

import androidx.lifecycle.ViewModel
import com.example.resultsbrowser.network.SportsResultsRepository
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class SportsResultsViewModel @AssistedInject constructor(
) : ViewModel() {
    private val sportsResultsRepository: SportsResultsRepository = SportsResultsRepository()


}