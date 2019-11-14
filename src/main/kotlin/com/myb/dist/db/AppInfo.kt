package com.myb.dist.db

import javax.persistence.*

@Entity(name = "app_info")
data class AppInfo(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int,
        val appId: String,
        val name: String,
        val type: String,
        val icon: String? = null,
        val note: String? = null
)