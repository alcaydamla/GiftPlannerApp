package com.example.homework

import android.os.Parcel
import android.os.Parcelable


data class Category(val name: String) : Parcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel.readString()!!)
        }


        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }
}
