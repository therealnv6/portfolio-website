package io.github.cstringdev.data

import io.github.nosequel.data.store.StoreType

interface PersistentDataService<T>
{
    val storage: StoreType<String, T>
    val data: MutableList<T>

    fun retrieveId(key: T): String

    fun load()
    {
        this.data.clear()
        this.storage.retrieveAll {
            this.data.add(it)
        }
    }

    fun store()
    {
        for (persistentDatum in this.data)
        {
            this.storage.store(retrieveId(persistentDatum), persistentDatum)
        }
    }
}