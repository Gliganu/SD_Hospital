<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<title>
		<tiles:insertAttribute name="title"></tiles:insertAttribute>
	</title>
	
	
	<c:url var="CSS_URL" value="/static/css/main.css"></c:url>
	<link href="${CSS_URL}" rel="stylesheet" type="text/css" />
	
	<c:url var="SOCKJS_URL" value="/static/script/sockjs-0.3.4.js"></c:url>
	<script type="text/javascript" src="${SOCKJS_URL}"></script>
	
	<c:url var="STOMPJS_URL" value="/static/script/stomp.js"></c:url>
	<script type="text/javascript" src="${STOMPJS_URL}"></script>
	
	<c:url var="JAVASCRIPT_URL" value="/static/script/jquery.js"></c:url>
	<script type="text/javascript" src="${JAVASCRIPT_URL}"></script>
    
    <script type="text/javascript">
    
        var stompClient = null;
        
        function init(){
        	connect();
        	setInterval(function() {
        		/*    stompClient.send("/app/hello", {}, JSON.stringify({ 'name': 'GLIGAA' })); */
        		 stompClient.send("/app/hello", {}, {});
        		}, 1000 * 15);  
        }
        
        function connect() {
            var socket = new SockJS("<c:url value='/hello'/>");
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/greetings', function(greeting){
                	
                	 if(JSON.parse(greeting.body).message.length > 0){
                	 	alert(JSON.parse(greeting.body).message);
                	 }

                });
            });
        }

    </script>
    
	<tiles:insertAttribute name="includes"></tiles:insertAttribute>

</head>
<body onload="init();">


	<div class="header">
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</div>

	<tiles:insertAttribute name="toolbar"></tiles:insertAttribute>

	<div class="content">
		<tiles:insertAttribute name="content"></tiles:insertAttribute>
	</div>

	<div class="footer">
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>

</body>
</html>