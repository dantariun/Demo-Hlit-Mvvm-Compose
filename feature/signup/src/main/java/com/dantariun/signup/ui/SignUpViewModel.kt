package com.dantariun.signup.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dantariun.domain.SignUpUsecase
import com.dantariun.model.InvalidUserException
import com.dantariun.model.User
import com.dantariun.signup.event.SignUpEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUsecase,
) : ViewModel(){

    private val _userEmail = mutableStateOf("")
    val userEmail: State<String> = _userEmail

    private val _userPassword = mutableStateOf("")
    val userPassword: State<String> = _userPassword

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event:SignUpEvent) {
        when (event) {
            is SignUpEvent.EnteredEmail -> {
                _userEmail.value = event.value
            }
            is SignUpEvent.EnteredPassword -> {
                _userPassword.value = event.value
            }
            is SignUpEvent.SignUp -> {
                viewModelScope.launch {
                    try{
                        signUpUseCase (
                            User (
                                email = userEmail.value,
                                password = userPassword.value
                            )
                        )
                        _eventFlow.emit(UiEvent.SignUp)
                    }catch (e:InvalidUserException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar (
                                message = e.message ?: "You cannot register as a member."
                            )
                        )
                    }
                }
            }
        }
    }


    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SignUp : UiEvent()
    }
}