package com.example.noteit.datafiles

import android.webkit.WebSettings.RenderPriority
import androidx.room.Entity
import androidx.room.PrimaryKey

import android.os.Parcel
import android.os.Parcelable


@Entity(tableName = "NotesTable")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val Title: String,
    val subTitle: String,
    val note: String,
    val DateToday: String,
    val priority: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(Title)
        parcel.writeString(subTitle)
        parcel.writeString(note)
        parcel.writeString(DateToday)
        parcel.writeString(priority)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Notes> {
        override fun createFromParcel(parcel: Parcel): Notes {
            return Notes(parcel)
        }

        override fun newArray(size: Int): Array<Notes?> {
            return arrayOfNulls(size)
        }
    }
}
