package io.github.cstringdev

import freemarker.cache.ClassTemplateLoader
import freemarker.core.HTMLOutputFormat
import io.github.cstringdev.data.PersistentDataService
import io.github.cstringdev.experiences.Experience
import io.github.cstringdev.experiences.ExperienceService
import io.github.cstringdev.project.Project
import io.github.cstringdev.project.ProjectService
import io.github.cstringdev.rating.LanguageRating
import io.github.cstringdev.rating.RatingService
import io.github.nosequel.data.DataHandler
import io.github.nosequel.data.connection.flatfile.FlatfileConnectionPool
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.freemarker.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.netty.*

var creatingProject = hashMapOf<String, Boolean>()

fun main(args: Array<String>)
{
    EngineMain.main(args)
}

fun Application.module()
{
    DataHandler
        .linkTypeToId<Project>("projects")
        .linkTypeToId<LanguageRating>("language_ratings")
        .linkTypeToId<Experience>("experiences")
        .withConnectionPool<FlatfileConnectionPool> {
            this.directory = "data"
        }

    val services = hashMapOf(
        "languageRatings" to RatingService,
        "projects" to ProjectService,
        "experiences" to ExperienceService
    )


    routing {
        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
            outputFormat = HTMLOutputFormat.INSTANCE
        }

        get("/") {
            call.respond(
                FreeMarkerContent(
                    "index.ftl",
                    createDataMap(services, call),
                    ""
                )
            )
        }

        post("/submit/project/create") {
            val params = call.receiveParameters()

            val name = params["name"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val language = params["language"] ?: "Unspecified"
            val maintained = params["maintained"] ?: "no"
            val description = params["description"] ?: "view github repository"

            val origin = call.request.origin
            val ip = origin.remoteHost

            creatingProject[ip] = false

            call.respondRedirect("/")
        }

        post("/submit/project/start") {
            val origin = call.request.origin
            val ip = origin.remoteHost

            creatingProject[ip] = true

            call.respondRedirect("/")
        }
    }

    for (service in services.values)
    {
        service.store()
    }
}

fun createDataMap(
    services: Map<String, PersistentDataService<*>>,
    call: ApplicationCall
): HashMap<String, Any>
{
    val origin = call.request.origin
    val ip = origin.remoteHost

    val map = hashMapOf<String, Any>(
        "languages" to "Kotlin, Rust and Java",
        "creating_project" to (creatingProject[ip] ?: false),
        "admin" to (call.request.origin.remoteHost == "0:0:0:0:0:0:0:1")
    )

    for (entry in services)
    {
        entry.value.load(); map[entry.key] = entry.value.data
    }

    return map
}