package alaiz.hashim.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    lateinit var loginBtn:Button
    lateinit var signUpTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginBtn=findViewById(R.id.login_button1)
        signUpTextView=findViewById(R.id.sing_up_text_view)
        signUpTextView.setOnClickListener{
        val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }


}