package suhov.com.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.details_view_item.view.*

import suhov.com.R

import suhov.com.network.models.CryptoData
import suhov.com.utils.Constants

class DetailViewAdapter(private var list: List<CryptoData>) :  RecyclerView.Adapter<DetailViewAdapter.Pager2ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.details_view_item, parent, false))
    }

    override fun getItemCount(): Int {return list.size}

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {holder.bind(list[position])}

    fun update(itemList: List<CryptoData>) {
        list = itemList
        notifyDataSetChanged()
    }

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var imgCrypto = itemView.img_crypto
        private var titleCrypto = itemView.title_crypto
        private var fullTitleCrypto = itemView.full_title_crypto
        private var priceCrypto = itemView.price_crypto
        private var marketDynamicDay = itemView.market_dynamics_day
        private var marketDynamicHour = itemView.market_dynamics_hour
        private var marketDynamicWeek = itemView.market_dynamics_week
        private var lastUpdateBtn = itemView.last_update_button
        private val currencies = "$"
        private val percent = "%"
        private val cutToTwoSymbol = "%.2f"
        private val cutToFourSymbol = "%.4f"

        init {
            lastUpdateBtn.setOnClickListener {
                val position = adapterPosition
                Toast.makeText(itemView.context, "you update crypto info in cell #${position + 1}", Toast.LENGTH_SHORT).show()
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: CryptoData) {
            titleCrypto.text = item.symbol
            fullTitleCrypto.text = item.name
            priceCrypto.text = currencies + String.format(cutToFourSymbol, item.price)
            marketDynamicHour.text = String.format(cutToTwoSymbol, item.percent_change_1h) + percent
            marketDynamicDay.text = String.format(cutToTwoSymbol, item.percent_change_24h) + percent
            marketDynamicWeek.text = String.format(cutToTwoSymbol, item.percent_change_7d) + percent
            lastUpdateBtn.text = "↻" + item.last_updated

            Glide.with(itemView.context)
                    .load(Constants.IMG_URL_MAIN + item.imgURL)
                    .placeholder(R.drawable.guru)
                    .into(imgCrypto)
        }
    }
}
