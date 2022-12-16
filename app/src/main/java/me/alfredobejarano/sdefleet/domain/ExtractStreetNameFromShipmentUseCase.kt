package me.alfredobejarano.sdefleet.domain

/**
 * Use case that extracts a Street name from an address.
 * Ex. 63187 Volkman Garden Suite 447 returns Volkman Garden.
 */
class ExtractStreetNameFromShipmentUseCase {
    private val interiorAbbreviations = arrayOf("Suite", "Apt.")

    /**
     * Returns true if the string is a number.
     * Ex. "133" returns true.
     * Ex. "Oak Street" returns false.
     * Ex. null returns false.
     */
    private fun String?.isNumber() = this?.toIntOrNull() != null

    /**
     * Extracts the street name of a Shipment address.
     */
    fun extractStreetName(shipment: String) = shipment.split(" ").filter { subString ->
        !subString.isNumber() && !interiorAbbreviations.contains(subString)
    }.joinToString(" ")
}