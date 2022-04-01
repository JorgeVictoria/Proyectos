package com.jovian.masterdetail

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

//This function pass an string like "batman.jpg" that is in raw folder to an Bitmap
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