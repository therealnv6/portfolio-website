<!DOCTYPE html>
<meta charset="UTF-8">
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    </head>

    <nav class="navbar is-dark">
        <div class="navbar-brand">
            <a class="navbar-item">
                <p style="max-height: 70px; font-size: 25px" class="py-4 px-4">cstring</p>
            </a>
        </div>

        <div class="navbar-end">
            <#if loggedIn>
                <div class="navbar-item has-dropdown is-hoverable">
                    <a class="navbar-link" href="">
                        <p class="is-danger">${user.username}</p>
                    </a>

                    <div class="navbar-dropdown is-boxed">
                        <a class="navbar-item" href="logout">
                            Logout
                        </a>
                    </div>
                </div>
            </#if>

            <div class="navbar-item">
                <div class="buttons">
                    <a href="/register">
                        <div class="button is-primary" style="margin-right: 10px">
                            <strong>Sign up</strong>
                        </div>
                    </a>

                    <a href="/login">
                        <div class="button is-light">
                            Log in
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </nav>

    <section class="hero is-dark is-medium">
        <div class="hero-body">
            <div class="container has-shadow">
                <div class="columns">
                    <div class="column is-8">
                        <h1 class="is-size-1 title">professional developer</h1>
                        <h2 class="is-size-2 subtitle">${languages}</h1>
                        <p>Providing the cleanest and cheapest code, while keeping it very maintainable, performant and flexible.</p>
                    </div>

                    <div class="column is-4">
                        <img src="https://i.imgur.com/W8kzVCw.png" height="450" width="450" cllass="px-6">
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="section is-medium">
        <div class="columns">
            <div class="column is-8 px-6">
                <h1 class="is-size-1 title">About Me</h1>
                <p class="is-size-5">
                    I'm a <strong>16-year-old</strong> programming enthousiast who loves to play with
                    programming languages, operating systems, kernels, data, physics, and everything in between (however I hate, and suck at, designing).
                    I've been programming ever since I was 10, and have since made many unique projects.
                    In the past I've worked with many different technologies, 
                    such as <a href="https://spigotmc.org">Spigot</a>, <a href="https://wiki.archlinux.org/title/xorg">Xorg</a>,
                    <a href="https://www.mongodb.com/">MongoDB</a>, <a href="https://redis.io/">Redis</a>, and many more.
                </p> 
            </div>

            <div class="column is-2">
                <#list languageRatings as language>
                    <h2 class="is-size-4 subtitle">${language.name} - ${language.years} years</h2>
                    <progress class="progress is-small" value="${language.rating}" max="100">${language.rating}%</progress>
                </#list>
            </div>
        </div>
    </section>

    <section class="hero is-dark">
        <div class="hero-body">
            <div class="columns">
                <div class="column">
                    <#list projects as project>
                        <div class="box">
                            <p style="color: #363636" class="title">${project.displayName}</p> 
                            <p style="color: #363636" class="subtitle">${project.description}</p>

                            <div class="field is-grouped is-grouped-multiline">
                                <#list project.tags as tag>
                                    <#if tag?contains("%git_version%")>
                                        <div class="control">
                                            <div class="tags has-addons">
                                                <span class="tag is-dark">jitpack</span>
                                                <span class="tag is-info">${project.version}</span>
                                            </div>
                                        </div>
                                    <#else>
                                        <div class="control">
                                            <span class="tag is-info">${tag}</span>
                                        </div>
                                    </#if>
                                </#list>
                            </div>
                        </div>
                    </#list>
                </div>

                <div class="column is-6">
                    <h1 style="margin-top: 200px" class="is-size-1 py-6 title">Projects</h1>
                    <h2 class="subtitle">This is a list of projects I've previously worked on, or am still working on. These are the most mentiontionable projects and/or famous projects.</h2>        
                
                    <#if admin>
                        <a href="/addproject">
                            <button class="button is-info">Add Project</button>
                        </a>
                    </#if>
                </div>
            </div>
        </div>
    </section>

    <section class="section">
        <h1 class="is-size-1 title">Previous Clients</h1>
        <h2 class="subtitle">I've worked for many different companies in the past, including the following</h2>
        <#list experiences as experience>
            <article class="media">
                <figure class="media-left">
                    <p class="image is-64x64">
                        <img src="${experience.logo}">  
                    </p>
                </figure>        
                <div class="media-content">
                    <div class="content">
                        <p>
                            <strong>${experience.name}</strong> <small>${experience.jobTitle}</small>
                        </p>
                        ${experience.description}
                    </div>
                </div>
            </article>
        </#list>
    </section>

    <footer class="footer">
        <div class="content has-text-centered">
            <p>
                Website created by <strong>cstring</strong>, using <a href="https://ktor.io/">Ktor</a> and <a href="https://freemarker.apache.org/">FreeMarker</a>
            </p>
                           
            <a href="https://github.com/devrawr">
                <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Octicons-mark-github.svg/2048px-Octicons-mark-github.svg.png" width="16" height="16">
            </a>

            <a href="https://twitter.com/rawrdev">
                <img src="https://upload.wikimedia.org/wikipedia/sco/thumb/9/9f/Twitter_bird_logo_2012.svg/1200px-Twitter_bird_logo_2012.svg.png" width="16" height="16">
            </a>
        </div>
    </footer>
</html>