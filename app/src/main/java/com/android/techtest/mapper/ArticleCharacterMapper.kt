package com.android.techtest.mapper

import com.android.techtest.data.mapper.BaseMapperRepository
import com.android.techtest.domain.entities.ArticleCharacter
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
                    result.media.map {
                        MediaData(
                            it.mediaMetadata.map { metadataMapper ->
                                MediaMetadataData(
                                    metadataMapper.url,
                                )
                            },
                        )
                    },
                    result.publishedDate,
                    result.section,
                    result.title,
                    result.url
                )
            })
}