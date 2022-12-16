package me.alfredobejarano.sdefleet.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CalculateSuitabilityScoreUseCaseTest {
    private val extractStreetNameUseCase = ExtractStreetNameFromShipmentUseCase()
    private val candidate = CalculateSuitabilityScoreUseCase(extractStreetNameUseCase)

    /**
     * Performs the calculation for a driver named "John Doe"
     * for the shipment "123 Oak Street Apt. 21".
     *
     *
     *
     * Street address: Oak Street
     *
     * Street address size: 10
     *
     * Is street address size even: yes
     *
     * Vowels in driver name: 3
     *
     * Base suitability score: 4.5 (Vowels * 1.5)
     *
     * Driver name length: 8
     *
     * Driver name factors: 1, **2**, 4, 8
     *
     * Street address factors: 1, **2**, 5, 10
     *
     * Shared factor by both lengths: 2
     *
     * Is the shared factor different than 1?: yes
     *
     * Increment to base SS: 50%
     *
     * Total SS: 6.75 (4.5 + 2.25)
     */
    @Test
    fun `Calculate suitability score test`() {
        val address = "123 Oak Street Apt. 21"
        val name = "John Doe"
        val expectedSS = 6.75f

        val calculation = candidate.calculateSuitabilityScore(name, address)
        assert(expectedSS == calculation)
    }
}