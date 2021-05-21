package com.hangyeong.mynote

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit

class PasswordActivity : AppCompatActivity() {
    private val passwordChange : Button by lazy {
        findViewById<Button>(R.id.passwordChange)
    }

    private val currentPasswordInput : EditText by lazy {
        findViewById<EditText>(R.id.currentPassword)
    }

    private val changePasswordInput : EditText by lazy {
        findViewById<EditText>(R.id.changePassword)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        passwordChange.setOnClickListener {
            val changeResult = passwordChanger(currentPasswordInput.text.toString(), changePasswordInput.text.toString())
            if (changeResult) {
                Toast.makeText(this, "비밀번호 변경 완료!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "비밀번호 변경 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 저장이 안된다?
    private fun passwordChanger(pwd1 : String, pwd2 : String): Boolean {
        val passwordPreferences = getSharedPreferences("Password", Context.MODE_PRIVATE)
        val password = passwordPreferences.getString("password", "0000")
        if (password.equals(pwd1)) {
            passwordPreferences.edit {
                putString("password", pwd2)
                apply()
            }
            return true
        }
        return false
    }
}