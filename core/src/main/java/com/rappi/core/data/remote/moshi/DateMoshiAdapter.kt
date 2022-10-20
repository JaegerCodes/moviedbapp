package com.rappi.core.data.remote.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * [DateMoshiAdapter] is an adapter that Moshi will use to convert a [String] to [Date] and vice versa.
 */
class DateMoshiAdapter {

    private val dateFormat = SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss", Locale.US)

    @ToJson
    fun toJson(date: Date): String = dateFormat.format(date)

    @FromJson
    fun fromJson(date: String): Date = dateFormat.parse(date) ?: Date()
}