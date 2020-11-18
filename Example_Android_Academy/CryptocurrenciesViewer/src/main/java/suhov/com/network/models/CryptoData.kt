package suhov.com.network.models

import android.os.Parcel
import android.os.Parcelable
import suhov.com.network.models.current.DataItem


class CryptoData() : Parcelable {

    var id:String = ""
    var name:String = ""
    var symbol:String = ""
    var imgURL:String = ""
    var last_updated:String = ""
    var price:Double = 0.0
    var percent_change_1h:Double = 0.0
    var percent_change_24h:Double = 0.0
    var percent_change_7d:Double = 0.0
    var data_change_day:List<DataItem> = ArrayList()
    var data_change_week:List<DataItem> = ArrayList()
    var data_change_month:List<DataItem> = ArrayList()
    var data_change_three_month:List<DataItem> = ArrayList()
    var data_change_six_month:List<DataItem> = ArrayList()
    var data_change_year:List<DataItem> = ArrayList()
    var data_change_allTime:List<DataItem> = ArrayList()

    constructor(parcel: Parcel) : this() {
        id = parcel.readString().toString()
        name = parcel.readString().toString()
        symbol = parcel.readString().toString()
        imgURL = parcel.readString().toString()
        last_updated = parcel.readString().toString()
        price = parcel.readDouble()
        percent_change_1h = parcel.readDouble()
        percent_change_24h = parcel.readDouble()
        percent_change_7d = parcel.readDouble()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(symbol)
        parcel.writeString(imgURL)
        parcel.writeString(last_updated)
        parcel.writeDouble(price)
        parcel.writeDouble(percent_change_1h)
        parcel.writeDouble(percent_change_24h)
        parcel.writeDouble(percent_change_7d)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CryptoData> {
        override fun createFromParcel(parcel: Parcel): CryptoData {
            return CryptoData(parcel)
        }

        override fun newArray(size: Int): Array<CryptoData?> {
            return arrayOfNulls(size)
        }
    }


}
