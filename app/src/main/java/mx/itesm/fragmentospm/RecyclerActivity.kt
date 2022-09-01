package mx.itesm.fragmentospm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var datos : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        // el recycler es un widget que sirve para desplegar
        // muchos elementos con interfaz identica pero con tipos distintos

        // DATOS (abstracto) - Adapter(el traductor) - GUI (concreto)

        // DATOS
        datos = ArrayList()
        datos.add("Fido")
        datos.add("Fifi")
        datos.add("Firulais")
        datos.add("Solovino")

        // ADAPTER
        val adapter = PerritoAdapter(datos)

        // GUI
        recyclerView = findViewById(R.id.recyclerView)

        // es necesario utilizar un layout manager
        // layout manager es una clase que define cómo se van a desplegar
        // los items en el recyclerview
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.HORIZONTAL
        val glm = GridLayoutManager(this, 3)

        // terminamos asignando al recycler view referencias a objetos necesarios
        recyclerView.layoutManager = glm
        recyclerView.adapter = adapter
    }
}