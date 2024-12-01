package hu.bme.aut.android.receptes_konyv

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import hu.bme.aut.android.receptes_konyv.ui.animation.LoadingCirlce
import hu.bme.aut.android.receptes_konyv.ui.theme.Receptes_KonyvTheme
import java.util.Timer
import java.util.TimerTask

class LoadingScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Receptes_KonyvTheme {
                Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background), contentAlignment = Alignment.Center){
                    LoadingCirlce(color= MaterialTheme.colorScheme.primary, modifier = Modifier.background(
                        MaterialTheme.colorScheme.background))
                }
            }
        }
        var timer= Timer()
        val timeout: Long = 4000
        timer.schedule(object : TimerTask() {
            override fun run() {
                intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, timeout)
    }


}