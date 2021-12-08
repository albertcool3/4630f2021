package com.example.calendartask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calendartask.data.DatesLocalDataSource
import com.example.calendartask.data.DatesRepository
import com.example.calendartask.data.repository.Repository
import com.example.calendartask.domain.model.ColorModel
import com.example.calendartask.domain.model.DaySelected
import com.example.calendartask.domain.model.NoteModel
import com.example.calendartask.routing.CalendarTaskRouter
import com.example.calendartask.routing.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * View model used for storing the global app state.
 *
 * This view model is used for all screens.
 */
class MainViewModel(private val repository: Repository) : ViewModel() {

    private val datesRepository by lazy { DatesRepository(DatesLocalDataSource()) }
    val datesSelected = datesRepository.datesSelected
    val calendarYear = datesRepository.calendarYear

    fun onDaySelected(daySelected: DaySelected) {
        viewModelScope.launch {
            datesRepository.onDaySelected(daySelected)
        }
    }


    val notesNotInTrash: LiveData<List<NoteModel>> by lazy {
        repository.getAllNotesNotInTrash()
    }

    private var _noteEntry = MutableLiveData(NoteModel())
    val noteEntry: LiveData<NoteModel> = _noteEntry

    val colors: LiveData<List<ColorModel>> by lazy {
        repository.getAllColors()
    }

    val notesInTrash by lazy { repository.getAllNotesInTrash() }

    private var _selectedNotes = MutableLiveData<List<NoteModel>>(listOf())
    val selectedNotes: LiveData<List<NoteModel>> = _selectedNotes

    fun onCreateNewNoteClick() {
        _noteEntry.value = NoteModel()
        CalendarTaskRouter.navigateTo(Screen.SaveNote)
    }

    fun onNoteClick(note: NoteModel) {
        _noteEntry.value = note
        CalendarTaskRouter.navigateTo(Screen.SaveNote)
    }

    fun onNoteCheckedChange(note: NoteModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.insertNote(note)
        }
    }

    fun onNoteSelected(note: NoteModel) {
        _selectedNotes.value = _selectedNotes.value!!.toMutableList().apply {
            if (contains(note)) {
                remove(note)
            } else {
                add(note)
            }
        }
    }

    fun restoreNotes(notes: List<NoteModel>) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.restoreNotesFromTrash(notes.map { it.id })
            withContext(Dispatchers.Main) {
                _selectedNotes.value = listOf()
            }
        }
    }

    fun permanentlyDeleteNotes(notes: List<NoteModel>) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.deleteNotes(notes.map { it.id })
            withContext(Dispatchers.Main) {
                _selectedNotes.value = listOf()
            }
        }
    }

    fun onNoteEntryChange(note: NoteModel) {
        _noteEntry.value = note
    }

    fun saveNote(note: NoteModel, datesSelected: String) {
        viewModelScope.launch(Dispatchers.Default) {
            var note2 = note.copy(daySelected = datesSelected)
            repository.insertNote(note2)

            withContext(Dispatchers.Main) {
                CalendarTaskRouter.navigateTo(Screen.Notes)

                _noteEntry.value = NoteModel()

            }
        }
    }

    fun moveNoteToTrash(note: NoteModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.moveNoteToTrash(note.id)

            withContext(Dispatchers.Main) {
                CalendarTaskRouter.navigateTo(Screen.Notes)
            }
        }
    }
}
