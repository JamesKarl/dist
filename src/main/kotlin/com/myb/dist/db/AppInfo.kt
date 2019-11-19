package com.myb.dist.db

import javax.persistence.*

@Entity(name = "app_info")
data class AppInfo(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,
        val appId: String,
        val name: String,
        val type: String,
        val icon: String? = null,
        val note: String? = null
)