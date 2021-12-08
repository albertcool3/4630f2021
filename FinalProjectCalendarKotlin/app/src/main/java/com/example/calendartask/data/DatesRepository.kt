

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

package com.example.calendartask.data

import com.example.calendartask.domain.model.DatesSelectedState
import com.example.calendartask.domain.model.DaySelected
import com.example.calendartask.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Annotated with Singleton because [DatesSelectedState] contains mutable state.
 */
@Singleton
class DatesRepository @Inject constructor(
    datesLocalDataSource: DatesLocalDataSource  //,
  //  @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,

    ) {
    val calendarYear = datesLocalDataSource.year2021
    val datesSelected = DatesSelectedState(datesLocalDataSource.year2021)

    suspend fun onDaySelected(daySelected: DaySelected) = withContext(Dispatchers.IO) {
        datesSelected.daySelected(daySelected)
    }
}
