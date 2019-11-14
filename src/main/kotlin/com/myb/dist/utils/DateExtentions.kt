package com.myb.dist.utils

import java.text.SimpleDateFormat
import java.util.*

val DATE_PATTERN = SimpleDateFormat("yyyyMMddHHmmss")
fun Date.toString(pattern: SimpleDateFormat): String {
    return pattern.format(this)
}