package com.example.ejercicio1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RestablecerPass : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restablecer_pass)

        val botonEnviar: Button = findViewById(R.id.btnRestablecer)
        val editCorreo: TextView=findViewById(R.id.editCorreo)

        botonEnviar.setOnClickListener {enviarCorreo(editCorreo.text.toString())}
        firebaseAuth= Firebase.auth
    }


    private fun enviarCorreo(correo:String){

        firebaseAuth.sendPasswordResetEmail(correo)
            .addOnCompleteListener(){task ->
                if(task.isSuccessful){

                    Toast.makeText(this, "Se envió correo", Toast.LENGTH_SHORT).show()

                    val intent= Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    finish()
                    startActivity(intent)

                }else{

                    Toast.makeText(this, "Algo salio mal. Error: "+task.exception, Toast.LENGTH_SHORT).show()

                }

            }



    }

}