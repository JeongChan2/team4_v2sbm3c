<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9092/</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css">
</head>
<body>
<c:import url="/menu/top.do" />
 
 
<div style="width:70%; margin: 30px auto">
  <img src="/images/sam03.jpg" style="width:80%">
</div>


<jsp:include page="./menu/bottom.jsp" flush='false' /> 
</body>
</html>