package com.example.mvvm.repository

import com.example.mvvm.model.CommentService

class CommentRemoteRepositoryImpl(private val service: CommentService): CommentRemoteRepository {
    override suspend fun getAllComment(): List<CommentResponse> {
        return  service.getAllPost()
    }
}