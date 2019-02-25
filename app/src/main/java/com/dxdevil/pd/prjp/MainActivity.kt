package com.dxdevil.pd.prjp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val background = object : Thread() {
            override fun run() {

                try {
                    // Thread will sleep for 5 seconds
                    Thread.sleep(2000)

                    // After 5 seconds redirect to another intent
                    val i = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(i)
                    //Remove activity
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
        // start thread
        background.start()
    }
}


            var backg = object : Thread(){
                override fun run() {
                    super.run()
                    Thread.sleep(1000)
                startActivity(Intent(applicationContext,LoginActivity::class.java))
                }
            }
            }
    }
