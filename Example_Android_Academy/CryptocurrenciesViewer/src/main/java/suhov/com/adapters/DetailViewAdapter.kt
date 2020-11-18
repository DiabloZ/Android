package suhov.com.view

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.details_view_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DetailViewAdapter.Pager2ViewHolder, position: Int) {
                when (holder) {
                    is DetailViewAdapter.Pager2ViewHolder -> {
                        holder.bind(list.get(position))
                    }
                }
    }

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var imgCrypto = itemView.img_Crypto
        private var titleCrypto = itemView.title_Crypto
        private var fullTitleCrypto = itemView.full_title_Crypto
        private var priceCrypto = itemView.price_Crypto
        private var marketDynamicDay = itemView.market_Dynamics_Day
        private var marketDynamicHour = itemView.market_Dynamics_Hour
        private var marketDynamicWeek = itemView.market_Dynamics_Week
        private var lastUpdateBtn = itemView.last_update_button

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
            priceCrypto.text = "$" + String.format("%.4f", item.price)
            marketDynamicHour.text = String.format("%.2f", item.percent_change_1h) + "%"
            marketDynamicDay.text = String.format("%.2f", item.percent_change_24h) + "%"
            marketDynamicWeek.text = String.format("%.2f", item.percent_change_7d) + "%"
            lastUpdateBtn.text = "↻" + item.last_updated

            Glide.with(itemView.context)
                    .load(Constants.IMG_URL_MAIN + item.imgURL)
                    .placeholder(R.drawable.placeholder)
                    .into(imgCrypto)
        }

    }
}
