<%@taglib prefix="f" uri="https://openhome.cc/jstl/fake" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css" />
        <title>會員申請</title>
        <style>
            @import url(https://fonts.googleapis.com/css?family=Noto+Sans+TC|Open+Sans&display=swap);
            @import url(https://fonts.googleapis.com/css?family=Roboto:300);
            .all {
                width: 600px;
                padding: 8% 0 0;
                margin: auto;
            }
            
            h1 {
                color: rgb(79, 117, 119);
            }
            
            .form {
                position: relative;
                z-index: 1;
                background: #FFFFFF;
                max-width: 360px;
                margin: 0 auto 100px;
                padding: 45px;
                text-align: center;
                box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
                border-radius: 10px;
            }
            
            .form input {
                font-family: 'Noto Sans TC', sans-serif;
                outline: 0;
                background: #f2f2f2;
                width: 100%;
                border: 0;
                margin: 0 0 15px;
                padding: 15px;
                box-sizing: border-box;
                font-size: 14px;
            }
            
            .form .buttonR {
                font-family: 'Noto Sans TC', sans-serif;
                text-transform: uppercase;
                outline: 0;
                background: #77AAAD;
                width: 100%;
                border: 0;
                padding: 15px;
                color: #FFFFFF;
                font-size: 14px;
                -webkit-transition: all 0.3 ease;
                transition: all 0.3 ease;
                cursor: pointer;
                border-radius: 10px;
            }
            
            .form button:hover,
            .form button:active,
            .form button:focus {
                background: #7F8894;
            }
            
            .form select {
                font-family: 'Noto Sans TC', sans-serif;
            }
            
            body {
                background: linear-gradient(to left, #D8E6E7, #77AAAD);
                font-family: 'Noto Sans TC', sans-serif;
                -webkit-font-smoothing: antialiased;
                -moz-osx-font-smoothing: grayscale;
            }
        
        
        
        </style>
        
        
    </head>

    <body class="all">
        <div class="form">
            <h1>會員申請</h1>

            <f:if test="${requestScope.errors != null}">
                <ul style='color: rgb(255, 0, 0);'>
                    <f:forEach var="error" items="${requestScope.errors}">
                        <li>${error}</li>
                    </f:forEach>
                </ul>
            </f:if>

            <form method='post' action='register'>
                <input type='text' name='email' value='${param.email}' placeholder="Email" size='25' maxlength='100'>
                <input type='text' name='username' placeholder="名稱（最大 16 字元）" value='${param.username}' size='25' maxlength='16'>
                <input type='password' name='password' placeholder="密碼（8 到 16 字元）" size='25' maxlength='16'>
                <input type='password' name='password2' placeholder="確認密碼" size='25' maxlength='16'>
                <input type='text' name='budget' placeholder="月固定預算(不可超過16位數)" size='25' maxlength='16'>

                <tr>
                    <td colspan='2' align='center'><input type='submit' class="buttonR" value='註冊'></td>
                </tr>
            </form>
        </div>

    </body>

    </html>