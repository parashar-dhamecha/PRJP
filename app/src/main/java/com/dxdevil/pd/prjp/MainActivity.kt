package com.dxdevil.pd.prjp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            var backg = object : Thread(){
                override fun run() {
                    super.run()
                    Thread.sleep(1000)
                startActivity(Intent(applicationContext,LoginActivity::class.java))
                }
            }
            }
    }

