package com.sanjeet.weatherinfo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.sanjeet.weatherinfo.R
import com.sanjeet.weatherinfo.databinding.SpaslScreenBinding

class SplashScreen : AppCompatActivity() {


    lateinit var binding:SpaslScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SpaslScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val ttb = AnimationUtils.loadAnimation(this, R.anim.ttb)
        binding.tvTop.startAnimation(ttb)
        binding.tvDesc.startAnimation(ttb)
        val stb = AnimationUtils.loadAnimation(this, R.anim.stb)
        binding.img1.startAnimation(stb)
        val btt = AnimationUtils.loadAnimation(this, R.anim.btt)
        binding.img2.startAnimation(btt)
        val btt2 = AnimationUtils.loadAnimation(this, R.anim.btt2)
        binding.img3.startAnimation(btt2)



        val splashTread = object : Thread() {
            override fun run() {
                try {
                    var waited = 0
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100)
                        waited += 100
                    }
                    val intent = Intent(
                        this@SplashScreen,
                        MainActivity::class.java
                    )
                    intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                    startActivity(intent)
                    this@SplashScreen.finish()
                } catch (e: InterruptedException) {
                    // do nothing
                } finally {
                    this@SplashScreen.finish()
                }
            }
        }
        splashTread.start()
    }
}