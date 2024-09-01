<%@taglib prefix="f" uri="https://openhome.cc/jstl/fake" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>重設密碼</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset_password.css" />

    </head>

    <body class="all">
        <div class="form">
            <h1>重設密碼</h1>

            <f:if test="${requestScope.errors != null}">
                <ul style='color: rgb(255, 0, 0);'>
                    <f:forEach var="error" items="${requestScope.errors}">
                        <li>${error}</li>
                    </f:forEach>
                </ul>
            </f:if>

            <form method='post' action='reset_password'>
                <input type='hidden' name='name' value='${requestScope.acct.name}'>
                <input type='hidden' name='email' value='${requestScope.acct.email}'>
                <input type='hidden' name='token' value='${sessionScope.token}'>
                <input type='password' name='password' placeholder="新密碼（8 到 16 字元）" size='25' maxlength='16'>
                <input type='password' name='password2' placeholder="確認新密碼" size='25' maxlength='16'>

                <tr>
                    <td colspan='2' align='center'><input type='submit' class="buttonR" value='確定'></td>
                </tr>
            </form>
        </div>

    </body>

    </html>