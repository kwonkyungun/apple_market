package com.example.apple_market

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyItem (
    val aIcon: Int,
    val aTitle:String,
    val aAddress:String,
    val aPrice:Int,
    val aChat :Int,
    val aLike :Int,
    val aName:String,
    val aDetail:String
) : Parcelable
{}
