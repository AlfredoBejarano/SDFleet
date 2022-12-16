package me.alfredobejarano.sdefleet.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ExtractStreetNameFromShipmentUseCaseTest {
    private val candidate = ExtractStreetNameFromShipmentUseCase()

    /**
     * Executes the function in the following list of addresses:
     *            Input                                 Expected
     *     "215 Osinski Manors"                     "Osinski Manors"
     *     "9856 Marvin Stravenue"                  "Marvin Stravenue"
     *     "7127 Kathlyn Ferry"                     "Kathlyn Ferry"
     *     "987 Champlin Lake"                      "Champlin Lake"
     *     "63187 Volkman Garden Suite 447"         "Volkman Garden"
     *     "75855 Dessie Lights"                    "Dessie Lights"
     *     "1797 Adolf Island Apt. 744"             "Adolf Island"
     *     "2431 Lindgren Corners"                  "Lindgren Corners"
     *     "8725 Aufderhar River Suite 859"         "Aufderhar River"
     *     "79035 Shanna Light Apt. 322"            "Shanna Light"
     */
    @Test
    fun `Extract street names test`() {
        val expectedNames = listOf(
            "Osinski Manors",
            "Marvin Stravenue",
            "Kathlyn Ferry",
            "Champlin Lake",
            "Volkman Garden",
            "Dessie Lights",
            "Adolf Island",
            "Lindgren Corners",
            "Aufderhar River",
            "Shanna Light"
        )

        val resultNames = listOf(
            "215 Osinski Manors",
            "9856 Marvin Stravenue",
            "7127 Kathlyn Ferry",
            "987 Champlin Lake",
            "63187 Volkman Garden Suite 447",
            "75855 Dessie Lights",
            "1797 Adolf Island Apt. 744",
            "2431 Lindgren Corners",
            "8725 Aufderhar River Suite 859",
            "79035 Shanna Light Apt. 322"
        ).map(candidate::extractStreetName)

        expectedNames.forEachIndexed { index, shipment ->
            assert(shipment == resultNames[index])
        }
    }
}