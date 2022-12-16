package me.alfredobejarano.sdefleet.model

import com.google.gson.annotations.SerializedName

data class Fleet(
    @SerializedName("shipments") val drivers: List<String>,
    @SerializedName("drivers") val shipments: List<String>
)