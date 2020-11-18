package suhov.com.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_empty.*
import suhov.com.R
import suhov.com.fragments.ListCrypto.ListCryptoFactory

class EmptyFragmentTwo() : Fragment() {
    lateinit var lcModel:ListCryptoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_empty, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lolView.text = "test"
        lcModel = ViewModelProvider(this, ListCryptoFactory(requireActivity().application, "te!!!!")).get(ListCryptoViewModel::class.java)
        lcModel.liveDate.observe(viewLifecycleOwner, Observer {
            lolView.text = it
        })
    }
}