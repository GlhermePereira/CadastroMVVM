package com.example.appmvvm

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.appmvvm.databinding.ActivityMainBinding
import com.example.appmvvm.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {

    // Aqui, o binding é inicializado para a Activity
    private lateinit var binding: ActivityMainBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o binding com o layout da Activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ação para o botão de login
        binding.btnLogin.setOnClickListener {
            val login = binding.edtLogin.text.toString()
            val senha = binding.edtSenha.text.toString()

            if (loginViewModel.isUsuarioBloqueado(login)) {
                AlertDialog.Builder(this)
                    .setTitle("Sistema X")
                    .setMessage("Usuário bloqueado!")
                    .show()
            } else {
                val sucesso = loginViewModel.login(login, senha)
                if (sucesso) {
                    AlertDialog.Builder(this)
                        .setTitle("Sistema X")
                        .setMessage("Logado com sucesso!")
                        .show()
                } else {
                    val user = loginViewModel.getUsers().find { it.login == login }
                    val tentativasRestantes = 3 - (user?.tentativasFalhas ?: 0)

                    AlertDialog.Builder(this)
                        .setTitle("Sistema X")
                        .setMessage("Senha ou login incorretos! Tentativas restantes: $tentativasRestantes")
                        .show()
                }
            }
        }


        // Ação para o botão de cadastro
        binding.btnCadastrar.setOnClickListener {
            val login = binding.edtLogin.text.toString()
            val pass = binding.edtSenha.text.toString()

            val result = loginViewModel.register(login, pass)

            val alertReg = AlertDialog.Builder(this)
            if (result) {
                alertReg.setTitle("Sistema X")
                alertReg.setMessage("Cadastrado com sucesso!")
            } else {
                alertReg.setTitle("Sistema X")
                alertReg.setMessage("Usuário já existe ou dados inválidos!")
            }
            alertReg.show()
        }

        binding.btnVerUsuarios.setOnClickListener {
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
        }
    }
}
