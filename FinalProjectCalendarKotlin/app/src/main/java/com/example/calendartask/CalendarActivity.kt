

/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.calendartask

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.example.calendartask.R
import com.example.calendartask.domain.model.CalendarDay
import com.example.calendartask.domain.model.CalendarMonth
import com.example.calendartask.domain.model.DaySelected
import com.example.calendartask.data.CalendarYear
import com.example.calendartask.ui.theme.CalendarTaskTheme
import com.example.calendartask.viewmodel.CalendarViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calendartask.routing.CalendarTaskRouter
import com.example.calendartask.routing.Screen
import com.example.calendartask.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


//import com.google.accompanist.insets.ProvideWindowInsets
//import com.google.accompanist.insets.statusBarsHeight
//import dagger.hilt.android.AndroidEntryPoint

fun launchCalendarActivity(context: Context) {
    val intent = Intent(context, CalendarActivity::class.java)
    context.startActivity(intent)
}

@AndroidEntryPoint
class CalendarActivity : ComponentActivity() {
    //private val exampleViewModel: CalendarViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            //ProvideWindowInsets {
                CalendarTaskTheme {
                    CalendarScreen()
                }
            //}
        }
    }
}

@Composable
fun CalendarScreen(
    //onBackPressed: () -> Unit,
    calendarViewModel: MainViewModel = viewModel()
) {
    val calendarYear = calendarViewModel.calendarYear

    CalendarContent(
        selectedDates = calendarViewModel.datesSelected.toString(),
        calendarYear = calendarYear,
        onDayClicked = { calendarDay, calendarMonth ->
            calendarViewModel.onDaySelected(
                DaySelected(calendarDay.value.toInt(), calendarMonth, calendarYear)
            )
        }
        //onBackPressed = onBackPressed
    )
}

@Composable
private fun CalendarContent(
    selectedDates: String,
    calendarYear: CalendarYear,
    onDayClicked: (CalendarDay, CalendarMonth) -> Unit
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        topBar = {
            CalendarTopAppBar(selectedDates)
        }
    ) {
        Calendar(calendarYear, onDayClicked)
    }
}

@Composable
private fun CalendarTopAppBar(selectedDates: String) {
    Column {
        Spacer(
            modifier = Modifier
                //.statusBarsHeight()
                .fillMaxWidth()
                .background(MaterialTheme.colors.primaryVariant)
        )
        TopAppBar(
            title = {
                Text(
                    text = if (selectedDates.isEmpty()) "Select Dates"
                    else selectedDates
                )
            },
            navigationIcon = {
                IconButton(onClick = { CalendarTaskRouter.navigateTo(Screen.SaveNote) }) {
                    Image(
                        painter = painterResource(R.drawable.ic_back),
                        contentDescription = stringResource(R.string.cd_back)
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.primaryVariant,
            elevation = 0.dp
        )
    }
}
