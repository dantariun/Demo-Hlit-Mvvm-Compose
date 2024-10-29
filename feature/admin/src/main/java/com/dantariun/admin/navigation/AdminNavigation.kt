package com.dantariun.admin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dantariun.admin.ui.AdminScreen


fun NavController.navigateAdmin(){
    navigate(AdminRoute.route)
}

fun NavGraphBuilder.adminNavGraph(
//    onAdminClick : (Int) -> Unit
) {
    composable(route = AdminRoute.route) {
        AdminScreen()
    }
}

object AdminRoute {
    const val route = "Admin"
}