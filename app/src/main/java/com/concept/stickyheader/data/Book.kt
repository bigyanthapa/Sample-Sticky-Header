package com.concept.stickyheader.data

import com.google.gson.annotations.SerializedName


class Book(
    @SerializedName("author")
    val author: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("imageLink")
    val imageLink: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: Int
)