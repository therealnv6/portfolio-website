package io.github.cstringdev.experiences

import io.github.cstringdev.data.PersistentDataService
import io.github.nosequel.data.DataHandler
import io.github.nosequel.data.DataStoreType
import io.github.nosequel.data.store.StoreType

object ExperienceService : PersistentDataService<Experience>
{
    override val storage = DataHandler.createStoreType<String, Experience>(DataStoreType.FLATFILE)
    override val data = mutableListOf<Experience>()

    override fun retrieveId(key: Experience): String
    {
        return key.id
    }
}