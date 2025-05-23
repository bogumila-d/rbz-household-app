package com.example.blog

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

	@Bean
	fun databaseInitializer(userRepository: UserRepository,
							articleRepository: ArticleRepository) = ApplicationRunner {

		println("🔌 Connecting to MongoDB...")
		println("User count: ${userRepository.count()}")
		println("Article count: ${articleRepository.count()}")
		userRepository.findAll()

		println("📰 Fetching articles from MongoDB:")
		articleRepository.findAll()
	}
}
