package com.jovian.parcelables.model

import android.os.Parcel
import android.os.Parcelable

data class Person(val name: String?, val surname: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(), //name
        parcel.readString() //surname
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {

        if (p0 != null) {
            p0.writeString(name)
        }
        if (p0 != null) {
            p0.writeString(surname)
        }

    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }


}
