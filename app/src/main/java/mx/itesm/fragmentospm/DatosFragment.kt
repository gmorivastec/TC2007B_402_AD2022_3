package mx.itesm.fragmentospm

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

class DatosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_datos, container, false)
    }

    fun saludar(context : Context) {

        Toast.makeText(
            context,
            "SALUDOS DESDE EL FRAGMENTO",
            Toast.LENGTH_SHORT
        ).show()
    }

}