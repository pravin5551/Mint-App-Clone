//package com.nero.mint.fragments;
//
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.material.bottomsheet.BottomSheetDialog;
//import com.nero.mint.R;
//
//public class lol extends AppCompatActivity {
//    Button btShow, btndialogue;
//
//    @Override
//    protected void onCreate( Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lol);
//
//        btShow = findViewById(R.id.btShow);
//
//        btShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(lol.this);
//                bottomSheetDialog.setContentView(R.layout.e_paper_bottome_sheet_layout);
//                bottomSheetDialog.setCanceledOnTouchOutside(false);
//
//
//                btndialogue= findViewById(R.id.btdialogue);
//                btndialogue.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//                        builder.setTitle("Login Successful");
//                        builder.setMessage("Welcome to Android Coding. ");
//
//                        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                        AlertDialog alertDialog = builder.create();
//                    }
//                });
//                bottomSheetDialog.show();
//            }
//        });
//
//
//
//    }
//}
