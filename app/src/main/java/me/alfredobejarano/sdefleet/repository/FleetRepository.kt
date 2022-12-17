package me.alfredobejarano.sdefleet.repository

import kotlinx.coroutines.suspendCancellableCoroutine
import me.alfredobejarano.sdefleet.datasource.FleetDataSource
import me.alfredobejarano.sdefleet.model.Fleet
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FleetRepository @Inject constructor(private val fleetDataSource: FleetDataSource) {

    /**
     * Function that wraps the retrieving of a [Fleet] property in a coroutine with a try-catch
     * for exception handling.
     *
     * @param dataSource - The source of the List of Strings coming from a [Fleet] object
     * @return - Said list of items or an Exception if something went wrong fetching the fleet object.
     */
    private suspend fun getFleetDataList(dataSource: () -> List<String>) =
        suspendCancellableCoroutine<List<String>> { continuation ->
            try {
                continuation.resume(dataSource())
            } catch (t: Throwable) {
                continuation.resumeWithException(t)
            }
        }

    /**
     * Returns the list of drivers from the fleet.
     * @return List of driver names or an Exception related to fetching the fleet.
     */
    suspend fun getDriverNames() = getFleetDataList { fleetDataSource.fleet.drivers }

    /**
     * Returns the list of shipment addresses from the fleet.
     * @return List of shipment addresses or an Exception related to fetching the fleet.
     */
    suspend fun getShipmentAddresses() = getFleetDataList { fleetDataSource.fleet.drivers }
}