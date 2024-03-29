@file:Suppress("DEPRECATION")

package com.sample.demo.myapplication

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    private lateinit var infoViewModel: InfoViewModel
    private lateinit var infoFactory: InfoFactory
    private lateinit var infoRepository: InfoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        infoRepository = InfoRepository()
        infoFactory = InfoFactory(infoRepository)
        infoViewModel = ViewModelProviders.of(this, infoFactory).get(InfoViewModel::class.java)
        val dialog = ProgressDialog.show(
            this, "",
            "Loading. Please wait...", true
        )
        dialog.hide()
        infoViewModel.userInfoLiveData.observe(this, Observer {
            info.text = it
            if(dialog.isShowing){
                dialog.hide()
            }
        })
        send_data.setOnClickListener {
            dialog.show()
            infoViewModel.callInfo()
        }
    }
}
