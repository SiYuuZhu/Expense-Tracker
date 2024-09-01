<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>New Record</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addRecord.css" />

    </head>

    <body>
        <div class="addPage">
            <div class="form">
                <form method='POST' action='addR'>
                    <div class="sec1">
                        選擇記錄種類:
                        <input type="radio" name="Type" class="margin:0px" value=TRUE checked>收入
                        <input type="radio" name="Type" class="radioType" value=FALSE>支出<br>
                        <br>
                    </div>
                    <input type="text" placeholder="日期YYYYMMDD" name="tdate">
                    <select name="Category" size="1">
                        <optgroup selected="true" label="收入"> 
                        <option value="收">收入</option>
                        <optgroup selected="true" label="支出"> 
                        <option value="食">食</option>
                        <option value="衣">衣</option>
                        <option value="住">住</option>
                        <option value="行">行</option>
                        <option value="育">育</option>
                        <option value="樂">樂</option>
                    </select>

                    <div class="sec1">
                        <br> 選擇金流種類：
                        <br>
                        <input type="radio" name="AccountType" value=TRUE checked>現金
                        <input type="radio" name="AccountType" value=FALSE>銀行帳戶
                    </div>
                    <input type="text" placeholder="金額" name="Amount">
                    <div class="sec">
                        備註：<br>
                        <textarea name="Notes" rows=8 cols=47></textarea>
                        <p>
                    </div>
                    <input type="submit" class="buttonadd" value="新增紀錄">
                </form>
            </div>
        </div>
    </body>

    </html>