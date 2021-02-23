package com.suhov.memappwocompose.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.suhov.memappwocompose.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tab.*
import javax.inject.Inject
@AndroidEntryPoint
class LastsFragment : Fragment(R.layout.fragment_tab) {

    @Inject
    lateinit var someRandomString:String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttom_sheet_display.text = someRandomString
    }
}


