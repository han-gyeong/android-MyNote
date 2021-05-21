package com.hangyeong.mynote

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private val numberButton : List<Button> by lazy {
        listOf(findViewById<Button>(R.id.number0),
        findViewById<Button>(R.id.number1),
        findViewById<Button>(R.id.number2),
        findViewById<Button>(R.id.number3),
        findViewById<Button>(R.id.number4),
        findViewById<Button>(R.id.number5),
        findViewById<Button>(R.id.number6),
        findViewById<Button>(R.id.number7),
        findViewById<Button>(R.id.number8),
        findViewById<Button>(R.id.number9)
    )}

    private val numberView : List<TextView> by lazy { listOf(
        findViewById<TextView>(R.id.TextView1),
        findViewById<TextView>(R.id.TextView2),
        findViewById<TextView>(R.id.TextView3),
        findViewById<TextView>(R.id.TextView4)
    )}

    private val resetButton : Button by lazy {
        findViewById<Button>(R.id.resetButton)
    }

    private val loginButton : Button by lazy {
        findViewById<Button>(R.id.loginButton)
    }

    private var clickedNumber = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in numberButton.indices) {
            numberButton[i].setOnClickListener {
                if (clickedNumber.size > 3) {
                    Toast.makeText(this, "숫자는 4개까지만 선택이 가능합니다.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                numberView[clickedNumber.size].text = i.toString()
                clickedNumber.add(i)
            }
        }

        resetButton.setOnClickListener {
            resetInput()
        }

        loginButton.setOnClickListener {
            val passwordPreferences = getSharedPreferences("Password", Context.MODE_PRIVATE)
            val password = passwordPreferences.getString("password", "0000")
            val passwordInput = "${numberView[0].text}${numberView[1].text}${numberView[2].text}${numberView[3].text}"
            if (password == passwordInput) {
                startActivity(Intent(this, NoteActivity::class.java))
            } else {
                //Toast.makeText(this, "Password is incorrect!", Toast.LENGTH_SHORT).show()
                //return@setOnClickListener
                negativeAlert()
                resetInput()
                }
            }
        }

    private fun negativeAlert() {
        AlertDialog.Builder(this)
            .setTitle("Login Failed")
            .setMessage("Password is incorrect!")
            .setPositiveButton("확인", null)
            .create()
            .show()
    }

    private fun resetInput() {
        clickedNumber.clear()
        numberView.forEach {
            it.text = getString(R.string.default_value)
            }
        }
    }


