package com.example.resultsbrowser.model

import java.util.Date

data class DisplayedResults(
    var publicationDate: Date? = null,
    var displayMessage: String? = null,
    var f1Results: F1Results? = null,
    var nbaResults: NbaResults? = null,
    var tennisResults: TennisResults? = null
)
