package com.example.domain.entity

data class ListButton(
    val title: String,
    val id: ButtonType = ButtonType.Default
)

enum class ButtonType { Default }
