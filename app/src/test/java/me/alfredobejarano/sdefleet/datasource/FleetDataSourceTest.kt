package me.alfredobejarano.sdefleet.datasource

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.google.gson.Gson
import me.alfredobejarano.sdefleet.R
import me.alfredobejarano.sdefleet.model.Fleet
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [32])
@RunWith(RobolectricTestRunner::class)
class FleetDataSourceTest {
    private var candidate: FleetDataSource? = null

    @Before
    fun setUp() {
        val resources = ApplicationProvider.getApplicationContext<Application>().resources
        val resourceReader = resources.openRawResource(R.raw.test_fleet)
        candidate = FleetDataSource(Gson(), resourceReader)
    }

    @After
    fun tearDown() {
        candidate = null
    }

    @Test
    fun `Proper Object built from proper JSON file`() {
        val expected = Fleet(
            drivers = listOf("Jane Doe", "John Doe"),
            shipments = listOf("123 Oak Street Apt. 21", "241 Mahogany Street Suite 2")
        )

        val fleet = candidate?.fleet
        assert(fleet != null)

        fleet?.drivers?.forEachIndexed { index, driver ->
            assert(expected.drivers[index] == driver)
        }

        fleet?.shipments?.forEachIndexed { index, shipment ->
            assert(expected.shipments[index] == shipment)
        }
    }
}