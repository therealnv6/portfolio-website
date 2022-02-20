package io.github.cstringdev.rating

import io.github.cstringdev.data.PersistentDataService
import io.github.nosequel.data.DataHandler
import io.github.nosequel.data.DataStoreType

object RatingService : PersistentDataService<LanguageRating>
{
    override val storage = DataHandler.createStoreType<String, LanguageRating>(DataStoreType.FLATFILE)
    override val data = mutableListOf<LanguageRating>()

    override fun retrieveId(key: LanguageRating): String
    {
        return key.name
    }
}