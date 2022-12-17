@file:OptIn(ExperimentalCoroutinesApi::class)

package me.alfredobejarano.sdefleet.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FindBestSuitabilityScoreUseCaseTest {
    private val extractStreetNameUseCase = ExtractStreetNameFromShipmentUseCase()
    private val calculateSSUseCase = CalculateSuitabilityScoreUseCase(extractStreetNameUseCase)

    private val candidate = FindBestSuitabilityScoreUseCase(calculateSSUseCase)

    /**
     * Test that the address returned is the one with the highest SS.
     *
     * 123 Oak Street Apt. 21 SS: 6.75
     * 241 Mahogany Street Suite 2 SS: 8.0
     *
     * @see CalculateSuitabilityScoreUseCaseTest
     */
    @Test
    fun `find best SS for a driver`() = runTest {
        val addressName = "John Doe"
        val expectedAddress = "241 Mahogany Street Suite 2"
        val mockAddressList = listOf("123 Oak Street Apt. 21", "241 Mahogany Street Suite 2")

        val bestAddress = candidate.findBestShipmentAddress(addressName, mockAddressList)
        assert(bestAddress == expectedAddress)
    }
}