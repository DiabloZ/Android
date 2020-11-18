package suhov.com.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.slide_crypto_list_item.view.*

import suhov.com.R
import suhov.com.network.models.CryptoData
import suhov.com.utils.Constants


class ListCryptoRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<CryptoData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListCryptoViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.slide_crypto_list_item, parent, false), items
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ListCryptoViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(itemList : List<CryptoData>){
        items = itemList
    }

    fun update(itemList: List<CryptoData>) {
        items = itemList
        notifyDataSetChanged()
    }

    class ListCryptoViewHolder constructor(
            itemView: View,
            items: List<CryptoData>
    ) : RecyclerView.ViewHolder(itemView){
        private val imgCrypto = itemView.img_Crypto
        private val titleCrypto = itemView.title_Crypto
        private val priceCrypto = itemView.price_Crypto
        private val marketDynamicsDay = itemView.market_Dynamics_Day
        private val marketDynamicsHour = itemView.market_Dynamics_Hour

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val action = MainFragmentDirections.actionListCryptoToDetailsViewFragment(position, items.toTypedArray())
                Navigation.findNavController(it).navigate(action)
                Toast.makeText(itemView.context, "you update crypto info in cell #${position + 1}", Toast.LENGTH_SHORT).show()
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: CryptoData) {
            titleCrypto.setText(item.symbol)
            priceCrypto.setText("$" + String.format("%.4f", item.price))
            marketDynamicsHour.setText(String.format("%.2f", item.percent_change_1h) + "%")
            marketDynamicsDay.setText(String.format("%.2f", item.percent_change_24h) + "%")

            Glide.with(itemView.context)
                    .load(Constants.IMG_URL_MAIN + item.imgURL)
                    .placeholder(R.drawable.placeholder)
                    .into(imgCrypto)
        }
    }

}