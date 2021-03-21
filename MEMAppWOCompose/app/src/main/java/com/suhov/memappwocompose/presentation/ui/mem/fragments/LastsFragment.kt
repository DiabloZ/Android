package com.suhov.memappwocompose.presentation.ui.mem.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.suhov.memappwocompose.R
import com.suhov.memappwocompose.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tab.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LastsFragment : Fragment(R.layout.fragment_tab) {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            //if (viewModel.mutableList.value.isEmpty())
                viewModel.requireListMemes()

        test_btn.setOnClickListener {
            viewModel.addField()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { event ->
                when(event){
                    is MainViewModel.MemEvent.Success -> {
                        progress_bar.isVisible = false
                        event.result.result?.forEach {
                            Log.i("TAGTAGTAG", "onViewCreated: MainViewModel.MemEvent.Success -" +
                                    "${it?.id} " +
                                    "${it?.author} " +
                                    "${it?.description}"
                            )
                        }

                    }
                    is MainViewModel.MemEvent.Failure -> {
                        progress_bar.isVisible = true
                    }
                    is MainViewModel.MemEvent.Loading -> {
                        progress_bar.isVisible = true
                    }
                    else -> Unit
                }
            }
            viewModel.mutableList.collect{
                when(it) {
                    is MainViewModel.MemEvent.Success -> {
                        progress_bar.isVisible = false
                        it.result?.forEach {
                            Log.i("TAGTAGTAG", "onViewCreated: MainViewModel.MemEvent.Success -" +
                                    "${it?.id} " +
                                    "${it?.author} " +
                                    "${it?.description}"
                            )
                        }

                    }
                    else ->
                        Unit
                }
            }

            viewModel.mutbleList.collect{ it ->
                it.forEach {
                    Log.i("TAGTAGTAG", "onViewCreated: MainViewModel.MemEvent.Success -" +
                            "${it.id} " +
                            "${it.author} " +
                            "${it.description}" +
                            "${it.embedId}"
                    )
                }
            }
        }
    }
}


