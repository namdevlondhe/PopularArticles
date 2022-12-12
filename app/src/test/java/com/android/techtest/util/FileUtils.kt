package com.android.techtest.util

import java.io.File

object FileUtils {
 fun getJson(path:String):String?{
     val uri = this.javaClass.classLoader?.getResource(path)?:return null
     val file = File(uri.path)
     return String(file.readBytes())
 }
}