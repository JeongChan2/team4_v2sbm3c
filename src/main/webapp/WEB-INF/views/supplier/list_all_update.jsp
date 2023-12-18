<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/supplier/update.do</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>

</head>
<body>
<c:import url="/menu/top.do" />

    <div class='title_line'>공급업체 수정목록</div>

    <aside class="aside_right">
      <a href="./list_all_managerno.do">등록</a>
      <span class='menu_divide' >│</span>
      <a href="javascript:location.reload();">새로고침</a>
    </aside>
    <div class="menu_line"></div> 
    
    <form name='frm' method='post' action='/supplier/update.do'>
    
      <input type='hidden' name='supplierno' value='${supplierVO.supplierno }'>
      
    <div style="margin-left:350px;">
      <div style="text-align: center;">
        <input type="text" class="form-control form-control-sm" name="name" value="${supplierVO.name }" required="required" placeholder="공급업체 이름" autofocus="autofocus" 
                class="" style="width: 50%">
     </div>   
     
    </div>
     <div class="content_body_bottom" style="text-align: center;">
      <button type="submit" class="btn btn-primary btn-sm">저장</button>
      <button type="button" onclick="location.href='./list_all_managerno.do'" class="btn btn-primary btn-sm">목록</button> 
     </div>
    </form>

<table class="table table-hover">
  <colgroup>
   <col style='width: 25%;'/>
   <col style='width: 25%;'/>
   <col style='width: 25%;'/>
   <col style='width: 25%;'/> 
  </colgroup>
  <thead>
    <tr>
     <th class="th_bs">번호</th>
     <th class="th_bs">업체 이름</th>
     <th class="th_bs">계약 날짜</th>
     <th class="th_bs">기타</th>
      </tr>
  </thead>
  <tbody>
    <c:forEach var="supplierVO" items="${list }" varStatus="info">
      <c:set var="supplierno" value="${supplierVO.supplierno }"/>
        <tr>
          <td class="td_bs">${info.count }</td>
          <td class="td_bs">${supplierVO.name }</td>
          <td class="td_bs">${supplierVO.rdate }</td>
          <td class="td_bs">
            <a href="./update.do?supplierno=${supplierno }"><img src="/res/images/update.png" class="icon"></a>
            <a href="./delete.do?supplierno=${supplierno }"><img src="/res/images/delete.png" class="icon"></a>
          </td>
        </tr>
    </c:forEach>

  </tbody>
</table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>