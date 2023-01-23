package com.example.ejercicio1
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth= Firebase.auth

        val passwordForget: TextView = findViewById(R.id.forgget)
        passwordForget.paintFlags = passwordForget.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        passwordForget.setOnClickListener { restablecerPassword() }

        val botonLogin: Button = findViewById(R.id.btnLogin)
        botonLogin.setOnClickListener {validarUser()}

        val botonCrear: Button = findViewById(R.id.btnCrearUser)
        botonCrear.setOnClickListener {createUser()}

    }



   private fun validarUser(){

       val edttexto: EditText = findViewById(R.id.editUsuario)
       val edtPassword: EditText = findViewById(R.id.editPassword)



        if (edttexto.text.isNotEmpty()&&edtPassword.text.isNotEmpty()) {

            firebaseAuth.signInWithEmailAndPassword(edttexto.text.toString(),edtPassword.text.toString())
                .addOnCompleteListener(this){task ->



                    if(task.isSuccessful){
                        var user=firebaseAuth.currentUser
                        var verifica=user?.isEmailVerified
                        if (verifica==true) {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra("INTENT_NAME", edttexto.text)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,"Se requiere verificar correo",Toast.LENGTH_SHORT).show()

                        }
                    }
                    else{
                        Toast.makeText(this, "Usuario y/o contraseña incorrecto", Toast.LENGTH_SHORT).show()
                    }
                }



            }else {

            if (edttexto.text.isEmpty()) {
                edttexto.error = "Ingrese Usuario"
            }
            if (edtPassword.text.isEmpty()) {
                edtPassword.error = "Ingrese contraseña"
            }


        }

    }

    private fun createUser(){

        val intent= Intent(this, CreateUser::class.java)
        startActivity(intent)

    }


    private fun restablecerPassword(){

        val intent= Intent(this, RestablecerPass::class.java)
        startActivity(intent)


    }




}

