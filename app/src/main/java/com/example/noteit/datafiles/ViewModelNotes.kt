package com.example.noteit.datafiles

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelNotes(application: Application): AndroidViewModel(application) {

    lateinit var repository: NotesRepository

    init {
        val dao = databaseNotes.getInstance(application)?.getDAO()
if(dao!=null){
    repository = NotesRepository(dao)
}

    }

    fun insertNote(notes: Notes){

        viewModelScope.launch(Dispatchers.IO) {
             repository.insertNote(notes)}

    }
    fun updateNote(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
             repository.updateNote(notes)}

    }
     fun delteNote(id :Int){
         viewModelScope.launch(Dispatchers.IO) {
         repository.delteNote(id)}

    }
    fun showNotes(): LiveData<List<Notes>> {
        return repository.showNotes()
    }

 fun showNotesred(): LiveData<List<Notes>> {
        return repository.showNotesRed()
    }

 fun showNotesOrg(): LiveData<List<Notes>> {
        return repository.showNotesOrg()
    }

 fun showNotesYellow(): LiveData<List<Notes>> {
        return repository.showNotesYellow()
    }









}