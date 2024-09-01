<!DOCTYPE html>
<html>

<head>
    <meta charset='UTF-8'>
    <title>成功送出表單</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register_success.css" />
</head>

<body class="root">
    <div class="content">
        <h1>${param.username} 成功送出申請表單</h1>
        請至填寫之信箱 ${param.email} 查看申請結果。<br><br>
        <a href='/gossip'>回首頁</a>
    </div>
</body>

</html>