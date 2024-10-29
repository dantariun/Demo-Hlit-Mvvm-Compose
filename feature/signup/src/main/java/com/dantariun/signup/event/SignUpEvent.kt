package com.dantariun.signup.event

sealed class SignUpEvent {
    data class EnteredEmail(val value: String): SignUpEvent()
    data class EnteredPassword(val value: String): SignUpEvent()
    object SignUp: SignUpEvent()
}