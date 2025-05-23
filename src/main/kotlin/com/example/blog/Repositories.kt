package com.example.blog

import org.springframework.data.mongodb.repository.MongoRepository

interface ArticleRepository : MongoRepository<Article, Long> {
	fun findBySlug(slug: String): Article?
	fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}

interface UserRepository : MongoRepository<User, Long> {
	fun findByLogin(login: String): User?
}

