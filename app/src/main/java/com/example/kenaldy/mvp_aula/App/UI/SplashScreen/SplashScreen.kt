package com.example.kenaldy.mvp_aula.App.UI.SplashScreen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.kenaldy.mvp_aula.App.UI.FilmesDetalhes.FilmesDetalhesActivity
import com.example.kenaldy.mvp_aula.App.UI.Home.HomeActivity
import com.example.kenaldy.mvp_aula.R

import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {

    private var handler : Handler? = null

    internal val splashRunnable: Runnable = Runnable {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK)
        this.startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        var preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

        if(preferences.contains("ja_abriu")){
            mostrarHomeActvity()
        }
        else{
            adicionarPreferences(preferences)
            mostrarSplash()
        }
    }

    fun mostrarSplash(){
        handler = Handler()
        handler!!.postDelayed(splashRunnable, 3000)
    }

    fun adicionarPreferences(preferences: SharedPreferences){
        var editor = preferences.edit()
        editor.putBoolean("ja_abriu", true)
        editor.commit()
    }

    fun mostrarHomeActvity(){
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK)
        this.startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        if(handler != null ){
            handler!!.removeCallbacks(splashRunnable)
        }
        super.onDestroy()
    }

}
