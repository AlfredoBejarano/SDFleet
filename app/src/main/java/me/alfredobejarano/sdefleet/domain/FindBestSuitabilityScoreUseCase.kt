package me.alfredobejarano.sdefleet.domain

import javax.inject.Inject

class FindBestSuitabilityScoreUseCase @Inject constructor(
    private val calculateSuitabilityScoreUseCase: CalculateSuitabilityScoreUseCase
) {
    /**
     * Finds the highest Suitability Score for a given driver.
     * @param driverName - The name of the driver.
     * @return Address of the shipment.]
     */
    fun findBestShipmentAddress(driverName: String, shipments: List<String>): String {
        var bestAddress = ""
        var highestSs = 0f

        shipments.forEach { address ->
            val ss = calculateSuitabilityScoreUseCase.calculateSuitabilityScore(driverName, address)
            if (ss > highestSs) {
                highestSs = ss
                bestAddress = address
            }
        }

        return bestAddress
    }
}