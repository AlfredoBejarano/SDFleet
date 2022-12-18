package me.alfredobejarano.sdefleet.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.alfredobejarano.sdefleet.domain.MatchDriverWithShipmentUseCase
import me.alfredobejarano.sdefleet.model.Shipment
import javax.inject.Inject

@HiltViewModel
class AssignmentsViewModel @Inject constructor(
    private val matchDriverWithShipmentUseCase: MatchDriverWithShipmentUseCase
) : ViewModel() {
    private val _shipmentList = mutableStateOf(listOf<Shipment>())
    val shipmentList: State<List<Shipment>> = _shipmentList

    fun getShipments() {
        viewModelScope.launch {
            val shipments = matchDriverWithShipmentUseCase.matchDriverWithShipment()
                .map { entry ->
                    println("${entry.key} - ${entry.value}")
                    Shipment(driver = entry.value, address = entry.key)
                }
            _shipmentList.value = shipments
        }
    }
}