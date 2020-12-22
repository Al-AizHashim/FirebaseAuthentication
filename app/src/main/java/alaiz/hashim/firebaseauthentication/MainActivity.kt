package alaiz.hashim.firebaseauthentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.app_bar_main.*

lateinit var drawerLayout: DrawerLayout
lateinit var navView: NavigationView
lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

class MainActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val toolbar: Toolbar = findViewById(R.id.toolbar)


        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)


        actionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                drawerView.id
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }

        }
        actionBarDrawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.starred -> {
                    callFragment(R.id.starred, "Starred Fragment")
                    true
                }
                R.id.recent -> {
                    callFragment(R.id.recent, "Recent Fragment")
                    true
                }
                R.id.upload -> {
                    callFragment(R.id.upload, "Upload Fragment")
                    true
                }
                R.id.signout -> {
                    mAuth = FirebaseAuth.getInstance()
                    mAuth.signOut()
                    finish()
                    val intent=Intent(this , LoginActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> {
                    false
                }
            }
        }


    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    fun callFragment(itemId: Int, title: String) {
        val fragment = StarredFragment.newInstance("You are in $title", "")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, fragment).addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        toolbar.setTitle(title)
        drawerLayout.closeDrawer(GravityCompat.START)
        Toast.makeText(this, "$title is opened", Toast.LENGTH_LONG).show()
    }

    fun signOut(context: Context): Task<Void?>? {
        return Tasks.whenAll(
        ).continueWith { task ->
            task.result // Propagate exceptions
            mAuth.signOut()
            null
        }
    }
}