package com.example.listapp.mainModule

/*
Una pantalla donde muestra una lista. Arriba, en el navigation,
hay un boton con un + Cada vez que se pulsa en ese boton, se inserta una celda con contiene un numero consecutivo.
Tiene que ser el que corresponda por su orden. Si pulsamos sobre una celda, el valor que tenga esa celda la multiplicamos x 2.
Por ejemplo, le das 3 veces al mas, tiene que aparece 1, 2 y 3. Pulsamos sobre la celda 2, deberia aparece 1, 4, 3.
Le damos al 3, deberia aparece 1, 4, 6. Le damos al +, deberia aparecer 1,4,6,4
*/

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.listapp.R
import com.example.listapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var listNumbers: MutableList<Int>

    private var currentValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listNumbers = mutableListOf()
        binding.txtThereIsNotElements.visibility = View.VISIBLE
        binding.btnRemoveList.visibility = View.GONE

        addListView()
        binding.imageAddNumber.setOnClickListener {
            addNumber()
            addListView()
        }
        binding.btnRemoveList.setOnClickListener {
            listNumbers.clear()
            addListView()
            binding.btnRemoveList.visibility = View.GONE
            binding.txtThereIsNotElements.visibility = View.VISIBLE
        }

    }

    private fun addListView(){

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listNumbers)
        binding.listViewNumbers.adapter = adapter

        binding.listViewNumbers.setOnItemClickListener { adapterView: AdapterView<*>, view: View, position: Int, l: Long ->
            currentValue= listNumbers[position]
            if (currentValue != 0){
                itemSelected(currentValue, position)
                addListView()
                currentValue = 0
            }
        }
    }

    private fun itemSelected(currentValue : Int, position: Int){
        listNumbers.removeAt(position)
        listNumbers.add(position,currentValue * 2)
    }

    private fun addNumber(){
        binding.txtThereIsNotElements.visibility = View.GONE
        listNumbers.add(listNumbers.size+1)
        binding.btnRemoveList.visibility = View.VISIBLE

    }
}