package phuchh.sunasterisk.moviedb_41.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import phuchh.sunasterisk.moviedb_41.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        startActivity(MainActivity.getIntent(this))
        finish()
    }
}
