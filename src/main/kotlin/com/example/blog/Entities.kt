package com.example.blog

import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "articles")
class Article(
		@Id val id: String? = null,
		var title: String,
		var headline: String,
		var content: String,
		var firstname: String? = null,
		var slug: String = title.toSlug(),
		var addedAt: LocalDateTime = LocalDateTime.now())

@Document(collection = "users")
class User(
		@Id val id: String? = null,
		var login: String,
		var firstname: String,
		var lastname: String,
		var description: String? = null)
