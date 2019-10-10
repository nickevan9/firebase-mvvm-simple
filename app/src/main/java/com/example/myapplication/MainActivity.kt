package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mNoteAdapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firestoreViewModel = ViewModelProviders.of(this)
            .get(NoteViewmodel::class.java)
        mNoteAdapter = NoteAdapter()

        val linearLayoutManager = LinearLayoutManager(this)

        rvList.layoutManager = linearLayoutManager
        rvList.setHasFixedSize(true)
        rvList.adapter = mNoteAdapter


        firestoreViewModel.getNote().observe(this, Observer {
            mNoteAdapter.swapData(it)
        })

        btnAdd.setOnClickListener {

            for (i in 0..10){
                firestoreViewModel.saveNote(Note(i, "hung $i", "skjadfaskjfddsa", "ijidsfndvnurenu3243"))
            }

        }


    }
}
