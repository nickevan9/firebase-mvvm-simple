package com.example.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

/**
 * Created by nickevan on 10,October,2019
 */

class NoteViewmodel : ViewModel() {
    val TAG = "FIRESTORE_VIEW_MODEL"
    var noteRepository = NoteRepository()
    var notes: MutableLiveData<List<Note>> = MutableLiveData()

    fun saveNote(note: Note) {
        noteRepository.saveNoteItem(note).addOnFailureListener {
            Log.d(TAG, "Failed to save note")
        }
    }

    fun getNote(): LiveData<List<Note>> {
        noteRepository.getNote().addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                notes.value = null
                return@EventListener
            }

            var noteList: MutableList<Note> = mutableListOf()
            for (doc in value!!) {
                var note = doc.toObject(Note::class.java)
                noteList.add(note)
            }
            notes.value = noteList
        })
        return notes
    }

    fun deleteNote(note: Note) {
        noteRepository.deleteNote(note).addOnFailureListener {
            Log.e(TAG, "Failed to delete note")
        }
    }
}