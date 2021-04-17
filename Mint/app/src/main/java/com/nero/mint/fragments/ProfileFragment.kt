package com.nero.mint.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.nero.mint.R
import com.nero.mint.views.MainActivity
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(R.layout.fragment_profile) {


    lateinit var navController: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvName.text = arguments?.getString("name")
        tvEmail.text = arguments?.getString("email")
        Glide.with(ivProfile).load(arguments?.getString("photo")).into(ivProfile)


        ivclose.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)

        }
    }


}