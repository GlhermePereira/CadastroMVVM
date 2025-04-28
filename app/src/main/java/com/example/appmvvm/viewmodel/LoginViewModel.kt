package com.example.appmvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appmvvm.model.User

class LoginViewModel : ViewModel() {

    companion object {
        private val users = mutableListOf<User>()
    }

    fun register(login: String, senha: String): Boolean {
        if (users.any { it.login == login }) {
            return false // já existe
        }
        users.add(User(login, senha, false))
        return true
    }

    fun login(login: String, senha: String): Boolean {
        return users.any { it.login == login && it.password == senha }
    }

    fun getUsers(): List<User> {
        return users
    }
}
