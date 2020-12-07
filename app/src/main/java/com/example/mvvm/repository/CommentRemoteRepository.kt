package com.example.mvvm.repository


interface CommentRemoteRepository {
    suspend fun getAllComment(): List<CommentResponse>
}
