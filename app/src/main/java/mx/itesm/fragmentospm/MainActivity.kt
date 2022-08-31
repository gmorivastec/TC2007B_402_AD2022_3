package mx.itesm.fragmentospm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    lateinit var datosFragment: DatosFragment
    lateinit var perritoFragment: PerritoFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // los fragmentos se pueden agreagr por medio de la GUI
        // O por medio de código

        // vamos a agregar fragmentos progamaticamente (programmatically)
        datosFragment = DatosFragment()
        perritoFragment = PerritoFragment.newInstance("FIFI", 5)

        // cómo se agrega a GUI
        // existe el fragmentManager que es el que se encarga de operaciones
        // de administración de fragmentos
        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.fragmentContainerView, datosFragment, TAG)

        // sin esto las cosas nunca suceden
        transaction.commit()
    }

    fun swap(view : View?) {

        // vamos a intercambiar fragmentos

        // obtener referencia al fragmento actualmente cargado
        val fragmentoActual = supportFragmentManager.findFragmentByTag(TAG)

        if(fragmentoActual != null){

            // obtener referencia al nuevo fragmento
            // polimorfismo - un objeto de un tipo puede representar
            // a un objeto de un subtipo
            var fragmentoNuevo : Fragment = perritoFragment

            if(fragmentoActual == perritoFragment)
                fragmentoNuevo = datosFragment

            val transaction = supportFragmentManager.beginTransaction()

            // 2 opciones
            // 1 - quitar y poner (no es la práctica)
            /*
            transaction.remove(fragmentoActual)
            transaction.add(R.id.fragmentContainerView, fragmentoNuevo, TAG)
             */

            // 2 - hacerlo con replace
            transaction.replace(R.id.fragmentContainerView, fragmentoNuevo, TAG)
            transaction.commit()
        }
    }

    fun saludar(view : View?) {

        datosFragment.saludar(this)
    }

    companion object {

        private const val TAG = "fragmentito"
    }
}
