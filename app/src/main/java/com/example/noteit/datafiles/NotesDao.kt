package com.example.noteit.datafiles

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIt(notes : Notes)

    @Update
     fun updateIt(notes : Notes)

    @Query("SELECT * FROM NotesTable")
    fun showAll(): LiveData<List<Notes>>

    @Query("SELECT * FROM NotesTable WHERE priority = 1")
    fun showAllred(): LiveData<List<Notes>>

    @Query("SELECT * FROM NotesTable WHERE priority = 2")
    fun showAllorg(): LiveData<List<Notes>>

    @Query("SELECT * FROM NotesTable WHERE priority = 3")
    fun showAllyellow(): LiveData<List<Notes>>

    @Query("DELETE FROM NotesTable WHERE id = :id")
     fun deleteIt(id: Int)

}