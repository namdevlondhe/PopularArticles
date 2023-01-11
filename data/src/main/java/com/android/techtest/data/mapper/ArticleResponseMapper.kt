package com.android.techtest.data.mapper

import com.android.techtest.data.entities.ArticleResponse
import com.android.techtest.data.entities.Media
import com.android.techtest.data.entities.MediaMetadata
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
                    transformToMedia(result.media),
                    result.publishedDate,
                    result.section,
                    result.title,
                    result.url
                )
            })

    private fun transformToMedia(type: List<Media>): List<MediaCharacter> =
        type.map { MediaCharacter(transformToMediaMetadata(it.mediaMetadata)) }

    private fun transformToMediaMetadata(type: List<MediaMetadata>): List<MediaMetadataCharacter> =
        type.map { MediaMetadataCharacter(it.url) }
}