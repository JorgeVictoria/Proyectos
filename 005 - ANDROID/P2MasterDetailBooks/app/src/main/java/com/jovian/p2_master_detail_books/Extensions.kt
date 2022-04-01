package com.jovian.p2_master_detail_books

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log

//This function pass an string like "NameImage.jpg" that is in raw folder to an Bitmap
fun String.toBitmap(context: Context): Bitmap {

    val imageName = this.split(".")[0]
    val id = context.resources.getIdentifier(
        imageName,
        "raw",
        context.packageName
    )

    val inputStream =context.resources.openRawResource(id)
    return  BitmapFactory.decodeStream(inputStream)
}