package com.android.techtest.data.mapper

import com.android.techtest.data.entities.ArticleResponse
import com.android.techtest.domain.entities.ArticleCharacter
import com.android.techtest.domain.entities.MediaCharacter
import com.android.techtest.domain.entities.MediaMetadataCharacter
import com.android.techtest.domain.entities.ResultCharacter

class ArticleResponseMapper : BaseMapperRepository<ArticleResponse, ArticleCharacter> {

    override fun transform(type: ArticleResponse): ArticleCharacter =
        ArticleCharacter(
            type.results.map { result ->
                ResultCharacter(
                    result.abstract,
                    result.byline,
                    result.id,
                    result.media.map {
                        MediaCharacter(
                            it.mediaMetadata.map { metadataMapper ->
                                MediaMetadataCharacter(
                                    metadataMapper.url,
                                )
                            },
                        ) },
                    result.nytdsection,
                    result.publishedDate,
                    result.section,
                    result.url
                )
            })
}