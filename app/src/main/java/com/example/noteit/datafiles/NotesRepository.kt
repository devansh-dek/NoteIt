package com.example.noteit.datafiles

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers


class NotesRepository(var notesDao: NotesDao){

     fun insertNote(notes: Notes){

        return notesDao.insertIt(notes)
    }
    fun updateNote(notes: Notes){
        return notesDao.updateIt(notes)
    }
    fun delteNote(id :Int) {
        return notesDao.deleteIt(id)
    }
    fun showNotes():LiveData<List<Notes>>{
        return notesDao.showAll()
    }
    fun showNotesRed():LiveData<List<Notes>>{
        return notesDao.showAllred()
    }

      fun showNotesOrg():LiveData<List<Notes>>{
        return notesDao.showAllorg()
    }

      fun showNotesYellow():LiveData<List<Notes>>{
        return notesDao.showAllyellow()
    }



}