package com.hangyeong.mynote

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class NoteActivity : AppCompatActivity() {
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val noteEditText = findViewById<EditText>(R.id.noteTextView)
        val notePreferences = getSharedPreferences("Note", Context.MODE_PRIVATE)

        noteEditText.setText(notePreferences.getString("Content", ""))

        val runnable = Runnable {
            notePreferences.edit {
                putString("Content", noteEditText.text.toString())
            }
        }

        noteEditText.addTextChangedListener {
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 500)
        }
    }
}