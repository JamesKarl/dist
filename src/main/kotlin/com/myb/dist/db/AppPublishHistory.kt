package com.myb.dist.db

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class AppPublishHistory(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int,
        val appId: Int,
        val version: String,
        val note: String? = null,
        val url: String,
        val size: Double,
        val count: Int,
        val publishDate: Date
)