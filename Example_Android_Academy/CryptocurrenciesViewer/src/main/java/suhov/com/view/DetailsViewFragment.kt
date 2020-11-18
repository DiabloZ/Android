package suhov.com.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.details_view_fragment.*
import kotlinx.android.synthetic.main.details_view_fragment.liner_chart_view
import suhov.com.R
import suhov.com.adapters.DetailViewAdapter
import suhov.com.network.models.CryptoData
import suhov.com.viewModel.DetailViewViewModel
import kotlin.math.abs

class DetailsViewFragment : Fragment() {
    private lateinit var viewModel: DetailViewViewModel
    private lateinit var detailViewAdapter:DetailViewAdapter
    private var cryptoList:ArrayList<CryptoData> = ArrayList()
    private val emptyString = ""
    private var positionCrypto:Int = 0
    private val defaultPosition = 0
    private var graph:Int = 0
    private val correctiveValue = 1
    private val offscreenPageLimit = 3
    private val pixMarginPage = 3
    private val linerChartTextSize = 16f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cryptoList = ArrayList(MainFragmentArgs.fromBundle(requireArguments()).arrCrypto.toList())
        positionCrypto = MainFragmentArgs.fromBundle(requireArguments()).position
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(DetailViewViewModel::class.java)
        return inflater.inflate(R.layout.details_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserve()
        setUpTabLayoutCryptoGraph()
        setUpViewPagerCryptoData()
        setUpGraph()
        viewModel.setStorageData(cryptoList)
        viewModel.changePosition(positionCrypto)
    }

    private fun setObserve() {
        viewModel.showProgress.observe(viewLifecycleOwner, Observer {
            if (it) {
                load_progress.visibility = VISIBLE
            } else {
                if (cryptoList[positionCrypto].data_change_day.isNotEmpty() || cryptoList[positionCrypto].data_change_year.isNotEmpty()) {
                    updateGraph()
                }
                load_progress.visibility = GONE
            }
        })

        viewModel.cryptoList.observe(viewLifecycleOwner, Observer {
            detailViewAdapter.update(it)
        })

        viewModel.position.observe(viewLifecycleOwner, Observer {
            positionCrypto = it
            viewModel.getDetailViewItem()
        })

        viewModel.graph.observe(viewLifecycleOwner, Observer {
            graph = it
            viewModel.getDetailViewItem()
        })

    }

    private fun setUpTabLayoutCryptoGraph() {
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.changeGraph(tab!!.position)
                graph_data.text = emptyString

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        tab_layout.selectTab(tab_layout.getTabAt(graph))
        viewModel.changeGraph(graph)
    }

    private fun setUpViewPagerCryptoData() {
        detailViewAdapter = DetailViewAdapter(cryptoList)
        view_pager2.adapter = detailViewAdapter
        view_pager2.orientation = ViewPager2.ORIENTATION_VERTICAL

        view_pager2.clipToPadding = false
        view_pager2.clipChildren = false
        view_pager2.offscreenPageLimit = offscreenPageLimit
        view_pager2.getChildAt(defaultPosition)
                .overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(pixMarginPage))
        compositePageTransformer.addTransformer { _: View, position: Float ->
            correctiveValue - abs(position)
        }
        view_pager2.setPageTransformer(compositePageTransformer)

        view_pager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.changePosition(position)
            }
        })

        view_pager2.currentItem = positionCrypto
        viewModel.changePosition(positionCrypto)
    }

    private fun setUpGraph() {
        liner_chart_view.axisRight.isEnabled = false
        liner_chart_view.axisLeft.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        liner_chart_view.setScaleEnabled(false)
        liner_chart_view.setPinchZoom(false)
        liner_chart_view.isDoubleTapToZoomEnabled = false
        liner_chart_view.setTouchEnabled(true)
        liner_chart_view.invalidate()

        liner_chart_view.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                graph_data.visibility = VISIBLE
                graph_data.text = e?.y.toString()
            }

            override fun onNothingSelected() {graph_data.text = emptyString}
        })
    }

    private fun updateGraph() {
        val arr = when (graph) {
            0 -> cryptoList[positionCrypto].data_change_day
            1 -> cryptoList[positionCrypto].data_change_week
            2 -> cryptoList[positionCrypto].data_change_month
            3 -> cryptoList[positionCrypto].data_change_three_month
            4 -> cryptoList[positionCrypto].data_change_six_month
            5 -> cryptoList[positionCrypto].data_change_year
            6 -> cryptoList[positionCrypto].data_change_allTime
            else -> cryptoList[positionCrypto].data_change_day
        }

        val lineEntries: ArrayList<Entry> = ArrayList()

        for (xx in arr.indices) {
            val y: Float = arr[xx].close?.toFloat()!!
            val x: Float = xx.toFloat()
            lineEntries.add(Entry(x, y))
        }
        val lineDataSet = LineDataSet(lineEntries, cryptoList[positionCrypto].name)

        lineDataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        lineDataSet.valueTextColor = Color.BLACK
        lineDataSet.valueTextSize = linerChartTextSize
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawCircles(false)

        liner_chart_view.data = LineData(lineDataSet)
        liner_chart_view.invalidate()
    }
}