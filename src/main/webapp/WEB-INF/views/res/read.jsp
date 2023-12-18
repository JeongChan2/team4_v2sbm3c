<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import = "dev.mvc.res.ResVO" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9092/res/read.do?resno=2</title>
<link rel="shortcut icon" href="/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <%-- /static/css/style.css --%> 


</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
<div class='title_line'>카테고리 조회</div>
<%
ResVO resVO = (ResVO)request.getAttribute("resVO");
%>
<div class="container mt-3">
  <ul class="list-group list-group-flush">
    <li class="list-group-item">번호: <%=resVO.getResno() %></li>
    <li class="list-group-item">이름: <%=resVO.getResname() %></li>
    <li class="list-group-item">주소: <%=resVO.getResaddress() %></li>
    <li class="list-group-item">전화번호: <%=resVO.getResphone() %></li>
    <li class="list-group-item">영업시간: <%=resVO.getRestime() %></li>
    <li class="list-group-item">별점: <%=resVO.getResstar() %></li>
  </ul>
</div>
 <div class="content_body_bottom">
  <button type="button" onclick="location.href='./create.do'" class="btn btn-primary btn-sm">등록</button>
  <button type="button" onclick="location.href='./list_all.do'" class="btn btn-primary btn-sm">목록</button>
 </div>
<jsp:include page="../menu/bottom.jsp" flush='false' />  
</body>
</html>