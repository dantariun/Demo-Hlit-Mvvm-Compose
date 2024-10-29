package com.dantariun.login.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dantariun.domain.LoginUsecase
import com.dantariun.login.event.LoginEvent
import com.dantariun.model.InvalidUserException
import com.dantariun.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginuseCase: LoginUsecase
) :ViewModel() {

    private val _userEmail = mutableStateOf("")
    val userEmail : State<String> = _userEmail

    private val _userPassword = mutableStateOf("")
    val userPassword : State<String> = _userPassword

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when(event){
            is LoginEvent.EnteredEmail -> {
                _userEmail.value = event.value
            }
            is LoginEvent.EnteredPassword -> {
                _userPassword.value = event.value
            }
            is LoginEvent.Login -> {
                viewModelScope.launch {
                    try {
                        loginuseCase(
                            User(
                                email = userEmail.value,
                                password = userPassword.value
                            )
                        )
                        _eventFlow.emit(UiEvent.Login)
                    }catch (e:InvalidUserException){
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "로그인할 수 없습니다."
                            )
                        )
                    }
                }
            }
        }
    }


    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object Login : UiEvent()
    }
}