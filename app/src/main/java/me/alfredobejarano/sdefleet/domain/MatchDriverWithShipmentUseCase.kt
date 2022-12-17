package me.alfredobejarano.sdefleet.domain

import me.alfredobejarano.sdefleet.repository.FleetRepository
import javax.inject.Inject

class MatchDriverWithShipmentUseCase @Inject constructor(
    private val repository: FleetRepository,
    private val findBestSsUseCase: FindBestSuitabilityScoreUseCase,
    private val calculateSsUseCase: CalculateSuitabilityScoreUseCase
) {
    suspend fun matchDriverWithShipment(): HashMap<String, String> {
        val drivers = repository.getDriverNames()
        val shipments = repository.getShipmentAddresses()

        val skippedDrivers = mutableListOf<String>()
        val assignments = hashMapOf<String, String>()

        // Assign drivers to shipments
        assignDriverToShipment(drivers, shipments, skippedDrivers, assignments)

        do {
            val newDrivers = mutableListOf<String>().apply { addAll(skippedDrivers) }
            skippedDrivers.clear()
            val remainingShipments =
                shipments.filter { shipment -> !assignments.keys.contains(shipment) }
            assignDriverToShipment(newDrivers, remainingShipments, skippedDrivers, assignments)
        } while (skippedDrivers.isNotEmpty())

        return assignments
    }

    /**
     * Assigns a driver to a shipment.
     * @param drivers - The list of drivers to assign
     * @param shipments - The list of shipments to assign to a driver
     * @param skippedDrivers - The list of conflicting drivers
     * @param assignments - The record of assignments
     *
     * @see onDriverShipmentCollision
     */
    private fun assignDriverToShipment(
        drivers: List<String>,
        shipments: List<String>,
        skippedDrivers: MutableList<String>,
        assignments: HashMap<String, String>
    ) = drivers.forEach { driver ->
        val shipment = findBestSsUseCase.findBestShipmentAddress(driver, shipments)

        if (assignments.containsKey(shipment)) {
            onDriverShipmentCollision(shipment, driver, skippedDrivers, assignments)
        } else {
            assignments[shipment] = driver
        }
    }

    /**
     * Re-calculates the SS for the existing driver and the new driver and assigns the shipment
     * address to the driver with the highest SS for the current shipment address.
     *
     * The other drivers gets into a "skipped" list for finding the 2nd best shipment address for
     * said driver.
     *
     * @param shipment - The conflicting address
     * @param newDriver - The driver causing the conflict
     * @param skippedDrivers - List of drivers when the "losing" driver will be set.
     * @param assignments - HashMap keeping the record of Shipment - Driver relationship.
     */
    private fun onDriverShipmentCollision(
        shipment: String,
        newDriver: String,
        skippedDrivers: MutableList<String>,
        assignments: HashMap<String, String>
    ) {
        val currentDriver = assignments[shipment].orEmpty()
        val currentDriverSs = calculateSsUseCase.calculateSuitabilityScore(currentDriver, shipment)

        val newDriverSs = calculateSsUseCase.calculateSuitabilityScore(newDriver, shipment)

        if (newDriverSs > currentDriverSs) {
            assignments[shipment] = newDriver
            skippedDrivers.add(currentDriver)
        } else {
            skippedDrivers.add(newDriver)
        }
    }

}