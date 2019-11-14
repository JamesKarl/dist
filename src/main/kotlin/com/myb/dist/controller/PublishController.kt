package com.myb.dist.controller

import com.myb.dist.DistConfig
import com.myb.dist.db.AppInfo
import com.myb.dist.db.AppInfoRepository
import com.myb.dist.db.AppPublishHistory
import com.myb.dist.db.AppPublishHistoryRepository
import com.myb.dist.utils.DATE_PATTERN
import com.myb.dist.utils.toString
import javafx.scene.input.DataFormat
import org.apache.tomcat.jni.Directory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("dist")
@CrossOrigin
class PublishController {

    @Autowired
    lateinit var appRepository: AppInfoRepository
    @Autowired
    lateinit var historyRepository: AppPublishHistoryRepository
    //@Autowired
    val config = DistConfig()

    @GetMapping("hello")
    fun hello(): String {
        return "Hello, world"
    }

    @PostMapping("apps")
    fun queryAllApps(): List<AppInfo> {
        return appRepository.findAll().toList()
    }

    @PostMapping("history")
    fun queryPublishHistory(@RequestParam("appId") appId: Int,
                            @RequestParam("page") page: Int,
                            @RequestParam("pageSize") pageSize: Int
    ): Page<AppPublishHistory> {
        return historyRepository.findAll(PageRequest.of(page, pageSize))
    }


    @PostMapping("detail")
    fun queryDetail(@Param("appId") appId: Int): Optional<AppInfo> {
        return appRepository.findById(appId)
    }

    @PostMapping("uploadPackage")
    fun uploadPackage(@RequestParam("file") file: MultipartFile, redirectAttributes: RedirectAttributes): String? {
        val originalFilename = file.originalFilename ?: return null
        val nowString = Date().toString(DATE_PATTERN)
        val destName = "${nowString}_${originalFilename}"
        val root = getRootDir()
        val dest = File(root, destName)
        dest.createNewFile()
        dest.outputStream().buffered().write(file.inputStream.buffered().readBytes())
        return destName
    }

    @GetMapping("downloadPackage")
    fun downloadPackage(@RequestParam("fileId") fileId: String, response: HttpServletResponse) {
        val dest = getDestFile(fileId)
        if (!dest.second.exists()) return
        with(response) {
            setHeader("content-type", "application/octet-stream")
            contentType = "application/octet-stream"
            setHeader("Content-Disposition", "attachment;filename=${dest.first}")
            outputStream.buffered().write(dest.second.inputStream().buffered().readBytes())
        }
    }

    private fun getRootDir(): File {
        val root = File(config.root)
        if (root.isDirectory) {
            return root
        }

        if (root.isFile) {
            root.delete()
        }

        root.mkdir()
        return root
    }

    private fun getDestFile(fileId: String): Pair<String, File> {
        val file = File(getRootDir(), fileId)
        val name = file.name
        return name to file
    }
}