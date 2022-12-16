package me.alfredobejarano.sdefleet.domain

import me.alfredobejarano.sdefleet.utils.hcf
import javax.inject.Inject

class CalculateSuitabilityScoreUseCase @Inject constructor(
    private val extractStreetNameUseCase: ExtractStreetNameFromShipmentUseCase
) {
    private val vowels = arrayOf('a', 'e', 'i', 'o', 'u')

    fun calculateSuitabilityScore(driverName: String, shipment: String): Float {
        val streetAddressSize = extractStreetNameUseCase.extractStreetName(shipment).length
        val isStreetAddressSizeEven = streetAddressSize % 2 == 0

        val baseSuitabilityScore = if (isStreetAddressSizeEven) {
            driverName.map { char -> vowels.contains(char) }.size * 1.5f
        } else {
            driverName.map { char -> !vowels.contains(char) }.size * 1f
        }

        val suitabilityScoreMultiplier = if (streetAddressSize.hcf(driverName.length) > 1) {
            0.5f
        } else {
            0f
        }

        return baseSuitabilityScore + (baseSuitabilityScore * suitabilityScoreMultiplier)
    }
}