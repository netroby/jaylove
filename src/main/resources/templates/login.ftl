<!DOCTYPE html>
        <html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/pure/0.6.0/pure-min.css">
    <style>
        .container {
            width:50%;
            height:50%;
            margin:10% auto;
            text-align:center
        }
        .container td {
            padding:10px;
        }
    </style>
</head>
<body>
<div class="container">
<form action="/login" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Username: </td>
            <td><input name="username"></td>
        </tr>
        <tr>
            <td>Password: </td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td> <#if _csrf??>
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}" />
                </#if></td>
            <td><input type="submit" value="Login"></td>
        </tr>
    </table>
</form>
</div>
</body>
</html>