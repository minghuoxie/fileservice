<!DOCTYPE>
<html>
<head>
    <!--#include "../components/base.ftl" >-->
    <title>freemarker</title>
</head>
<body >
<div class="wrap_content">
    <div class="container">
        <div class="crumbs">
            当前位置 ${from!''}
        </div>
        <div class="content">
            ${htmlFormat!''}
        </div>
    </div>
</div>
</body>
</html>