package io.github.cstringdev.users

import io.github.cstringdev.encryption.Encryption

data class User(
    val username: String,
    var password: String,
    val admin: Boolean = false,
)
{
    init
    {
        this.password = Encryption.encrypt(
            this.password,
            "${username}:${password}"
        )
    }

    fun matchesPassword(password: String): Boolean
    {
        return Encryption.encrypt(password, "${username}:${password}") == this.password
    }
}