package com.example.resultsbrowser.model

import com.google.gson.annotations.SerializedName


data class SportsResults(
    @SerializedName("f1Results") var f1Results: ArrayList<F1Results> = arrayListOf(),
    @SerializedName("nbaResults") var nbaResults: ArrayList<NbaResults> = arrayListOf(),
    @SerializedName("Tennis") var tennis: ArrayList<Tennis> = arrayListOf()
)

data class F1Results(
    @SerializedName("publicationDate") var publicationDate: String? = null,
    @SerializedName("seconds") var seconds: Double? = null,
    @SerializedName("tournament") var tournament: String? = null,
    @SerializedName("winner") var winner: String? = null
)

data class NbaResults(
    @SerializedName("gameNumber") var gameNumber: Int? = null,
    @SerializedName("looser") var looser: String? = null,
    @SerializedName("mvp") var mvp: String? = null,
    @SerializedName("publicationDate") var publicationDate: String? = null,
    @SerializedName("tournament") var tournament: String? = null,
    @SerializedName("winner") var winner: String? = null

)

data class Tennis(
    @SerializedName("looser") var looser: String? = null,
    @SerializedName("numberOfSets") var numberOfSets: Int? = null,
    @SerializedName("publicationDate") var publicationDate: String? = null,
    @SerializedName("tournament") var tournament: String? = null,
    @SerializedName("winner") var winner: String? = null
)