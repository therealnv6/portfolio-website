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
                            
            <a class="navbar-item" href="https://github.com/devrawr">
                <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Octicons-mark-github.svg/2048px-Octicons-mark-github.svg.png" width="16" height="16">
            </a>

            <a class="navbar-item" href="https://twitter.com/rawrdev">
                <img src="https://upload.wikimedia.org/wikipedia/sco/thumb/9/9f/Twitter_bird_logo_2012.svg/1200px-Twitter_bird_logo_2012.svg.png" width="16" height="16">
            </a>
        </div>
    </nav>

    <section class="hero is-dark">
        <div class="hero-body">
            <div class="container has-shadow">
                <div class="columns">
                    <div class="column is-8">
                        <h1 class="is-size-1 title">full-time developer</h1>
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

    <section class="hero">
        <div class="hero-body">
            <div class="columns">
                <div class="column is-8 px-6">
                    <h1 class="is-size-1 title">About Me</h1>
                    I'm a <strong>16-year-old</strong> programming enthousiast who loves to play with
                    programming languages, operating systems, kernels, data, physics, and everything in between.
                    I've been programming ever since I was 10, and have since made many unique projects.
                    In the past I've worked with many different technologies, 
                    such as <a href="spigotmc.org">Spigot</a>, <a href="https://wiki.archlinux.org/title/xorg">Xorg</a>,
                    <a href="https://www.mongodb.com/">MongoDB</a>, <a href="https://redis.io/">Redis</a>, and many more. 
                </div>

                <div class="column is-2">
                    <#list languageRatings as language>
                        <h2 class="is-size-4 subtitle">${language.name} - ${language.years} years</h2>
                        <progress class="progress is-small" value="${language.rating}" max="100">${language.rating}%</progress>
                    </#list>
                </div>
            </div>
        </div>
    </section>

    <section class="hero is-dark">
        <div class="hero-body">
            <h1 class="is-size-1 title">Projects</h1>
            <h2 class="is-size-4 subtitle">This is a list of projects I've previously worked on, or am still working on.</h2>
        
            <#list projects as project>
                <div class="tile is-parent">
                    <article class="tile is-child notification">
                        <p class="title">${project.displayName}</p> 
                        <p class="subtitle">${project.description}</p>

                        <span class="tag is-info">${project.language}</span>
                        <span class="tag is-info">${project.maintained}</span>
                        <span class="tag is-info">${project.version}</span>
                    </article>
                </div>
            </#list>
        </div>
    </section>

    <footer class="footer">
        <div class="content has-text-centered">
            <p>
                Website created by <strong>cstring</strong>, written using <a href="https://ktor.io/">Ktor</a> and <a href="https://freemarker.apache.org/">FreeMarker</a>
            </p>
        </div>
    </footer>
</html>