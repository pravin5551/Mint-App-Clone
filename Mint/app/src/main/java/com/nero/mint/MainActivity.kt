package com.nero.mint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.nero.mint.data.remote.APiClient
import com.nero.mint.data.remote.RetrofitGenerator
import com.nero.mint.pojo.ResponseDTO
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var responseMainClass: List<ResponseDTO> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
        var apiClient = RetrofitGenerator.getInstance().create(APiClient::class.java)
        apiClient.getNews()
            .enqueue(object : Callback<List<ResponseDTO>> {
                override fun onResponse(
                    call: Call<List<ResponseDTO>>,
                    response: Response<List<ResponseDTO>>
                ) {
                    if (response.body() != null) {
                        responseMainClass = response.body()!!
//                        adapter.updateData(responseMainClass)
//
                    }
                }

                override fun onFailure(call: Call<List<ResponseDTO>>, t: Throwable) {
                }
            })
    }
}
