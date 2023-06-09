package xtremedesar.com.UI.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import xtremedesar.com.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {

    private lateinit var _Binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _Binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(_Binding.root)
        supportActionBar?.hide()

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        Handler().postDelayed(
            {
                startActivity(Intent(this, MenuPrincipal::class.java))
                finish()
            },
            5000
        )
    }

    override fun onBackPressed() {
    }
}