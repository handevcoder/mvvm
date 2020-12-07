package com.example.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.repository.CommentRemoteRepository

class CommentViewModelFactory(
    private val commentRemoteRepository: CommentRemoteRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CommentViewModel(commentRemoteRepository) as T
    }
}