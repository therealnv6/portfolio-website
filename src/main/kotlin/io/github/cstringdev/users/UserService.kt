package io.github.cstringdev.users

import io.github.cstringdev.data.PersistentDataService
import io.github.nosequel.data.DataHandler
import io.github.nosequel.data.DataStoreType
import io.ktor.application.*
import io.ktor.features.*
import kotlin.collections.HashMap
import kotlin.collections.firstOrNull
import kotlin.collections.hashMapOf
import kotlin.collections.mutableListOf
import kotlin.collections.set

object UserService : PersistentDataService<User>
{
    override val storage = DataHandler.createStoreType<String, User>(DataStoreType.FLATFILE)
    override val data = mutableListOf<User>()

    private val loggedIn = hashMapOf<String, String>()

    private val loggedInStorage = DataHandler.createStoreType<String, HashMap<String, String>>(DataStoreType.FLATFILE) {
        this.id = "loggedIn"
    }

    override fun retrieveId(key: User): String
    {
        return key.username
    }

    fun login(
        user: User,
        call: ApplicationCall
    )
    {
        loggedIn[call.request.origin.remoteHost] = user.username
    }

    fun getUser(
        call: ApplicationCall
    ): User?
    {
        return this.data.firstOrNull { it.username == (loggedIn[call.request.origin.remoteHost] ?: "") }
    }

    override fun load()
    {
        super.load()

        this.loggedIn.clear()

        this.loggedInStorage.retrieveAll {
            this.loggedIn.putAll(it)
        }
    }

    override fun store()
    {
        super.store()
        this.loggedInStorage.store("global", this.loggedIn)
    }
}