package com.myb.dist.db

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "app_file")
data class AppFile(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,
        val name: String,
        val md5: String,
        val size: Long? = null,
        val path: String? = null
) {
}