package com.myb.dist.db

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
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