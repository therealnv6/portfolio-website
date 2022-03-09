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

    <div class="section is-large">
        <div class="box">
            <form action="/loginlocal" method="post">
                <div class="field">
                    <label class="label">Username</label>
                    <div class="control">
                        <input class="input" name="username" type="name" placeholder="e.g. cstring">
                    </div>
                </div>

                <div class="field">
                    <label class="label">Password</label>
                    <div class="control">
                        <input class="input" name="password" type="password" placeholder="*******">
                    </div>
                </div>
                
                <button class="button is-info">Login</button>
            </form>            
        </form>
    </div>
</html