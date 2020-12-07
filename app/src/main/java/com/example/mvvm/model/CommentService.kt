package com.example.mvvm.model

import com.example.mvvm.repository.CommentResponse
import retrofit2.http.GET

interface CommentService {
    @GET("comments")
    suspend fun getAllPost(): List<CommentResponse>
}