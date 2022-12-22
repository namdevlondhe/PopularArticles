package com.android.techtest.domain.entities
const val NOT_FOUND = "NOT FOUND"
const val DEFAULT_ID = 0

class ArticleCharacter(
    var copyright: String = NOT_FOUND,
    var numResults: Int = DEFAULT_ID,
    var results: List<Any>,
    var status: String = NOT_FOUND
)