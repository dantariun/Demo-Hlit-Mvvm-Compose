package com.dantariun.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dantariun.login.ui.LoginScreen

fun NavController.navigateLogin(){
    navigate(LoginRoute.route)
}

fun NavGraphBuilder.loginNavGraph(
    onSignUpClick : (Int) -> Unit,
    onAdminClick : () -> Unit
) {
    composable(route = LoginRoute.route) {
        LoginScreen(
            onSignUpClick = onSignUpClick,
            onAdminClick = onAdminClick
        )
    }
}



object LoginRoute {
    const val route = "Login"
}