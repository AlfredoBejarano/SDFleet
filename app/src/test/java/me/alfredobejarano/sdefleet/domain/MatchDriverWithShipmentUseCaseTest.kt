@file:OptIn(ExperimentalCoroutinesApi::class)

package me.alfredobejarano.sdefleet.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import me.alfredobejarano.sdefleet.repository.FleetRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MatchDriverWithShipmentUseCaseTest {
    private val extractStreetNameFromShipmentUseCase = ExtractStreetNameFromShipmentUseCase()
    private val calculateSsUseCase =
        CalculateSuitabilityScoreUseCase(extractStreetNameFromShipmentUseCase)
    private val findBestSsUseCase = FindBestSuitabilityScoreUseCase(calculateSsUseCase)

    private var candidate: MatchDriverWithShipmentUseCase? = null

    @Mock
    private lateinit var mockRepository: FleetRepository

    @Before
    fun setUp() {
        candidate =
            MatchDriverWithShipmentUseCase(mockRepository, findBestSsUseCase, calculateSsUseCase)
    }

    @After
    fun tearDown() {
        candidate = null
    }

    /**
     * Tests that the best driver gets matched to the best shipment preventing duplicates between
     * shipment to driver relationship.
     *
     *
     * John Doe - 123 Oak Street Apt. 21 - 6.75 SS
     *
     * John Doe - 241 Mahogany Street Suite 2 - 8.0 SS
     *
     * Jane Doe - 123 Oak Street Apt. 21 - 9.0 SS
     *
     * Jane Doe - 241 Mahogany Street Suite 2 - 8.0 SS
     *
     *
     * The expected result should be
     *
     *
     * Jane Doe - 123 Oak Street Apt. 21 - 9.0 SS
     *
     * John Doe - 241 Mahogany Street Suite 2 - 8.0 SS
     *
     * @see CalculateSuitabilityScoreUseCaseTest
     * @see FindBestSuitabilityScoreUseCaseTest
     */
    @Test
    fun `Get unique assignments for driver - address test`() = runTest {
        val mockDrivers = listOf(
            "Jane Doe",
            "John Doe"
        )

        val mockShipments = listOf(
            "123 Oak Street Apt. 21",
            "241 Mahogany Street Suite 2"
        )

        Mockito.`when`(mockRepository.getDriverNames()).thenReturn(mockDrivers)
        Mockito.`when`(mockRepository.getShipmentAddresses()).thenReturn(mockShipments)

        val assignment = candidate?.matchDriverWithShipment()

        assignment?.forEach { entry ->
            if (entry.value == "Jane Doe") assert(entry.key == "123 Oak Street Apt. 21")
            if (entry.value == "John Doe") assert(entry.key == "241 Mahogany Street Suite 2")
        }
    }
}