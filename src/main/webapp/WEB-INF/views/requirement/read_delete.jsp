<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "dev.mvc.food.FoodVO" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, width=device-width" /> 
<title>Resort world</title>

<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
</head> 


<body>
<c:import url="/menu/top.do" />
  
  <form name='frm' method='post' action='/requirement/delete.do'>
          <input type="hidden" name="customerno" value="${param.customerno }">
            <div class="msg_warning">
            ${sessionScope.cname }의 추천 정보를 삭제하시겠습니까?
          </div>
         <div class="content_body_bottom" style="text-align: center;">
          <button type="submit" id='submit' class='btn btn-warning btn-sm' style='height: 28px; margin-bottom: 5px;'>삭제</button>
      <button type="button" onclick="location.href='./read.do'" class="btn btn-primary btn-sm" style='height: 28px; margin-bottom: 5px;'>취소</button> 
         </div>
    </form>
  
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>

