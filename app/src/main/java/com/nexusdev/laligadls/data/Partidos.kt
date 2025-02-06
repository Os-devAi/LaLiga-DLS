package com.nexusdev.laligadls.data

import android.os.Parcel
import android.os.Parcelable

data class Partidos(
    var id: String? = null,
    var equipoUno: String? = null,
    var equipoDos: String? = null,
    var golesUno: String? = null,
    var golesDos: String? = null,
    var resultado: String? = null,
    var fecha: String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(equipoUno)
        parcel.writeString(equipoDos)
        parcel.writeString(golesUno)
        parcel.writeString(golesDos)
        parcel.writeString(resultado)
        parcel.writeString(fecha)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Partidos> {
        override fun createFromParcel(parcel: Parcel): Partidos {
            return Partidos(parcel)
        }

        override fun newArray(size: Int): Array<Partidos?> {
            return arrayOfNulls(size)
        }
    }
}
