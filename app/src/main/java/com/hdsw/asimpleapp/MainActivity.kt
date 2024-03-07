package com.hdsw.asimpleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hdsw.asimpleapp.ui.screens.CatDetailScreen
import com.hdsw.asimpleapp.ui.screens.CatListScreen
import com.hdsw.asimpleapp.ui.theme.ASimpleAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ASimpleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    ASimpleAppNavHost(
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun ASimpleAppNavHost(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            CatListScreen(
                navController
            )
        }
        composable(
            "cat/{catId}",
            arguments = listOf(navArgument("catId") {
                type = NavType.StringType
            })
        ) {
            val catId = it.arguments?.getString("catId")
            catId?.let { id -> CatDetailScreen(navController = navController, catId = id) }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ASimpleAppTheme {
        Greeting("Android")
    }
}