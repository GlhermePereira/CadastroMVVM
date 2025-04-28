package com.example.appmvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appmvvm.model.StatusUsuario
import com.example.appmvvm.model.User

class LoginViewModel : ViewModel() {

    companion object {
        private val users = mutableListOf<User>()
    }

    fun register(login: String, senha: String): Boolean {
        if (users.any { it.login == login }) {
            return false // já existe
        }
        users.add(User(login, senha, ))
        return true
    }

    fun login(login: String, senha: String): Boolean {
        val user = users.find { it.login == login }

        if (user != null) {
            if (user.status == StatusUsuario.BLOQUEADO) {
                return false
            }

            if (user.password == senha) {
                user.status = StatusUsuario.ATIVO
                user.tentativasFalhas = 0
                return true
            } else {
                user.tentativasFalhas++
                if (user.tentativasFalhas >= 3) {
                    user.status = StatusUsuario.BLOQUEADO
                }
                return false
            }
        }

        return false
    }
    fun isUsuarioBloqueado(login: String): Boolean {
        val user = users.find { it.login == login }
        return user?.status == StatusUsuario.BLOQUEADO
    }
    fun getUsers(): List<User> {
        return users
    }

    fun addUser(user: User) {
        users.add(user)
    }
}
