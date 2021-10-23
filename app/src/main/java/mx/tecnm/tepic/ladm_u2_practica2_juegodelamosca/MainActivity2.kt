package mx.tecnm.tepic.ladm_u2_practica2_juegodelamosca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var extra = intent.extras!!
        var mensajeFinal = extra.getString("Resultado")

        txtResultado.text = "${mensajeFinal}"

        val ventana3 = Intent(this, MainActivity3::class.java)
        btnIniciar.setOnClickListener {
            var nMoscas = Math.random().toInt() + 80
            ventana3.putExtra("numeroMoscas",nMoscas)
            startActivity(ventana3)
        }

    }
    }
