package com.example.appmvvm

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmvvm.databinding.ActivityAdminBinding
import com.example.appmvvm.viewmodel.LoginViewModel

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuração da RecyclerView
        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(this)


        val users = loginViewModel.getUsers()
        val adapter = UserAdapter(users)
        binding.recyclerViewUsers.adapter = adapter
        binding.recyclerViewUsers.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
        binding.fabVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
