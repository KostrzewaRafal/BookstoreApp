package models


data class User(
    val id: Int,
    val imie: String,
    val nazwisko: String,
    val email: String,
    val haslo: String
)