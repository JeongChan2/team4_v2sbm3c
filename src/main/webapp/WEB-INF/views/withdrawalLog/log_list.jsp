<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/withdrawalLog/log_list.do</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>

</head>
<body>
<c:import url="/menu/top.do" />


<table class="table table-hover">
 <colgroup>
   <col style='width: 15%;'/>
   <col style='width: 15%;'/>
   <col style='width: 45%;'/>
   <col style='width: 25%;'/>    
  </colgroup>
  <thead>
    <tr>
     <th class="th_bs">번호</th>
     <th class="th_bs">회원 번호</th>
     <th class="th_bs">아이디</th>
     <th class="th_bs">탈퇴 날짜</th>
      </tr>
  </thead>
  <tbody>
    <c:forEach var="JoinLogVO" items="${list }" varStatus="info">
      <c:set var="logno" value="${JoinLogVO.logno }"/>
        <tr>
          <td class="td_bs">${info.count }</td>
          <td class="td_bs">${JoinLogVO.customerno }</td>
          <td class="td_bs">${JoinLogVO.id }</td>
          <td class="td_bs">${JoinLogVO.logdate }</td>
        </tr>
    </c:forEach>

  </tbody>
</table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>