package mx.itesm.fragmentospm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class RecyclerActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "request"
    private lateinit var queue: RequestQueue
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
        val adapter = PerritoAdapter(datos,this)

        // GUI
        recyclerView = findViewById(R.id.recyclerView)

        // es necesario utilizar un layout manager
        // layout manager es una clase que define cómo se van a desplegar
        // los items en el recyclerview
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        val glm = GridLayoutManager(this, 3)

        // terminamos asignando al recycler view referencias a objetos necesarios
        recyclerView.layoutManager = llm
        recyclerView.adapter = adapter

        // JSON?
        // Javascript object notation
        // - su uso es modelar información

        // los documentos pueden estar definidos por 2 tipos de contenedor
        // - objetos - {}
        // - arreglos - []

        val jsonTest = "{'nombre': 'Juan', 'edad': 20}"
        val jsonTest2 = "{'nombre': 'Pedro', 'calificaciones': [80, 70, 50, 90]}"
        val jsonTest3 = "[{'nombre': 'Juan', 'edad': 20}, {'nombre': 'Pedro', 'edad': 19}, {'nombre': 'Ana', 'edad': 21}]"

        // parsing - interpretación de texto con resultado en objeto de kotlin
        // parser - librería / lógica / código que se encarga de esta traducción
        try {

            val objeto = JSONObject(jsonTest)
            Log.wtf("JSON", objeto.getString("nombre"))
            Log.wtf("JSON", objeto.getInt("edad").toString())

            val objeto2 = JSONObject(jsonTest2)
            val calificaciones = objeto2.getJSONArray("calificaciones")

            Log.wtf("JSON", objeto2.getString("nombre"))
            for(i in 0 until calificaciones.length()){

                Log.wtf("JSON", calificaciones.getInt(i).toString())
            }

            val objeto3 = JSONArray(jsonTest3)

            for(i in 0 until objeto3.length()){

                val actual = objeto3.getJSONObject(i)
                Log.wtf("JSON", actual.getString("nombre"))
                Log.wtf("JSON", actual.getInt("edad").toString())
            }


        } catch(e: JSONException){
            e.printStackTrace()
        }

        // HACIENDO REQUESTS CON VOLLEY
        // Looper
        // los requests son asíncronos
        // que diablos significa asíncrono?!

        // hagamos el queue
        queue = Volley.newRequestQueue(this)

        var url = "https://www.google.com"

        var stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                Toast.makeText(
                    this,
                    "response: $response",
                    Toast.LENGTH_SHORT
                ).show()
            },
            { error ->
                Toast.makeText(
                    this,
                    "error: $error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ).apply {
            tag = TAG
        }

        // se puede hacer request directamente con json

        url = "https://bitbucket.org/itesmguillermorivas/partial2/raw/992b45809954609ff8521e14f8f70f359c68a3ea/videojuegos.json"
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            { response ->

                // procesar lógica del json array
                for(i in 0 until response.length()){

                    val actual = response.getJSONObject(i)
                    Log.wtf("JSON-REQUEST", actual.getString("nombre"))
                    Log.wtf("JSON-REQUEST", actual.getString("anio"))
                    Log.wtf("JSON-REQUEST", actual.getString("imagen"))

                    val plataformas = actual.getJSONArray("plataformas")
                    for(j in 0 until plataformas.length()){

                        Log.wtf("JSON-REQUEST", plataformas.getString(j))

                    }
                }
            },
            { error->
                Toast.makeText(
                    this,
                    "error: $error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ).apply{
            tag = TAG
        }

        queue.add(jsonArrayRequest)
    }

    override fun onStop() {
        super.onStop()

        // si hacemos stop lo ideal es detener la queue
        queue.cancelAll(TAG)
    }

    // NOTA DE ESTE MÉTODO:
    // el argumento que recibimos es el widget que mando llamar a este método
    override fun onClick(row : View) {

        // tenemos un sólo escucha para todos los rows!
        // podemos obtener la ubicación de una row con referencia a la vista
        val position = recyclerView.getChildLayoutPosition(row)
        Toast.makeText(
            this,
            datos[position],
            Toast.LENGTH_SHORT
        ).show()
    }
}