package com.myb.dist.controller

import com.myb.dist.db.AppInfo
import net.dongliu.apk.parser.ApkParser
import java.io.File
import java.util.*

data class PackageInfo(val name: String, val versionName: String, val versionCode: Long, val icon: String, val pkgId: String)

object PackageReader {
    fun readApkInfo(file: File): PackageInfo {
        val apk = ApkParser(file).apkMeta
        return PackageInfo(name = apk.name, versionName = apk.versionName, versionCode = apk.versionCode, icon = apk.icon, pkgId = apk.packageName)
    }
}


fun PackageInfo.toAppInfo(): AppInfo {
    return AppInfo(appId = pkgId, name = name, icon = icon, type = "android", id = Date().time.toInt())
}