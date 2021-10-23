package mx.tecnm.tepic.ladm_u2_practica2_juegodelamosca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        var extra = intent.extras!!
        var moscas = extra.getInt("numeroMoscas")

        setContentView(Lienzo(this,moscas))
    }
}