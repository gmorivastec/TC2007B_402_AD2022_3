package mx.itesm.fragmentospm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private const val NOMBRE = "nombre"
private const val EDAD = "edad"

/**
 * A simple [Fragment] subclass.
 * Use the [PerritoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PerritoFragment : Fragment() {

    private var nombre: String? = null
    private var edad: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

            nombre = it.getString(NOMBRE)
            edad = it.getInt(EDAD)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_perrito, container, false)

        view.findViewById<TextView>(R.id.perritoNombre).apply {
            text = nombre
        }

        view.findViewById<TextView>(R.id.perritoEdad).apply {
            text = edad.toString()
        }

        return view
    }

    companion object {

        // método estático para creación de instancias
        // PROBLEMA - los fragmentos están obligados a tener un constructor
        // default (sin argumentos)
        // esto se vuelve problema cuando necesitamos argumentos!

        // factory
        // - https://en.wikipedia.org/wiki/Factory_method_pattern

        @JvmStatic
        fun newInstance(nombre: String, edad: Int) : PerritoFragment {

            val perrito = PerritoFragment()
            val datos = Bundle()
            datos.putString(NOMBRE, nombre)
            datos.putInt(EDAD, edad)
            perrito.arguments = datos
            return perrito
        }
        /*
         = PerritoFragment().apply {
                arguments = Bundle().apply {
                    putString(NOMBRE, nombre)
                    putInt(EDAD, edad)
                }
            }
         */
    }
}