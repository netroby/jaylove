<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><#if message??>${message}</#if> - powered by jaylove</title>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/pure/0.6.0/pure-min.css">
    <style>
        body {
            margin: 0px;
            padding: 0px;
        }

        .clearfix {
            clear: both
        }

        .message-container {
            width: 50%;
            height: 50%;
            margin: 10% auto;
            text-align: center
        }

        .back-to-home {
            width: 100px;
            height: 24px;
            margin: 10px;
            float: right;
        }

    </style>
</head>
<body>
<div class="back-to-home"><a href="/">Home</a></div>
<div class="clearfix"></div>
<div class="message-container">
    <p><#if message??>${message}</#if></p>

    <p><#if url??><a href="${url}">Ok</a></#if></p>
</div>

</body>
</html>