package org.d3if3159.assessment1.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
}