package io.github.cstringdev

import freemarker.cache.ClassTemplateLoader
import freemarker.core.HTMLOutputFormat
import io.github.cstringdev.data.PersistentDataService
import io.github.cstringdev.encryption.Encryption
import io.github.cstringdev.experiences.Experience
import io.github.cstringdev.experiences.ExperienceService
import io.github.cstringdev.project.Project
import io.github.cstringdev.project.ProjectService
import io.github.cstringdev.rating.LanguageRating
import io.github.cstringdev.rating.RatingService
import io.github.cstringdev.users.User
import io.github.cstringdev.users.UserService
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
        .linkTypeToId<User>("users")
        .withConnectionPool<FlatfileConnectionPool> {
            this.directory = "data"
        }

    val services = hashMapOf(
        "languageRatings" to RatingService,
        "projects" to ProjectService,
        "experiences" to ExperienceService,
        "users" to UserService
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

        get("/register") {
            call.respond(
                FreeMarkerContent(
                    "register.ftl",
                    createDataMap(services, call),
                    ""
                )
            )
        }

        get("/login") {
            call.respond(
                FreeMarkerContent(
                    "login.ftl",
                    createDataMap(services, call),
                    ""
                )
            )
        }

        get("/addproject") {
            call.respond(
                FreeMarkerContent(
                    "addproject.ftl",
                    createDataMap(services, call),
                    ""
                )
            )
        }

        post("/createaccount") {
            val params = call.receiveParameters()

            val username = params["username"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val password = params["password"] ?: return@post call.respond(HttpStatusCode.BadRequest)

            if (
                UserService.data.any {
                    it.username == username
                }
            )
            {
                return@post call.respond(HttpStatusCode.BadRequest)
            }

            val user = User(
                username = username,
                password = password
            )

            UserService.data.add(user)
            UserService.login(user, call)

            call.respondRedirect("/")
        }

        post("/loginlocal") {
            val params = call.receiveParameters()

            val username = params["username"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val password = params["password"] ?: return@post call.respond(HttpStatusCode.BadRequest)

            val user = UserService.data.firstOrNull {
                it.username == username
            } ?: return@post call.respond(HttpStatusCode.BadRequest)

            if (!user.matchesPassword(password))
            {
                return@post call.respond(HttpStatusCode.BadRequest)
            }

            UserService.login(user, call)
            call.respondRedirect("/")
        }

        post("/addproject") {
            val params = call.receiveParameters()

            val id = params["id"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val display = params["display"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val description = params["description"] ?: return@post call.respond(HttpStatusCode.BadRequest)

            ProjectService.data.add(
                Project(
                    name = id,
                    displayName = display,
                    description = description,
                    github = id.contains("git-"),
                    tags = emptyList(),
                )
            )

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
        "admin" to (UserService.getUser(call)?.admin ?: false),
        "loggedIn" to (UserService.getUser(call) != null),
    )

    if (UserService.getUser(call) != null)
    {
        map["user"] = UserService.getUser(call)!!
    }

    for (service in services.values)
    {
        service.store()
    }

    for (entry in services)
    {
        entry.value.load(); map[entry.key] = entry.value.data
    }

    return map
}