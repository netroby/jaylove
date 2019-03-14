<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><#if exception??>${exception}</#if></title>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/default.css">
</head>
<body>
<div class="container">
    <div class="content">
        <div class="txtcenter">
            <p>There is some error</p>
            <p><#if exception??>${exception}</#if></p>
            <p><#if trace??>${trace}</#if></p>
            <p><#if url??>
                    <a href="${url}">Ok</a>
                </#if>
            </p>
        </div>
    </div>
</div>

</body>
</html>