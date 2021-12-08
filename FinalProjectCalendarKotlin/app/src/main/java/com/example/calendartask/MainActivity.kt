package com.example.calendartask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.calendartask.ui.theme.CalendarTaskTheme
import com.example.calendartask.routing.CalendarTaskRouter
import com.example.calendartask.routing.Screen
import com.example.calendartask.ui.screens.NotesScreen
import com.example.calendartask.ui.screens.SaveNoteScreen
import com.example.calendartask.ui.screens.TrashScreen
import com.example.calendartask.viewmodel.MainViewModel
import com.example.calendartask.viewmodel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels(factoryProducer = {
        MainViewModelFactory(
            this,
            (application as CalendarTaskApplication).dependencyInjector.repository
        )
    })

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    // Greeting("Android")
                    MainActivityScreen(viewModel = viewModel)
                    // launchCalendarActivity(this)
                }
            }
        }
    }
}

@Composable
@ExperimentalMaterialApi
private fun MainActivityScreen(viewModel: MainViewModel) {
    Surface {
        when (CalendarTaskRouter.currentScreen) {
            is Screen.Notes->NotesScreen(viewModel)
            is Screen.Calendar -> CalendarScreen(viewModel)
            is Screen.SaveNote -> SaveNoteScreen(viewModel)
            is Screen.Trash -> TrashScreen(viewModel)
        }
    }
}