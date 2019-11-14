package com.myb.dist.utils

import java.text.SimpleDateFormat
import java.util.*

val DATE_PATTERN = SimpleDateFormat("YYYYmmDDHHmm")
fun Date.toString(pattern: SimpleDateFormat): String {
    return pattern.format(this)
}