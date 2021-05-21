package com.hangyeong.mynote

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class NoteActivity : AppCompatActivity() {
    private val handler = Handler(Looper.getMainLooper())
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.actionbar_action, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.password_change) {
            startActivity(Intent(this, PasswordActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

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