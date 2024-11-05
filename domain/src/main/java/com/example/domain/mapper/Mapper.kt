package com.example.domain.mapper

interface Mapper<From, To> {
    suspend fun map(from: From): To
}