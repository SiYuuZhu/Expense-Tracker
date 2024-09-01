<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Accounting</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
    </head>

    <body class="root">
        <div class="content">
            <b><h1>Accounting</h1></b><br>

            <div class="func">
                <a href='logout'>登出 ${sessionScope.login}</a><br>
                <a href='new_add_record'><button id="Check1">新增紀錄</button></a>
            </div>
            <hr>
            <c:choose>
                <c:when test="${overBudget>=0}">
                    <div>本月尚餘預算${overBudget}元</div>
                </c:when>
                <c:otherwise>
                    <div>本月已超過預算${-overBudget}元</div>
                </c:otherwise>
            </c:choose>

            <p>
                <div>
                    今日餘額：${todayAmount}
                    <br> 現金總餘額：${allCash}
                    <br> 銀行總餘額：${allDeposit}
                    <br> 總餘額：${allAmount}
                </div>
                <p>

                    有${pcount}個人和你一起記帳中
        </div>


    </body>

    </html>