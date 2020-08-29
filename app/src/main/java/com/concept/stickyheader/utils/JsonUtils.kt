package com.concept.stickyheader.utils

import android.content.Context
import com.concept.stickyheader.data.Book
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.IOException

object JsonUtils {

    private const val SOURCE_FILE_NAME = "sample_data.json"

    fun getItems(context: Context): List<Book> {

        val jsonString: String
        try {
            jsonString =
                context.assets.open(SOURCE_FILE_NAME).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }

        return GsonBuilder().create()
            .fromJson(jsonString, object : TypeToken<List<Book>>() {}.type)
    }
}