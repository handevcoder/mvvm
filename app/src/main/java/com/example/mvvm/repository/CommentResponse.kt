package com.example.mvvm.repository

import com.example.mvvm.model.CommentModel
import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("name") val name: String = "",
    @SerializedName("postId") val postId: Int = 0,
    @SerializedName("id") val id: Int = 0,
    @SerializedName("body") val body: String = "",
    @SerializedName("email") val email: String = ""
)

fun CommentResponse.toModel() = CommentModel(name, postId, id, body, email)
