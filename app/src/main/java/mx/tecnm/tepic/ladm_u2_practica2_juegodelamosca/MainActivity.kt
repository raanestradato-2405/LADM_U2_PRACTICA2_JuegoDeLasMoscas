package mx.tecnm.tepic.ladm_u2_practica2_juegodelamosca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciarAplicacion()
    }
    fun iniciarAplicacion(){
        object : CountDownTimer(3000,1000){
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                val ventana2 = Intent(applicationContext, MainActivity2::class.java).apply {}
                ventana2.putExtra("Resultado", " ")
                startActivity(ventana2)
            }
        }.start()
    }
}