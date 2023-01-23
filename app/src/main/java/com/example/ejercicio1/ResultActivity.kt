package com.example.ejercicio1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val btnVuelve: Button = findViewById(R.id.btnVolver)
        btnVuelve.setOnClickListener {vuelve()}
        recibirDatos()


    }

    fun recibirDatos(){

        val textResult: TextView = findViewById(R.id.textResultado)
        val bundle= intent.extras
        val name= bundle?.get("INTENT_NAME")
        textResult.text="Bienvenido $name"

    }


    private fun vuelve(){

        val intent= Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        finish()
        startActivity(intent)



    }
}