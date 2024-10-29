package com.dantariun.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dantariun.admin.navigation.AdminRoute
import com.dantariun.admin.navigation.adminNavGraph
import com.dantariun.admin.navigation.navigateAdmin
import com.dantariun.login.navigation.LoginRoute
import com.dantariun.login.navigation.loginNavGraph
import com.dantariun.login.navigation.navigateLogin
import com.dantariun.signup.navigation.navigateSignUp
import com.dantariun.signup.navigation.signUpNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = AdminRoute.route
                ) {
                    loginNavGraph(
                        onSignUpClick = { navController.navigateSignUp() },
                        onAdminClick = { navController.navigateAdmin() }
                    )
                    signUpNavGraph(
                        onLoginClick = { navController.navigateLogin() }
                    )
                    adminNavGraph()
                }
            }
        }
    }
}