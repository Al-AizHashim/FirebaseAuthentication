package alaiz.hashim.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var loginBtn:Button
    lateinit var emailLoginTextView: EditText
    lateinit var passwordLoginTextView: EditText
    lateinit var signUpTextView:TextView
    lateinit var mAuth: FirebaseAuth
    var email: String = ""
    var password: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        signUpTextView=findViewById(R.id.sing_up_text_view)
        loginBtn=findViewById(R.id.login_button1)
        emailLoginTextView=findViewById(R.id.email_login_ET)
        passwordLoginTextView=findViewById(R.id.passwordET)
        signUpTextView.setOnClickListener{
        val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        loginBtn.setOnClickListener{
            email=emailLoginTextView.text.toString()
            password=passwordLoginTextView.text.toString()
            login()
        }
    }
    fun login() {
        val singinIntent = Intent(this, MainActivity::class.java)
        mAuth = FirebaseAuth.getInstance()
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("logginUser", "loggin UserWithEmail:success")
                    startActivity(singinIntent)

                } else {
                    Log.w(
                        "logginUser","login UserWithEmail:failure", task.exception
                    )
                    Toast.makeText(
                        this, "Authentication failed.",Toast.LENGTH_LONG).show()
                }

            }


    }


}