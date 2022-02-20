package io.github.cstringdev.project

import io.github.cstringdev.data.PersistentDataService
import io.github.nosequel.data.DataHandler
import io.github.nosequel.data.DataStoreType

object ProjectService : PersistentDataService<Project>
{
    override val storage = DataHandler.createStoreType<String, Project>(DataStoreType.FLATFILE)
    override val data = mutableListOf<Project>()

    override fun retrieveId(key: Project): String
    {
        return key.name
    }
}