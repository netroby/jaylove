<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><#if exception??>${exception.message}</#if></title>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/default.css">
</head>
<body>
<div class="container">
    <div class="content">
        <div class="txtcenter">
            <p><#if exception??>${exception.message}</#if></p>
            <p><#if url??>
                    <a href="${url}">Ok</a>
                </#if>
            </p>
        </div>
    </div>
</div>

</body>
</html>