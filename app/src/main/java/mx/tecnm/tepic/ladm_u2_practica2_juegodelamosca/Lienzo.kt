package mx.tecnm.tepic.ladm_u2_practica2_juegodelamosca

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class Lienzo(p: MainActivity3, numeroMoscas : Int ): View(p){
    val principal = p
    val nMoscas = numeroMoscas
    val moscaimagen = BitmapFactory.decodeResource(principal.resources, R.drawable.mosca)
    val splash = BitmapFactory.decodeResource(principal.resources, R.drawable.splash)
    val hiloMoverMoscas = moviminetoMoscas(this, nMoscas)
    val hiloEmpezarJuegoNuevo = tiempoPartida()
    var aplastadas = 0
    var bandera = false

    init {
        hiloMoverMoscas.start()//hilo para mover moscas
        hiloEmpezarJuegoNuevo.start()//hilo para contar el tiempo
    }//init

    override fun onDraw(c: Canvas){
        super.onDraw(c)

        (0..(nMoscas-1)).forEach{

            if((hiloEmpezarJuegoNuevo.contador==59 || aplastadas==nMoscas) && bandera==false) {
                hiloMoverMoscas.detenerJuego()
                bandera =true
                val ventana2 = Intent(principal, MainActivity2::class.java)

                if(aplastadas==nMoscas){
                    ventana2.putExtra("Resultado", "GANADOR!!!")

                }else{
                    ventana2.putExtra("Resultado", "PERDEDOR!!")

                }
                principal.startActivity(ventana2)
            }else{

                if (hiloMoverMoscas.mosca[it].moscaAp == 1) {
                    c.drawBitmap(splash, hiloMoverMoscas.mosca[it].x, hiloMoverMoscas.mosca[it].y, Paint())
                } else {
                    c.drawBitmap(moscaimagen, hiloMoverMoscas.mosca[it].x, hiloMoverMoscas.mosca[it].y, Paint())
                }
            }
        }
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        super.onTouchEvent(e)
        val accion = e.action

        when(accion){
            MotionEvent.ACTION_DOWN->{
                (0..(nMoscas-1)).forEach{
                    if(hiloMoverMoscas.mosca[it].estaenArea(e.x,e.y)){
                        hiloMoverMoscas.mosca[it].aplastarMosca()
                        aplastadas++
                    }
                }
            }
        }
        invalidate()
        return true
    }
}


class moviminetoMoscas(p:Lienzo, cantidadMoscas:Int):Thread(){
    val puntero = p
    val mosca = ArrayList<Mosca>()
    val canMoscas = cantidadMoscas
    var detener = true

    fun detenerJuego(){
        detener = false
    }

    init{
        (1..canMoscas).forEach {
            val moscas = Mosca(p,R.drawable.mosca)
            mosca.add(moscas)
        }
    }

    override fun run(){
        super.run()
        while(detener) {
            (0..(canMoscas-1)).forEach{
                mosca[it].moverMoscas()
            }
            puntero.principal.runOnUiThread {
                puntero.invalidate()
            }
            sleep(80)
        }
    }
}

class tiempoPartida():Thread(){
    var contador = 0
    override fun run(){
        super.run()
        while(contador<60) {
            contador++
            sleep(1000)
        }
    }
}