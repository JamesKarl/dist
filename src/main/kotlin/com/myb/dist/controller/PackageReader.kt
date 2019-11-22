package com.myb.dist.controller

import com.myb.dist.db.AppInfo
import com.myb.dist.db.AppPublishHistory
import com.myb.dist.utils.md5
import net.dongliu.apk.parser.ApkParser
import java.io.File
import java.util.*

data class PackageInfo(
        val name: String,
        val versionName: String,
        val versionCode: Long,
        val icon: String,
        val pkgId: String,
        val size: Long,
        val md5: String
) {
    fun valid(): Boolean = versionName.isNotEmpty()
            && versionCode > 0
            && icon.isNotEmpty()
            && pkgId.isNotEmpty()
            && size > 0
            && md5.isNotEmpty()
}

object PackageReader {
    fun readApkInfo(file: File): PackageInfo {
        val apk = ApkParser(file).apkMeta
        return PackageInfo(
                name = apk.name,
                versionName = apk.versionName,
                versionCode = apk.versionCode,
                icon = apk.icon,
                pkgId = apk.packageName,
                size = file.length(),
                md5 = file.md5()
        )
    }
}


fun PackageInfo.toAppInfo(): AppInfo {
    return AppInfo(appId = pkgId, name = name, icon = icon, type = "android", id = Date().time.toInt())
}

fun PackageInfo.toAppPublishHistory(appId: Int, fileId: Int): AppPublishHistory {
    return AppPublishHistory(appId = appId, version = versionName, fileId = fileId, count = 0, publishDate = Date())
}