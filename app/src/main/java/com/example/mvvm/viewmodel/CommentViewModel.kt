package com.example.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.repository.CommentRemoteRepository
import com.example.mvvm.repository.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CommentViewModel(private val commentRemoteRepository: CommentRemoteRepository) : ViewModel() {
    private val mutableState by lazy { MutableLiveData<CommentState>() }
    val state: LiveData<CommentState> get() = mutableState

    fun getAllComment() {
        mutableState.value = CommentState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val commentResponse = commentRemoteRepository.getAllComment()
                val commentList = commentResponse.asSequence().map { it.toModel() }.toList()
                mutableState.postValue(CommentState.SuccessGetAllComment(commentList))
            } catch (exc: Exception) {
                exc.printStackTrace()
                mutableState.postValue(CommentState.Error(exc))
            }
        }

    }
}