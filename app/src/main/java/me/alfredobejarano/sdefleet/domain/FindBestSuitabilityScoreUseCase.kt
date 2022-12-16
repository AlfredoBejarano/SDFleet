package me.alfredobejarano.sdefleet.domain

import me.alfredobejarano.sdefleet.repository.FleetRepository
import javax.inject.Inject

class FindBestSuitabilityScoreUseCase @Inject constructor(
    private val fleetRepository: FleetRepository,
    private val calculateSuitabilityScoreUseCase: CalculateSuitabilityScoreUseCase
) {
    /**
     * Finds the highest Suitability Score for a given driver.
     * @param driverName - The name of the driver.
     * @return Address of the shipment.]
     */
    suspend fun findBestShipmentAddress(driverName: String): String {
        var bestAddress = ""
        var highestSs = 0f

        fleetRepository.getShipmentAddresses().forEach { address ->
            val ss = calculateSuitabilityScoreUseCase.calculateSuitabilityScore(driverName, address)
            if (ss > highestSs) {
                highestSs = ss
                bestAddress = address
            }
        }

        return bestAddress
    }
}