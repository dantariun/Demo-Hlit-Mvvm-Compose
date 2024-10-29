package com.dantariun.admin.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dantariun.admin.event.AdminEvent
import com.dantariun.model.User
import kotlinx.coroutines.flow.collectLatest


@Composable
fun AdminScreen (
    viewModel: AdminViewModel = hiltViewModel()
    ) {
    val userList by viewModel.users.collectAsStateWithLifecycle()
    viewModel.onEvent(AdminEvent.Admin)

    val snackBarHostState = remember { SnackbarHostState() }
    SnackbarHost(hostState = snackBarHostState)
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event){
                is AdminViewModel.UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AdminViewModel.UiEvent.Delete -> {
                    viewModel.onEvent(AdminEvent.Admin)
                }
            }
        }
    }

    Column ( modifier = Modifier.fillMaxSize()) {
        AdminAppBar()
        AdminContent(userList)
    }
}

@Composable
fun AdminContent(
    userList : List<User>
){
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(userList) { item ->
            UserItem(user = item)
        }
    }
}

@Composable
fun UserItem(user:User,
             viewModel: AdminViewModel = hiltViewModel()
             ){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = user.email, fontSize = 20.sp)
            IconButton(onClick = {
                viewModel.onEvent(AdminEvent.DeleteUser(user.email))
            }) {
                Icon(Icons.Rounded.Delete, contentDescription = null)
            }
        }
    }
}

@Composable
fun AdminAlertDialog(){
    AlertDialog(onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminAppBar(){
    TopAppBar(title = {
        Text(
            text = "Admin - User List",
            color = Color.White
        )
    },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue
        )
    )
}

private val userListDummy = listOf(
    User("harrison@pet-store.com", ""),
    User("calhoun@pet-store.com", ""),
)

@Composable
@Preview
fun PreviewAppBar(){
    AdminAppBar()
}

@Composable
@Preview
fun PreviewAdminScreen(){
    AdminContent(
        userListDummy
    )
}