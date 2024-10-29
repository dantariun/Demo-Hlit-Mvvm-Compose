package com.dantariun.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dantariun.signup.ui.SignUpScreen

fun NavController.navigateSignUp() {
    navigate(SignUpRoute.route)
}

fun NavGraphBuilder.signUpNavGraph(
    onLoginClick: (Int) -> Unit
) {
    composable(route = SignUpRoute.route) {
        SignUpScreen(onLoginClick)
    }
}

object SignUpRoute {
    const val route = "sign_up"
}