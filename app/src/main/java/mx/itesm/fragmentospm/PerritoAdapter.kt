package mx.itesm.fragmentospm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PerritoAdapter(var perritos : ArrayList<String>)
    : RecyclerView.Adapter<PerritoAdapter.PerritoViewHolder>() {

    // 1era cosa que hay que hacer -
    // ViewHolder
    // ViewHolder es similar al binding: un objeto con referencias a los elementos de una vista

    class PerritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var texto1 : TextView
        var texto2 : TextView
        var boton : Button

        // bloque de inicializacion
        // bloque de código que se corre TODAS las veces que se crea un objeto
        init {

            texto1 = itemView.findViewById(R.id.rowText1)
            texto2 = itemView.findViewById(R.id.rowText2)
            boton = itemView.findViewById(R.id.rowButton)
        }

    }

    // momento de creación de una vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerritoViewHolder {

        // igual que con fragmentos inflamos la view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row, parent, false)

        return PerritoViewHolder(view)
    }

    // momento de asociación de vista con datos
    override fun onBindViewHolder(holder: PerritoViewHolder, position: Int) {
        holder.texto1.text = perritos[position]
        holder.texto2.text = perritos[position]
    }

    // obtener la cantidad de datos a mostrar
    override fun getItemCount(): Int {
        return perritos.size
    }
}