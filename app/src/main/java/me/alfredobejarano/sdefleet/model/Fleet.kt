package me.alfredobejarano.sdefleet.model

import com.google.gson.annotations.SerializedName

data class Fleet(
    @SerializedName("drivers") val drivers: List<String>,
    @SerializedName("shipments") val shipments: List<String>
)