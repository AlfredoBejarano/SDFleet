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
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FindBestSuitabilityScoreUseCaseTest {
    private var mockitoCloseable: AutoCloseable? = null

    @Mock
    private lateinit var mockFleetRepository: FleetRepository

    private val extractStreetNameUseCase = ExtractStreetNameFromShipmentUseCase()
    private val calculateSSUseCase = CalculateSuitabilityScoreUseCase(extractStreetNameUseCase)

    private var candidate: FindBestSuitabilityScoreUseCase? = null

    @Before
    fun setUp() {
        mockitoCloseable = MockitoAnnotations.openMocks(this)
        candidate = FindBestSuitabilityScoreUseCase(mockFleetRepository, calculateSSUseCase)
    }

    @After
    fun tearDown() {
        candidate = null
        mockitoCloseable?.close()
        mockitoCloseable = null
    }

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

        Mockito.`when`(mockFleetRepository.getShipmentAddresses()).thenReturn(mockAddressList)

        val bestAddress = candidate?.findBestShipmentAddress(addressName)
        assert(bestAddress == expectedAddress)
    }
}