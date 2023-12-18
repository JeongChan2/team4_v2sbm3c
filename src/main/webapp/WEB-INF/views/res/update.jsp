<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import = "dev.mvc.res.ResVO" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9092/res/update.do?resno=2</title>
<link rel="shortcut icon" href="/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <%-- /static/res/style.css --%> 


</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
<div class='title_line'>카테고리 등록</div>
<%
 ResVO resVO = (ResVO)request.getAttribute("resVO");
 int resno = resVO.getResno();
%>
<form name='frm' method='post' action='/res/update.do'>
 <input type='hidden' name='resno' value='<%=resno %>'>
 <div>
  <label>식당 이름</label>
  <input type="text" name="resname" value="<%=resVO.getResname() %>" required="required" autofocus="autofocus" 
          class="form-control form-control-sm" style="width: 50%">
 </div>
 
 <div style="margin-top: 20px">
  <label>식당 주소</label>
  <input type="text" name="resaddress" value="<%=resVO.getResaddress() %>" required="required" autofocus="autofocus" 
          class="form-control form-control-sm" style="width: 50%">
 </div>
 
 <div style="margin-top: 20px">
  <label>전화 번호</label>
  <input type="text" name="resphone" value="<%=resVO.getResphone() %>" required="required" autofocus="autofocus" 
          class="form-control form-control-sm" style="width: 50%">
 </div>
 
 <div style="margin-top: 20px">
  <label>영업 시간</label>
  <input type="text" name="restime" value="<%=resVO.getRestime() %>" required="required" autofocus="autofocus" 
          class="form-control form-control-sm" style="width: 50%">
 </div>

 <div class="content_body_bottom">
  <button type="submit" class="btn btn-primary btn-sm">저장</button>
  <button type="button" onclick="history.back();" class="btn btn-primary btn-sm">취소</button>
 </div>
</form>
<jsp:include page="../menu/bottom.jsp" flush='false' />  
</body>
</html>