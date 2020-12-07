package com.example.mvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.mvvm.AdapterComment
import com.example.mvvm.R
import com.example.mvvm.databinding.FragmentCommentBinding
import com.example.mvvm.model.CommentModel
import com.example.mvvm.model.CommentService
import com.example.mvvm.repository.ClientComment
import com.example.mvvm.repository.CommentRemoteRepository
import com.example.mvvm.repository.CommentRemoteRepositoryImpl
import com.example.mvvm.viewmodel.CommentState
import com.example.mvvm.viewmodel.CommentViewModel
import com.example.mvvm.viewmodel.CommentViewModelFactory
import kotlinx.android.synthetic.main.fragment_comment.*
import kotlinx.android.synthetic.main.fragment_comment.view.*
import java.lang.Exception

class CommentFragment : Fragment() {
    private lateinit var binding: FragmentCommentBinding
    private val adapter by lazy { AdapterComment(requireContext()) }
    private val service: CommentService by lazy { ClientComment.service }
    private val remoteRepository: CommentRemoteRepository by lazy {
        CommentRemoteRepositoryImpl(
            service
        )
    }
    private val viewModelFactory by lazy { CommentViewModelFactory(remoteRepository) }
    private val viewModel by viewModels<CommentViewModel> { viewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommentBinding.inflate(inflater, container, false).apply {
            rvComment.adapter = adapter
            viewModel.state.observe(viewLifecycleOwner) {
                when (it) {
                    is CommentState.Loading -> showLoading(true)
                    is CommentState.SuccessGetAllComment -> {
                        showLoading(false)
                        adapter.list = it.list.toMutableList()
                    }
                    is CommentState.Error -> {
                        showLoading(false)
                        Toast.makeText(
                            requireContext(),
                            "${it.exception.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllComment()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pgBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}