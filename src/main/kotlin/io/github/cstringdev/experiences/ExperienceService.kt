package io.github.cstringdev.experiences

import io.github.cstringdev.data.PersistentDataService
import io.github.nosequel.data.DataHandler
import io.github.nosequel.data.DataStoreType
import io.github.nosequel.data.store.StoreType

object ExperienceService : PersistentDataService<Experience>
{
    override val storage = DataHandler.createStoreType<String, Experience>(DataStoreType.FLATFILE)
    override val data = mutableListOf(
        Experience(
            id = "chasecraft-mc",
            name = "Chasecraft MCS LLC",
            jobTitle = "Content & Backend Developer",
            logo = "https://pbs.twimg.com/profile_images/1477480101029683200/WwC3bhRw_400x400.jpg",
            description = "Developer in the first quarter of 2021."
        )
    )

    override fun retrieveId(key: Experience): String
    {
        return key.id
    }
}