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
            if (changeResult == 1) {
                Toast.makeText(this, "비밀번호 변경 완료!", Toast.LENGTH_SHORT).show()
                finish()
            } else if (changeResult == 2) {
                Toast.makeText(this, "이전 비밀번호와 변경 비밀번호가 같습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "비밀번호 변경 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun passwordChanger(pwd1 : String, pwd2 : String): Int {
        val passwordPreferences = getSharedPreferences("Password", Context.MODE_PRIVATE)
        val password = passwordPreferences.getString("password", "0000")
        if (password.equals(pwd1)) {
            if (pwd1 == pwd2) {
                return 2 // 패스워드와 입력받은 패스워드는 같으나, 기존 == 변경일때.
            }
            passwordPreferences.edit {
                putString("password", pwd2)
                apply()
            }
            return 1 // 패스워드와 입력받은 패스워드가 같을때, 변경 성공.
        }
        return 0 // 기존 패스워드를 다르게 입력했을때.
    }
}