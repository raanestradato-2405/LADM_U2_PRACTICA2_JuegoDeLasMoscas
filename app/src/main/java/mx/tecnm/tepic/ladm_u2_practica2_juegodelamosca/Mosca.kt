package mx.tecnm.tepic.ladm_u2_practica2_juegodelamosca
import android.graphics.*


class Mosca(l:Lienzo,ima:Int) {

    var x = 0f
    var y = 0f
    var movX = 0
    var movY = 0
    var desplazamiento = 0
    var imagen = BitmapFactory.decodeResource(l.resources, ima)
    var moscaAp = 0
    var clicAplastar = true

    init {
        x = (Math.random() * 2100).toFloat()
        y = (Math.random() * 1000).toFloat()
        movX = (Math.random() * 5).toInt()
        movY = (Math.random() * 5).toInt()
    }

    fun moverMoscas() {
        if (moscaAp == 0) {
            if (movX == 1) {
                x += (Math.random() * 5).toInt() + 5
            } else {
                x -= (Math.random() * 5).toInt() + 5
            }
            if (movY == 1) {
                y += (Math.random() * 5).toInt() + 5
            } else {
                y -= (Math.random() * 5).toInt() + 5
            }
            if (y > 1500) movY = 2

            if (y < 0) movY = 1

            if (x > 1200) movX = 2

            if (x < 0) movX = 1

            desplazamiento++

            if (desplazamiento == 1500) {
                movX = (Math.random() * 2).toInt()
                movY = (Math.random() * 2).toInt()
                desplazamiento = 0
            }
        }
    }

    fun estaenArea(toqueX: Float, toqueY: Float): Boolean {

        var x2 = x + imagen.width
        var y2 = y + imagen.height

        if (toqueX >= x && toqueX <= x2 && clicAplastar) {
            if (toqueY >= y && toqueY <= y2 && clicAplastar) {
                clicAplastar = false
                return true
            }

        }
        return false
    }

    fun aplastarMosca() {
        moscaAp = 1
    }
}