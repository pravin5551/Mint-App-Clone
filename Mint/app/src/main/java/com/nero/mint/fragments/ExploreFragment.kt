package com.nero.mint.fragments


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nero.mint.R
import com.nero.mint.views.MainActivity
import com.nero.mint.views.SettingsActivity
import kotlinx.android.synthetic.main.fragment_explore.*


class ExploreFragment : Fragment(R.layout.fragment_explore){
    lateinit var option :Spinner



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


//            val bottomSheetFragment = Intent()
//            startActivity(bottomSheetFragment)
//            val nextFrag = BottomSheetFragment()
//            activity!!.supportFragmentManager.beginTransaction()
//                .replace(R.id.e_paper, nextFrag, "findThisFragment")
//                .addToBackStack(null)
//                .commit()
////            bottomSheetFragment.show()


            btnEPaper.setOnClickListener(View.OnClickListener {
                val bottomSheetDialog = context?.let { it1 -> BottomSheetDialog(it1) }
                if (bottomSheetDialog != null) {
                    bottomSheetDialog.setContentView(R.layout.e_paper_bottome_sheet_layout)
                    bottomSheetDialog.setCanceledOnTouchOutside(false)

                    bottomSheetDialog.show()

                }
            })
        btnsettings.setOnClickListener { View.OnClickListener {
            val intent = Intent(view.context, SettingsActivity::class.java)
            view.context.startActivity(intent)
        } }


    }
}