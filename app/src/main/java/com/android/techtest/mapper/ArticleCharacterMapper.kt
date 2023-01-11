package com.android.techtest.mapper

import com.android.techtest.data.mapper.BaseMapperRepository
import com.android.techtest.domain.entities.ArticleCharacter
import com.android.techtest.domain.entities.MediaCharacter
import com.android.techtest.domain.entities.MediaMetadataCharacter
import com.android.techtest.entities.ArticleData
import com.android.techtest.entities.MediaData
import com.android.techtest.entities.MediaMetadataData
import com.android.techtest.entities.ResultData

class ArticleCharacterMapper : BaseMapperRepository<ArticleCharacter, ArticleData> {

    override fun transform(type: ArticleCharacter): ArticleData =
        ArticleData(
            type.results.map { result ->
                ResultData(
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

    private fun transformToMedia(type: List<MediaCharacter>): List<MediaData> =
        type.map { MediaData(transformToMediaMetadata(it.mediaMetadata)) }

    private fun transformToMediaMetadata(type: List<MediaMetadataCharacter>): List<MediaMetadataData> =
        type.map { MediaMetadataData(it.url) }
}
