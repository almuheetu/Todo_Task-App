package com.example.todotask

import SharedPrefHelper
import TodoViewModel
import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todotask.databinding.FragmentTodoTaskBinding


class TodoTaskFragment : Fragment(), TodoAdapter.ItemClickListener {
    private lateinit var adapter: TodoAdapter
    private lateinit var binding: FragmentTodoTaskBinding
    private val viewModel: TodoViewModel by viewModels()
//    private lateinit var sharedPrefHelper: SharedPrefHelper


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoTaskBinding.inflate(inflater, container, false)
//        sharedPrefHelper = SharedPrefHelper(requireContext())
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding.todoTaskRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

//        val tasks = sharedPrefHelper.getTasks()
//        viewModel.items.observe(viewLifecycleOwner, Observer {
//            adapter = TodoAdapter(tasks as ArrayList<Todo>, this)
//            recyclerView.adapter = adapter
//        })

        viewModel.items.observe(viewLifecycleOwner, Observer {
            adapter = TodoAdapter(it, this)
            recyclerView.adapter = adapter
        })

        binding.btnAdd.setOnClickListener {
            val action =
                TodoTaskFragmentDirections.actionTodoTaskFragmentNavIdToAddTaskFragmentNavId()
            findNavController().navigate(action)
        }

    }

    override fun onItemClick(todo: Todo) {
        Log.d("Log404", "onItemClick: $todo")
        val action =
            TodoTaskFragmentDirections.actionTodoTaskFragmentNavIdToUpdateTaskFragmentNavId(todo)
        findNavController().navigate(action)
    }
}