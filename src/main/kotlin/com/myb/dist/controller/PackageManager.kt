package com.myb.dist.controller

import com.myb.dist.DistConfig
import com.myb.dist.db.AppFile
import com.myb.dist.db.AppFileRepository
import com.myb.dist.utils.DATE_PATTERN
import com.myb.dist.utils.md5
import com.myb.dist.utils.toString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.*

@Service
class PackageManager {

    //@Autowired
    private val config = DistConfig()

    @Autowired
    private lateinit var appFileRepository: AppFileRepository

    /**
     * 保持上传上来的文件
     *
     * @return 返回文件id和File
     */
    fun save(file: MultipartFile): AppFile? {
        val originalFilename = file.originalFilename ?: return null
        val nowString = Date().toString(DATE_PATTERN)
        val destName = "${nowString}_${originalFilename}"
        val root = getRootDir()
        val dest = File(root, destName)
        dest.createNewFile()
        dest.outputStream().buffered().write(file.inputStream.buffered().readBytes())
        val fileInfo = AppFile(name = originalFilename, md5 = dest.md5(), size = dest.length(), path = destName)
        return appFileRepository.save(fileInfo)
    }

    fun save(file: File): AppFile? {
        val fileInfo = AppFile(name = file.name, md5 = file.md5(), size = file.length(), path = file.name)
        return appFileRepository.save(fileInfo)
    }

    /**
     * 查询文件
     *
     * @param fileId 文件id
     * @return 文件名和文件, 或空
     */
    fun query(fileId: Int): Optional<AppFile> {
        return appFileRepository.findById(fileId)
    }


    fun getRootDir(): File {
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

fun AppFile.getFile(root: File): File? = if (path == null) null else File(root, path)