package com.dantariun.admin.event

sealed class AdminEvent {
    data class DeleteUser(val value: String): AdminEvent()
    object Admin: AdminEvent()
}