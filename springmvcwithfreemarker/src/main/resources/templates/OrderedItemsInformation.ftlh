<#ftl output_format="HTML" auto_esc=false>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html">
    <title>${userName} orders</title>
</head>
<body>
<h1>
    ${userName} orders
</h1>
<hr/>
${orderedDisplayInformation}
<hr/>
${orderedItemInformation}
</body>
</html>