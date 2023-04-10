@file:OptIn(ExperimentalFoundationApi::class)

package com.example.composebugs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.composebugs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            ComposeView(this).apply {
                setContent {
                    Scaffold(
                        bottomBar = {
                            BottomNavigation {
//                                val navBackStackEntry by findNavController().currentBackStackEntryFlow.collectAsState(
//                                    initial = false
//                                )
//                                val currentDestination =
//                                    (navBackStackEntry as? NavBackStackEntry)?.destination
                                BottomNavigationItem(
                                    icon = {
                                        Icon(
                                            Icons.Filled.Favorite,
                                            contentDescription = null
                                        )
                                    },
                                    label = { Text("Test") },
                                    selected = false,//currentDestination?.hierarchy?.any { it.id == R.id.nav_graph } == true,
                                    onClick = {
//                                        navController.navigate(R.id.nav_graph) {
//                                            // Pop up to the start destination of the graph to
//                                            // avoid building up a large stack of destinations
//                                            // on the back stack as users select items
//                                            popUpTo(navController.graph.findStartDestination().id) {
//                                                saveState = true
//                                            }
//                                            // Avoid multiple copies of the same destination when
//                                            // reselecting the same item
//                                            launchSingleTop = true
//                                            // Restore state when reselecting a previously selected item
//                                            restoreState = true
//                                        }
                                    }
                                )
                            }
                        }
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            AndroidViewBinding(ActivityMainBinding::inflate)
                        }
                    }
                }
            }
        )
    }
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val nc = binding.navHostFragment.findNavController()
//        val a = 1
//        setContentView(binding.root)
//
//        navController =
//            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) ||
            super.onSupportNavigateUp()
    }

    private fun findNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }
}
