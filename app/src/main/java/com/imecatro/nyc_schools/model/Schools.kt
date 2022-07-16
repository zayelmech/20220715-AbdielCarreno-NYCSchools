package com.imecatro.nyc_schools.model

import com.google.gson.annotations.SerializedName


data class Schools(
    @SerializedName("dbn")
    val dbn: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("website")
    val website: String

)