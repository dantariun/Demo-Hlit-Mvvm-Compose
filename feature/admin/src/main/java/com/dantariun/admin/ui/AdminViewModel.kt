package com.dantariun.admin.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dantariun.admin.event.AdminEvent
import com.dantariun.domain.AdminDeleteUseCase
import com.dantariun.domain.AdminUseCase
import com.dantariun.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val adminUseCase: AdminUseCase,
    private val adminDeleteUseCase: AdminDeleteUseCase
) : ViewModel() {

    private val _users:MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val users:StateFlow<List<User>> = _users

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event : AdminEvent){
        when(event) {
            is AdminEvent.DeleteUser -> {
                viewModelScope.launch {
                    adminDeleteUseCase(event.value)
                    _eventFlow.emit(UiEvent.Delete)
                }
            }
            is AdminEvent.Admin -> {
                viewModelScope.launch {
                    try {
                        _users.emit(adminUseCase())
                    } catch ( e: Exception){
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Users search failed"
                            )
                        )
                    }
                }
            }
        }
    }


    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object Delete : UiEvent()
    }
}