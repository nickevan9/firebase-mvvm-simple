package com.example.myapplication

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Created by nickevan on 10,October,2019
 */
class NoteRepository {
    val TAG = "FIREBASE_REPOSITORY"
    var firestoreDB = FirebaseFirestore.getInstance()

    fun saveNoteItem(note: Note): Task<Void> {
        var documentReference =
            firestoreDB.collection("note").document(note.id.toString())

        return documentReference.set(note)
    }

    fun getNote(): CollectionReference {
        return firestoreDB.collection("note")
    }

    fun deleteNote(note: Note): Task<Void> {
        var documentReference = firestoreDB.collection("note").document(note.id.toString())
        return documentReference.delete()
    }
}