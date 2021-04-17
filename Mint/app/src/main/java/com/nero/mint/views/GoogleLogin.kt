package com.nero.mint.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.nero.mint.Fragments.ProfileFragment
import com.nero.mint.R
import kotlinx.android.synthetic.main.activity_google_login.*


const val RC_SIGN_IN = 123

class GoogleLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_login)
        lateinit var navController: NavController


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()


        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso);




        btnGoogle.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent;
            startActivityForResult(signInIntent, RC_SIGN_IN);

            val acct = GoogleSignIn.getLastSignedInAccount(applicationContext)
            if (acct != null) {

//                val bundle = Bundle()
//                val name = acct.displayName
//                val email = acct.email

                val bundle = bundleOf("name" to acct.displayName, "email" to acct.email,"photo" to acct.photoUrl.toString())

                val fragment = ProfileFragment()
                val fragmentManager: FragmentManager = supportFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                fragment.arguments = (bundle)
                fragmentTransaction.replace(R.id.googleContainer, fragment,"fragment").addToBackStack("fragment").commit()

            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {

    }
}


//  val bundle = bundleOf(
//
//                )
//// set Fragmentclass Arguments
//// set Fragmentclass Arguments
//                val fragobj = Fragmentclass()
//                fragobj.setArguments(bundle)