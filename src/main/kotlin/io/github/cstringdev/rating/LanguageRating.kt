package io.github.cstringdev.rating

data class LanguageRating(
    override val rating: Int,
    val years: Int,
    val name: String,
) : Rating()