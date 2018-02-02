<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>AJAX请求示例</title>

    <script type="text/javascript" src="../static/js/jquery-1.9.0.js"></script>
<#--<script type="text/javascript" src="../static/javascript/application.js"></script>-->

    <script type="text/javascript">
        $(document).ready(function () {
            $("#btnSubmit").bind("click", function () {
                $("#result").val("");
                $.ajax({
                    type: "post",
                    async: true,
                    url: "/test17",
                    contentType: "application/x-www-form-urlencoded",
                    dataType: "json",
                    data: $("#formData").serialize(),
                    success: function (json) {
                        console.info(json);
                        $("#result").val(JSON.stringify(json));
                    },
                    error: function (e) {
                        $("#result").val("");
                        console.info(e);
                    }
                });
            });
        });

    </script>

</head>
<body>
<form id="formData">
    <table>
        <tr>
            <td>请求参数：</td>
            <td style="width:179px;"></td>
        </tr>

        <tr>
            <td><input type="text" id="inputPara" value="请求参数" style="width: 200px;padding: 15px;"></td>
            <td><input type="button" id="btnSubmit" value="提交" style="width: 200px;padding: 15px;"></td>
        </tr>

        <tr>
            <td nowrap>返回结果：</td>
            <td colspan="2"><textarea id="result" size="40" rows="20" style="width: 791px; height: 333px;"> </textarea>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
