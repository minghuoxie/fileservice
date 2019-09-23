<!DOCTYPE>
<html>
<head>
    <title>测试上传文件</title>
</head>
<body>
<p>上传html测试</p>
<form action="/upfile" method="post" enctype="multipart/form-data">
    <p>标题: <input type="text" name="title" /></p>
    <p>文件: <input type="file" name="file" /></p>
    <input type="submit" value="Submit" />
</form>
</body>
</html>