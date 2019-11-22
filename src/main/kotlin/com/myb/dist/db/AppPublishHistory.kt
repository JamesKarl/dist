package com.myb.dist.db

import java.util.*
import javax.persistence.*

@Entity(name = "app_publish_history")
data class AppPublishHistory(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,
        val appId: Int,
        val version: String,
        val note: String? = null,
        val fileId: Int,
        val count: Int,
        val publishDate: Date
)