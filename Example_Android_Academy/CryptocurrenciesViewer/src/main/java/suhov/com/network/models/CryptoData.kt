package suhov.com.models

class CryptoData {
    constructor(id: String, name: String, symbol: String) {
        this.id = id
        this.name = name
        this.symbol = symbol
    }

    var id:String = ""
    var name:String = ""
    var symbol:String = ""
    var imgURL:String = ""
    var last_updated:String = ""
    var price:Double = 0.0
    var percent_change_1h:Double = 0.0
    var percent_change_24h:Double = 0.0
    var percent_change_7d:Double = 0.0
}
