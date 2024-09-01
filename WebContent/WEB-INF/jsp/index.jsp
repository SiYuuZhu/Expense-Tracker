<%@taglib prefix="f" uri="https://openhome.cc/jstl/fake" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Account Home</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" />
    </head>

    <body>

        <div id="login">

            <p></p>

            <f:if test="${requestScope.errors != null}">
                <ul style='color: rgb(255, 0, 0);'>
                    <f:forEach var="error" items="${requestScope.errors}">
                        <li>${error}</li>
                    </f:forEach>
                </ul>
            </f:if>


            <div class="login-page">
                <div class="form">

                    <form class="login-form" method='post' action='login'>
                        <input type="text" placeholder="username" name='username' value='${param.username}' />
                        <input type="password" placeholder="password" name='password' />
                        <button>login</button>
                        <p class="message">Not registered? <a href="register">Create an account</a></p>
                        <span class="message">Forgot password? <a href="forgot.html">Reset password</a></span>
                    </form>
                </div>
            </div>


        </div>

    </body>

    </html>