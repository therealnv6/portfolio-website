package io.github.cstringdev.project

import com.google.gson.JsonParser
import java.net.URL

class Project(
    val name: String,
    val displayName: String,
    val description: String,
    val tags: List<String>,
    val github: Boolean = false,
    version: String = "not found"
)
{
    val version: String = version
        get()
        {
            if (this.github)
            {
                val url = "https://jitpack.io/api/builds/com.github.devrawr/${name}"
                val text = URL(url).readText()

                val json = JsonParser.parseString(text).asJsonObject
                val builds = json["com.github.devrawr"].asJsonObject[name].asJsonObject

                for (entry in builds.entrySet().reversed())
                {
                    if (entry.value.asString == "ok")
                    {
                        return entry.key
                    }
                }

                return field
            }

            return field
        }
}