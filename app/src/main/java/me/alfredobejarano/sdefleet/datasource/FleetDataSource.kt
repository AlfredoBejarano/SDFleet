package me.alfredobejarano.sdefleet.datasource

import com.google.gson.Gson
import me.alfredobejarano.sdefleet.di.FleetJSON
import me.alfredobejarano.sdefleet.model.Fleet
import java.io.InputStream
import javax.inject.Inject

class FleetDataSource @Inject constructor(
    gson: Gson, @FleetJSON private val resourceReader: InputStream
) {
    val fleet: Fleet by lazy {
        val jsonString = resourceReader.bufferedReader(Charsets.UTF_8).use { it.readText() }
        gson.fromJson(jsonString, Fleet::class.java)
    }
}