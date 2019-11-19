package com.myb.dist.utils

import org.springframework.util.DigestUtils
import java.io.File

fun File.md5(): String {
    inputStream().use {
        return DigestUtils.md5DigestAsHex(it)
    }
}