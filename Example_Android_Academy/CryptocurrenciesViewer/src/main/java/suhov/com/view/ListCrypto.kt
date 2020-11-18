package suhov.com.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_crypto.*
import suhov.com.R
import suhov.com.adapters.ListCryptoRecyclerAdapter
import suhov.com.network.models.CryptoData
import suhov.com.viewModel.ListCryptoViewModel

class ListCrypto() : Fragment() {
    private lateinit var viewModel: ListCryptoViewModel
    private var cryptoList:ArrayList<CryptoData> = ArrayList()
    private lateinit var cryptoAdapter: ListCryptoRecyclerAdapter

    private val emptyString = ""
    private val emptyData = 0.0

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(ListCryptoViewModel::class.java)
        return inflater.inflate(R.layout.fragment_list_crypto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserve()
        initRecyclerView()
        restoreCryptoList()
    }

    private fun setObserve() {
        viewModel.showProgress.observe(viewLifecycleOwner, Observer {
            if (it){
                load_progress.visibility = VISIBLE
            } else {
                load_progress.visibility = GONE
            }
        })

        viewModel.cryptoList.observe(viewLifecycleOwner, Observer {
            cryptoList = it
            if (isFill(it)) {
                viewModel.getDataOfList()
                viewModel.getImgOfList()
                cryptoAdapter.update(it)
            } else {
                cryptoAdapter.update(it)
            }
        })
    }

    private fun isFill(it: java.util.ArrayList<CryptoData>?): Boolean {
        for (elem in it!!) {
            if (elem.imgURL != emptyString) return false
            if (elem.percent_change_1h != emptyData) return false
        }
        return true
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            cryptoAdapter = ListCryptoRecyclerAdapter()
            recycler_view.adapter = cryptoAdapter
            cryptoAdapter.submitList(cryptoList)
        }
    }

    private fun restoreCryptoList() {
        if (viewModel.cryptoList.value.isNullOrEmpty()) {
            viewModel.getCryptoList()
        }
    }
}