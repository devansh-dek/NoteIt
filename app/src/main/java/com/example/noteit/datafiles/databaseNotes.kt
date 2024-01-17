package com.example.noteit.datafiles

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Notes::class],version = 1)
abstract class databaseNotes: RoomDatabase() {

    abstract fun getDAO() : NotesDao


   companion object{

       @Volatile
       var INSTANCE : databaseNotes?=null

       fun getInstance(context : Context): databaseNotes? {
            val tempinst = INSTANCE

        if(tempinst!=null){
         return tempinst

        }

           synchronized(this){
               val tempoINST = Room.databaseBuilder(context ,databaseNotes::class.java,"NotesTable").build()
INSTANCE = tempoINST
return return tempoINST
           }


       }








   }






}