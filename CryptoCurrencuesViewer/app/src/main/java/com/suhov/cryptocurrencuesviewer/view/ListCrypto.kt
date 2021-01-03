package com.suhov.cryptocurrencuesviewer.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.fragment_list_crypto.*
import com.suhov.cryptocurrencuesviewer.R
import com.suhov.cryptocurrencuesviewer.adapters.ListCryptoRecyclerAdapter
import com.suhov.cryptocurrencuesviewer.network.models.CryptoData
import com.suhov.cryptocurrencuesviewer.viewModel.ListCryptoViewModel
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel

class ListCrypto : Fragment() {
    private val viewModel by viewModel<ListCryptoViewModel>()
    private var cryptoAdapter = get<ListCryptoRecyclerAdapter>()
    private val emptyString = ""
    private val emptyData = 0.0

    private val LOC_REQ_CODE = 10001
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_list_crypto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserve()
        initRecyclerView()
        restoreCryptoList()
        initFindEdit()
        setUpRefreshLayout()

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

        button_for_test.setOnClickListener{
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    ==  PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            } else {
                askLocationPermission()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation(){
        val locationTask: Task<Location> = fusedLocationProviderClient.lastLocation
        locationTask.addOnSuccessListener {
            Log.i("TAGTAGTAG", "addOnSuccessListener: $it")
            Log.i("TAGTAGTAG", "addOnSuccessListener: ${it?.longitude}")
            Log.i("TAGTAGTAG", "addOnSuccessListener: ${it?.latitude}")
        }
        locationTask.addOnFailureListener {
            Log.e("TAGTAGTAG", "addOnFailureListener: ${it.localizedMessage}", it)
        }
        locationTask.addOnCompleteListener {

        }
    }

    private fun askLocationPermission(){
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED)
                    if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                                    Manifest.permission.ACCESS_FINE_LOCATION)){
                        Log.d("TAGTAGTAG", "askLocationPermission: ")
                        ActivityCompat.requestPermissions(requireActivity(),
                                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOC_REQ_CODE)
                    } else {
                        ActivityCompat.requestPermissions(requireActivity(),
                                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOC_REQ_CODE)
                    }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == LOC_REQ_CODE){
            if (grantResults.isNotEmpty() && grantResults[0] ==  PackageManager.PERMISSION_GRANTED){
                getLastLocation()
            } else {
                askLocationPermission()
            }
        }
    }

    private fun setObserve() {
        viewModel.showProgress.observe(viewLifecycleOwner, {
            if (it){
                load_progress.visibility = VISIBLE
            } else {
                load_progress.visibility = GONE
            }
        })

        viewModel.cryptoList.observe(viewLifecycleOwner, {
            if (isFill(it)) {
                viewModel.getDataOfList()
                viewModel.getImgOfList()
                cryptoAdapter.update(it)
            } else {
                cryptoAdapter.update(it)
            }
        })

        viewModel.filterFind.observe(viewLifecycleOwner, {
            viewModel.setUpListWithFilter()
        })

        viewModel.filteredList.observe(viewLifecycleOwner, {
                Log.d("getPositionData", "viewModel.filteredList.observe(viewLifecycleOwner - ${it.size}")
                cryptoAdapter.update(it)
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
            recycler_view.adapter = cryptoAdapter
        }
    }

    private fun restoreCryptoList() {
        if (viewModel.cryptoList.value.isNullOrEmpty()) {
            viewModel.getCryptoList()
        }
    }

    private fun initFindEdit() {
        findView.doAfterTextChanged { viewModel.setFilter(it.toString()) }
    }

    private fun setUpRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.updateList()
            swipeRefreshLayout.isRefreshing = false
        }
    }
}
