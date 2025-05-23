package com.example.blog

import org.springframework.http.HttpStatus.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Controller
class HtmlController(private val repository: ArticleRepository,
					 private val properties: BlogProperties) {

	@GetMapping("/")
	fun blog(model: Model): String {
		model["title"] = properties.title
		model["banner"] = properties.banner
		model["articles"] = repository.findAllByOrderByAddedAtDesc().map { it.render() }
		return "blog"
	}

	@GetMapping("/article/{slug}")
	fun article(@PathVariable slug: String, model: Model): String {
		val article = repository
				.findBySlug(slug)
				?.render()
				?: throw ResponseStatusException(NOT_FOUND, "This article does not exist")
		model["title"] = article.title
		model["article"] = article
		return "article"
	}

	@PostMapping("/addArticles")
	fun submitArticle(
		@RequestParam title: String,
		@RequestParam headline: String,
		@RequestParam content: String,
		@RequestParam slug: String,
		@RequestParam addedAt: String,
		@RequestParam firstname: String
	): String {
		val formatter = DateTimeFormatter.ISO_DATE_TIME
		val article = Article(
			title = title,
			headline = headline,
			content = content,
			slug = slug,
			addedAt = LocalDateTime.parse(addedAt, formatter),
			firstname = firstname
		)
		repository.save(article)
		return "redirect:/"
	}

	fun Article.render() = RenderedArticle(
			slug,
			title,
			headline,
			content,
			firstname,
			addedAt.format()
	)

	data class RenderedArticle(
			val slug: String,
			val title: String,
			val headline: String,
			val content: String,
			var firstname: String? = null,
			val addedAt: String)

}
