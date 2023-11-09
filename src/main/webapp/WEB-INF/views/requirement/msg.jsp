<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

</head> 
<body>
<c:import url="/menu/top.do" />

<div class='title_line'>알림</div>

<div class='message'>
  <fieldset class='fieldset_basic'>
    <ul>
      <c:choose>
        <c:when test="${param.code == 'create_success'}"> <%-- Java if --%>
          <li class='li_none'>
            <span class="span_success">영양정보 등록에 성공했습니다.</span>
          </li>  
          <li class='li_none'>
            <button type='button' 
                         onclick="location.href='./read.do'"
                         class="btn btn-primary btn-sm">마이페이지</button>
          </li> 
        </c:when>
        
        <c:when test="${param.code == 'create_fail'}"> <%-- Java if --%>
          <li class='li_none'>
            <span class="span_fail">등록에 실패했습니다. 다시 시도해주세요.</span>
          </li>                                                                      
        </c:when>
        
        <c:when test="${param.code == 'update_success'}"> <%-- Java if --%>
          <li class='li_none'>
            <span class="span_success">영양정보를 갱신했습니다.</span>
          </li>
          <li class='li_none'>
            <button type='button' 
                         onclick="location.href='/'"
                         class="btn btn-primary btn-sm">시작 화면</button>
            <button type='button' 
                         onclick="location.href='./read.do'"
                         class="btn btn-primary btn-sm">마이페이지</button>                   
          </li>                                                                       
        </c:when>
                
        <c:when test="${param.code == 'update_fail'}"> <%-- Java if --%>
          <li class='li_none'>
            <span class="span_fail">${param.cname }님(${param.id }) 회원 정보 수정에 실패했습니다.</span>
          </li>                                                                      
        </c:when>
        
        
      </c:choose>
      <li class='li_none'>
        <br>
        <c:choose>
            <c:when test="${param.cnt == 0 }">
                <button type='button' onclick="history.back()" class="btn btn-primary btn-sm">다시 시도</button>    
            </c:when>
        </c:choose>
        
        <%-- <a href="./list_by_cateno.do?cateno=${param.cateno}" class="btn btn-primary">목록</a> --%>
        <%-- <button type='button' onclick="location.href='./list_by_cateno_search.do?cateno=${param.cateno}'" class="btn btn-primary">목록</button> --%>
        <%-- <button type='button' onclick="location.href='./list_by_cateno_search_paging.do?cateno=${param.cateno}'" class="btn btn-primary">목록</button> --%>

      </li>
    </ul>
  </fieldset>

</div>

<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>

