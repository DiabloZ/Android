package suhov.com.fragments.ListCrypto

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_crypto.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import suhov.com.R
import suhov.com.models.CryptoData
import suhov.com.models.list.CoinList
import suhov.com.network.RetroInstance
import suhov.com.network.RetroService
import java.util.concurrent.ThreadPoolExecutor


class ListCrypto() : Fragment() {
    private val TAG:String = "ListCryptocurrencies"

    private lateinit var cryptoAdapter: ListCryptoRecyclerAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_crypto, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet() {
        var dataBase:ArrayList<CryptoData> = ArrayList()

        val retroInstance = RetroInstance.getRetrogitInstanceCryptoList().create(RetroService::class.java)
        val request = retroInstance.getListFromAPI()
        request.enqueue(object : Callback<CoinList> {
            override fun onResponse(call: Call<CoinList>, response: Response<CoinList>) {
                if (response.isSuccessful) {

                    val dataList = response.body()?.data
                    for (data in dataList!!) {
                        dataBase.add(
                                CryptoData(
                                        data?.id.toString(),
                                        data?.name.toString(),
                                        data?.symbol.toString())
                        )
                        Log.i(TAG, "onResponse: " + dataBase.get(dataBase.size-1).name)
                    }

                }
                cryptoAdapter.update(dataBase)
            }

            override fun onFailure(call: Call<CoinList>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        cryptoAdapter.submitList(dataBase)
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            cryptoAdapter = ListCryptoRecyclerAdapter()
            recycler_view.adapter = cryptoAdapter
        }
    }

}