package me.alfredobejarano.sdefleet.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import me.alfredobejarano.sdefleet.domain.MatchDriverWithShipmentUseCase
import javax.inject.Inject

@HiltViewModel
class AssignmentsViewModel @Inject constructor(
    private val matchDriverWithShipmentUseCase: MatchDriverWithShipmentUseCase
) : ViewModel() {
    fun getAssignments(): LiveData<List<Pair<String, String>>> = liveData(Dispatchers.IO) {
        val itinerary = matchDriverWithShipmentUseCase.matchDriverWithShipment()
            .map { it.key to it.value }
        emit(itinerary)
    }
}