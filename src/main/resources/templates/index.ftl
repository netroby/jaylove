<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><#if siteName??>${siteName}</#if> - powered by jaylove!</title>
    <meta name="description" content="{{.site_description}}"/>
    <link href="/rss" rel="alternate" type="application/rss+xml" title="daylove"/>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/default.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/tinymce/4.2.7/tinymce.min.js"></script>
    <script type="text/javascript">
        tinymce.PluginManager.add('prettycode', function (editor, url) {
            // Add a button that opens a window
            editor.addButton('prettycode', {
                text: 'PrettyCode',
                icon: false,
                onclick: function () {
                    // Open window
                    editor.windowManager.open({
                        title: 'PrettyCode',
                        body: [
                            {
                                type: 'textbox',
                                name: 'sourcecode',
                                multiline: true,
                                minWidth: 600,
                                minHeight: 500
                            }
                        ],
                        onsubmit: function (e) {
                            // Insert content when the window form is submitted
                            editor.insertContent('<pre class="prettyprint">' + e.data.sourcecode + '</pre>');
                        }
                    });
                }
            });
        });
    </script>
    <script>
        tinymce.init({
            selector: "textarea",
            theme: "modern",
            plugins: [
                "advlist autolink lists link image charmap print preview hr anchor pagebreak",
                "searchreplace wordcount visualblocks visualchars fullscreen",
                "nonbreaking save table contextmenu directionality",
                "template paste textcolor colorpicker textpattern prettycode code"
            ],
            toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | prettycode link image |  forecolor backcolor ",
            image_advtab: true,
            extended_valid_elements: "pre[class],img[class|src|border=0|alt|title|hspace|vspace|width|height|align|onmouseover|onmouseout|name]"
        });
    </script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<div class="container">

    <div class="content">
        <div class="">
            <a href="/"><#if siteName??>${siteName}</#if></a>
        </div>

        <#if username??>

        <div style="float:right">
            Welcome ${username}
            <a href="/admin/logout">Logout</a>
        </div>
        <div style="width:92%;margin: 0px auto">
        <form action="/admin/save-blog-add" method="post" enctype="multipart/form-data" class="form-horizontal" role="form">
            <div class="form-group">
                <label>Content:</label>
                <textarea name="content" class="form-control"></textarea></div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit">Save</button>
                </div>
            </div>
        </form>
        </div>
        </#if>
        <div style="padding:0px 12px">
            <#if content??>${content}</#if>
        </div>
        <div class="txtcenter">

            <div class="paginav">
                <div style="float:left;width:150px;text-align:center"><a href="/?page=<#if nextPage??>${nextPage}</#if>">Next page</a>
                </div>
                <div style="float:left;width:150px;text-align:center"><a href="/?page=<#if prevPage??>${prevPage}</#if>">Previous
                    Page</a></div>
            </div>

        </div>
        <div class="clearfix h10"></div>
        <div class="txtcenter">Powered by <a href="//github.com/netroby/jaylove">jaylove</a>
        </div>
    </div>
</div>
</body>
</html>
