package suhov.com.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.slide_crypto_list_item.view.*

import suhov.com.R
import suhov.com.network.models.CryptoData
import suhov.com.utils.Constants
import suhov.com.view.MainFragmentDirections


class ListCryptoRecyclerAdapter : RecyclerView.Adapter<ListCryptoRecyclerAdapter.ListCryptoViewHolder>() {

    private var items: List<CryptoData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCryptoViewHolder {
        return ListCryptoViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.slide_crypto_list_item, parent, false), items
        )
    }

    override fun onBindViewHolder(holder: ListCryptoViewHolder, position: Int) {holder.bind(items[position])}

    override fun getItemCount(): Int {return items.size}

    fun submitList(itemList : List<CryptoData>){items = itemList}

    fun update(itemList: List<CryptoData>) {
        items = itemList
        notifyDataSetChanged()
    }

    class ListCryptoViewHolder constructor(
            itemView: View,
            items: List<CryptoData>
    ) : RecyclerView.ViewHolder(itemView){
        private val imgCrypto = itemView.img_crypto
        private val titleCrypto = itemView.title_crypto
        private val priceCrypto = itemView.price_crypto
        private val marketDynamicsDay = itemView.market_dynamics_day
        private val marketDynamicsHour = itemView.market_dynamics_hour
        private val currencies = "$"
        private val percent = "%"
        private val cutToTwoSymbol = "%.2f"
        private val cutToFourSymbol = "%.4f"

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val action = MainFragmentDirections.actionListCryptoToDetailsViewFragment(position, items.toTypedArray())
                Navigation.findNavController(it).navigate(action)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: CryptoData) {
            titleCrypto.text = item.symbol
            priceCrypto.text = currencies + String.format(cutToFourSymbol, item.price)
            marketDynamicsHour.text =  String.format(cutToTwoSymbol, item.percent_change_1h) + percent
            marketDynamicsDay.text =  String.format(cutToTwoSymbol, item.percent_change_24h) + percent

            Glide.with(itemView.context)
                    .load(Constants.IMG_URL_MAIN + item.imgURL)
                    .placeholder(R.drawable.guru)
                    .into(imgCrypto)
        }
    }

}