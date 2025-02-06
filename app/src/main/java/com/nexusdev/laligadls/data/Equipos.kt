package com.nexusdev.laligadls.data

import android.os.Parcel
import android.os.Parcelable

data class Equipos(
    var id: String? = null,
    var nombre: String? = null,
    var entrenador: String? = null,
    var abrev: String? = null,
    var puntos: String? = null,
    var partidos: String? = null,
    var ganados: String? = null,
    var empates: String? = null,
    var perdidos: String? = null,
    var golesF: String? = null,
    var golesC: String? = null,

    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
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
        parcel.writeString(nombre)
        parcel.writeString(entrenador)
        parcel.writeString(abrev)
        parcel.writeString(puntos)
        parcel.writeString(partidos)
        parcel.writeString(ganados)
        parcel.writeString(empates)
        parcel.writeString(perdidos)
        parcel.writeString(golesF)
        parcel.writeString(golesC)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Equipos> {
        override fun createFromParcel(parcel: Parcel): Equipos {
            return Equipos(parcel)
        }

        override fun newArray(size: Int): Array<Equipos?> {
            return arrayOfNulls(size)
        }
    }
}
