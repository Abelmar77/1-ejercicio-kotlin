package com.example.ejercicio1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CreateUser : AppCompatActivity(){

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        val botonCrear: Button = findViewById(R.id.btnCrearUser)

        botonCrear.setOnClickListener {createUser()}
        firebaseAuth= Firebase.auth

    }


    private fun createUser(){

        val edtCorreo: EditText = findViewById(R.id.creaCorreo)
        val edtPassword: EditText = findViewById(R.id.creaPassword)
        val edtusuario: EditText = findViewById(R.id.editNombreUsuario)
        val edtConfirmaPassword: EditText = findViewById(R.id.editConfirmarPassword)


        if (edtPassword.text.toString()==edtConfirmaPassword.text.toString())
            {
                crearCuenta(edtCorreo.text.toString(),edtPassword.text.toString())

            }else{

            edtConfirmaPassword.error = "Las contraseñas no coinciden"
            edtConfirmaPassword.requestFocus()


        }
                            }


    private fun crearCuenta(user:String, pass:String){

        firebaseAuth.createUserWithEmailAndPassword(user,pass)
            .addOnCompleteListener(this){task->
                if(task.isSuccessful)
                {

                    Toast.makeText(this, "Verifica correo electronico", Toast.LENGTH_SHORT).show()
                    mensajeConfirmacion()
                    val intent= Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    finish()
                    startActivity(intent)

                }else{

                    Toast.makeText(this, "Algo salio mal. Error: "+task.exception, Toast.LENGTH_SHORT).show()
                }

            }


    }
    private fun mensajeConfirmacion(){

        val user=firebaseAuth.currentUser!!
        user.sendEmailVerification().addOnCompleteListener(this){task->

            if (task.isSuccessful){



            }else{}
        }
    }


}
