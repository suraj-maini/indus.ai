package com.boilerplate.app.presentation.screens.home

sealed class NavDrawerItem(var route: String, var icon: Int, var title: String) {
    object Add : NavDrawerItem("add", android.R.drawable.ic_menu_add, "Add")
    object Edit : NavDrawerItem("edit", android.R.drawable.ic_menu_edit, "Edit")
    object Search : NavDrawerItem("search", android.R.drawable.ic_menu_search, "Search")
    object Location : NavDrawerItem("location", android.R.drawable.ic_menu_mylocation, "Location")
    object Preferences : NavDrawerItem("preferences", android.R.drawable.ic_menu_preferences, "Preferences")
}