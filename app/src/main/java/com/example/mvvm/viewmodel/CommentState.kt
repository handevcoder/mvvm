package com.example.mvvm.viewmodel

import com.example.mvvm.model.CommentModel
import java.lang.Exception

sealed class CommentState {
    data class Loading(val message: String = "Loading...") : CommentState()
    data class Error(val exception: Exception) : CommentState()
    data class SuccessGetAllComment(val list: List<CommentModel>) : CommentState()
}
