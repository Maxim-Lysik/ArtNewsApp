package com.example.artnewsapplicationtorelease.models

data class UserInput(
    val from: String,
    val lang: String,
    val page: Int,
    val q: String,
    val size: Int,
    val sort_by: String
)