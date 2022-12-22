package com.android.techtest.data.mapper

import com.android.techtest.data.entities.ArticleResponse
import com.android.techtest.data.entities.Result
import com.android.techtest.domain.entities.ArticleCharacter

open class ArticleMapperService : BaseMapperRepository<ArticleResponse, ArticleCharacter> {

    override fun transform(type: ArticleResponse): ArticleCharacter =
        ArticleCharacter(
            type.copyright,
            type.numResults,
            type.results,
            type.status

        )

    override fun transformToRepository(type: ArticleCharacter): ArticleResponse =
        ArticleResponse(
            type.copyright,
            type.numResults,
            type.results as List<Result>,
            type.status
        )
}
