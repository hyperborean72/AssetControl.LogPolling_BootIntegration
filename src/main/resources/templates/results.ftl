<!DOCTYPE html>
 
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
</head>
<body>
	<div class="jumbotron" id="resultsDiv">
    </div>
   	<div style="display:none;">
   	<div id="test">
   		<p>Errors: ${errors}</p>
   		<p>Warnings: ${warnings}</p>
   		<p>Info: ${infos}</p>
   	</div>
   	</div>
    <script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {

        $.ajaxSetup({ cache: false });
        $("#resultsDiv").load("#test");
        setInterval(function() {$("#resultsDiv").load("#test"); }, ${interval});

        });
    </script>
</body>
</html>