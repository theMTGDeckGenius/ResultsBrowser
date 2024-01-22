package com.example.resultsbrowser.model

import com.google.gson.annotations.SerializedName


data class SportsResults(
    @SerializedName("f1Results") var f1Results: ArrayList<F1Results> = arrayListOf(),
    @SerializedName("nbaResults") var nbaResults: ArrayList<NbaResults> = arrayListOf(),
    @SerializedName("Tennis") var tennisResults: ArrayList<TennisResults> = arrayListOf()
)

data class F1Results(
    @SerializedName("publicationDate") var publicationDate: String = "",
    @SerializedName("seconds") var seconds: Double = 0.0,
    @SerializedName("tournament") var tournament: String = "",
    @SerializedName("winner") var winner: String = ""
)

data class NbaResults(
    @SerializedName("gameNumber") var gameNumber: Int = 0,
    @SerializedName("looser") var looser: String = "",
    @SerializedName("mvp") var mvp: String = "",
    @SerializedName("publicationDate") var publicationDate: String = "",
    @SerializedName("tournament") var tournament: String = "",
    @SerializedName("winner") var winner: String = ""

)

data class TennisResults(
    @SerializedName("looser") var looser: String = "",
    @SerializedName("numberOfSets") var numberOfSets: Int = 0,
    @SerializedName("publicationDate") var publicationDate: String = "",
    @SerializedName("tournament") var tournament: String = "",
    @SerializedName("winner") var winner: String = ""
)
