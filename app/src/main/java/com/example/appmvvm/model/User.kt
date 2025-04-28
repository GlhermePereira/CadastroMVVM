package com.example.appmvvm.model

data class User(
    var login:String,
    var password:String,
    var status: StatusUsuario = StatusUsuario.INATIVO,
    var tentativasFalhas: Int = 0

)
enum class StatusUsuario {
    ATIVO,
    INATIVO,
    BLOQUEADO
}
