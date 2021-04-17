package com.nero.mint.fragments


import android.os.Bundle
import android.view.View
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nero.mint.R
import kotlinx.android.synthetic.main.fragment_explore.*


class ExploreFragment : Fragment(R.layout.fragment_explore) {

    lateinit var option: Spinner

    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        navController = Navigation.findNavController(view)


        btnEPaper.setOnClickListener(View.OnClickListener {
            val bottomSheetDialog = context?.let { it1 -> BottomSheetDialog(it1) }
            if (bottomSheetDialog != null) {
                bottomSheetDialog.setContentView(R.layout.e_paper_bottome_sheet_layout)
                bottomSheetDialog.setCanceledOnTouchOutside(false)

                bottomSheetDialog.show()

            }
        })

        btnMyRead.setOnClickListener {
            navController.navigate(R.id.action_explorefragment_to_bookmarkFragment)

        }

    }

}