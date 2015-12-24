<#macro header pageTitle pageDescription>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
	<title>${pageTitle}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="title" content="Document"/>
	<meta name="keywords" content=""/>
	<meta name="description" content="${pageDescription}"/>
	<link rel="icon" href="icon/favicon.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="icon/favicon.ico" type="image/x-icon"/>

        
    <link rel="stylesheet" href="css/styles.css">

    <style>
        /*these styles allow dhtmlxLayout to work in full screen mode in different browsers correctly*/
        html, body {
            width: 100%;
            height: 100%;
            margin: 0 auto;
            overflow: hidden;
            background-color:#EEEEEE;
        }
    </style>

    
	<script src="js/jquery/jquery-2.1.4.min.js"></script>

    <#nested>
</head>
</#macro>

<#macro body>
  <body>
  <#nested>
</#macro>

<#macro footer>
    <#nested>
  </body>
</html>
</#macro> 