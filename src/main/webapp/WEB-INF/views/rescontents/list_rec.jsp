<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/rescontents/list_rec.do</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

<style>
    .image-container {
      text-align:center;
      width: 70%;
      margin: 30px auto;
    }
</style>



</head>
<body>
<c:import url="/menu/top.do" />

<div class="image-container" id="imageContainer">
  <img src="/images/balanced-nutrition.jpg">
</div>
    <c:choose>
        <c:when test="${sessionScope.id == null}">
            <div class='title_line'>평점 기준 음식 추천</DIV>
        </c:when>
        <c:otherwise>
            <div class='title_line'>${ sessionScope.cname } 회원님 취향 기준 음식 추천</DIV>
        </c:otherwise>
    </c:choose>   
  
 <div>
      <div style='width: 100%;'> <%-- 갤러리 Layout 시작 --%>
    <c:forEach var="rescontentsVO" items="${list }" varStatus="status">
      <c:set var="title" value="${rescontentsVO.title }" />
      <c:set var="rescontent" value="${rescontentsVO.rescontent }" />
      <c:set var="resno" value="${rescontentsVO.resno }" />
      <c:set var="rescontentsno" value="${rescontentsVO.rescontentsno }" />
      <c:set var="thumb1" value="${rescontentsVO.thumb1 }" />
      <c:set var="size1" value="${rescontentsVO.size1 }" />
       
        
      <!-- 4기준 하나의 이미지, 24 * 4 = 96% -->
      <!-- 5기준 하나의 이미지, 19.2 * 5 = 96% -->
      <div onclick="location.href='./read.do?rescontentsno=${rescontentsno}&word=${param.word}&now_page=${param.now_page == null ? 1 : param.now_page }&resno=${param.resno }'"
      style='width: 19%; height: 200px; float: left; margin: 0.5%; padding: 0.5%; background-color: #EEEFFF; text-align: left; cursor: pointer;'>
        
        <c:choose> 
          <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
            <%-- registry.addResourceHandler("/rescontents/storage/**").addResourceLocations("file:///" +  Contents.getUploadDir()); --%>
            <img src="/rescontents/storage/${thumb1 }" style="width: 100%; height: 120px;">
          </c:when>
          <c:otherwise> <!-- 이미지가 없는 경우 기본 이미지 출력: /static/contents/images/none1.png -->
            <IMG src="/rescontents/images/none1.png" style="width: 100%; height: 120px;">
          </c:otherwise>
        </c:choose>
        ${title }
        
      </div>
      
      <%-- 하나의 행에 이미지를 5개씩 출력후 행 변경, index는 0부터 시작 --%>
      <c:if test="${status.count % 5 == 0 }"> 
        <HR class='menu_line'> <%-- 줄바꿈 --%>
      </c:if>
    </c:forEach>
  </div>

  <!-- 페이지 목록 출력 부분 시작 -->
  <div class='bottom_menu'>${paging }</DIV> <%-- 페이지 리스트 --%>
  <!-- 페이지 목록 출력 부분 종료 -->
 </div>
 
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>