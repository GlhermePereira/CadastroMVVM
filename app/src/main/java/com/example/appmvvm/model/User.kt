package com.example.appmvvm.model

data class User(
    var login:String,
    var password:String,
    var bloqueado: Boolean = false,
    var tentativasFalhas: Int = 0
)
