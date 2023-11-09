<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9092/</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>

</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />

<div class='title_line'>식당 등록</div>

<form name='frm' method='post' action='/res/create.do'>
 <div>
  <label>식당 이름</label>
  <input type="text" name="resname" value="" required="required" autofocus="autofocus" 
          class="form-control form-control-sm" style="width: 50%">
 </div>
 <div>
  <label>식당 주소</label>
  <input type="text" name="resaddress" value="" required="required" autofocus="autofocus" 
          class="form-control form-control-sm" style="width: 50%">
 </div>
 <div>
  <label>식당 번호</label>
  <input type="text" name="resphone" value="" required="required" autofocus="autofocus" 
          class="form-control form-control-sm" style="width: 50%">
 </div>
 <div>
  <label>영업 시간</label>
  <input type="text" name="restime" value="" required="required" autofocus="autofocus" 
          class="form-control form-control-sm" style="width: 50%">
 </div>
 <div class="content_body_bottom">
  <button type="submit" class="btn btn-primary btn-sm">등록</button>
  <button type="button" class="btn btn-primary btn-sm">목록</button>
 </div>
</form>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>