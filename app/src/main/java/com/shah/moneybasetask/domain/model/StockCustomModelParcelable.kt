package com.shah.moneybasetask.domain.model

import android.os.Parcel
import android.os.Parcelable

data class StockCustomModelParcelable(
    val symbol:String,
    val name:String,
    val price:String,
    val priceChange:String,
    val upOrDown:String):Parcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(symbol)
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(priceChange)
        parcel.writeString(upOrDown)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StockCustomModelParcelable> {
        override fun createFromParcel(parcel: Parcel): StockCustomModelParcelable {
            return StockCustomModelParcelable(
                symbol = parcel.readString() ?: "",
                name = parcel.readString() ?: "",
                price = parcel.readString() ?: "",
                priceChange = parcel.readString() ?: "",
                upOrDown = parcel.readString() ?: ""
            )
        }

        override fun newArray(size: Int): Array<StockCustomModelParcelable?> {
            return arrayOfNulls(size)
        }
    }
}
