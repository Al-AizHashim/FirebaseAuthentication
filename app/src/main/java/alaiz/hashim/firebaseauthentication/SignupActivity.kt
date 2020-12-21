package alaiz.hashim.firebaseauthentication



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class SignupActivity : AppCompatActivity() {
    lateinit var signupBtn: Button
    lateinit var signUpEmail: EditText
    lateinit var signUpPassword: EditText
    lateinit var mAuth: FirebaseAuth
    var email: String = ""
    var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        signUpEmail = findViewById(R.id.email_sign_up_ET)
        signUpPassword = findViewById(R.id.password_sign_up_ET)
        signupBtn = findViewById(R.id.sign_up_btn)

        signupBtn.setOnClickListener {
            email=signUpEmail.text.toString()
            password=signUpPassword.text.toString()
            registration()
        }
    }

    fun registration() {
        val singUpIntent = Intent(this, MainActivity::class.java)
        mAuth = FirebaseAuth.getInstance()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("createUser", "createUserWithEmail:success")
                    startActivity(singUpIntent)
                    //val user = mAuth.currentUser
                   // updateUI(user)
                } else {
                    Log.w(
                        "createUser","createUserWithEmail:failure", task.exception
                    )
                    Toast.makeText(
                        this, "Authentication failed.",Toast.LENGTH_SHORT).show()
                   // updateUI(null)
                }

            }


    }
}