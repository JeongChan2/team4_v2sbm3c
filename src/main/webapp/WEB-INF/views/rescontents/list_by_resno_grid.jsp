<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>http://localhost:9092/rescontents/list_all.do</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">

</head> 
 
<body>
<c:import url="/menu/top.do" />
 
  <div class='title_line'>
    ${resVO.resname }
    <c:if test="${param.word.length() > 0 }">
      > 「${param.word }」 검색 ${search_count } 건
    </c:if>
  </div>


  <aside class="aside_right">
  
    <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.manager_id != null }">
      <%--
      http://localhost:9092/rescontents/create.do?resno=1
      http://localhost:9092/rescontents/create.do?resno=2
      http://localhost:9092/rescontents/create.do?resno=3
      --%>
      <A href="./create.do?resno=${resVO.resno }">등록</A>
      <span class='menu_divide' >│</span>
    </c:if>
    
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>    
    <A href="./list_by_resno.do?resno=${param.resno }&now_page=${param.now_page == null ? 1 : param.now_page }&word=${param.word }">목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./list_by_resno_grid.do?resno=${param.resno }&now_page=${param.now_page == null ? 1 : param.now_page }&word=${param.word }">갤러리형</A>
  </aside> 
  
  <%-- 동적 sql --%>
   <div style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_resno_grid.do'> <%-- 자기자신한테 submit --%>
      <input type='hidden' name='resno' value='${param.resno }'>  <%-- 게시판의 구분 --%>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우는 검색어를 출력 --%>
          <input type='text' name='word' id='word' value='${param.word }' class='input_word'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' class='input_word'>
        </c:otherwise>
      </c:choose>
      <button type='submit' class='btn btn-secondary btn-sm'>검색</button>
      <c:if test="${param.word.length() > 0 }"> <%-- 검색 상태라면 '검색 취소' 버튼을 출력한다 --%>
        <button type='button' class='btn btn-secondary btn-sm' 
                    onclick="location.href='./list_by_resno.do?resno=${param.resno}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </div>

  <div class='menu_line'></DIV>
  
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
            <img src="/images/${thumb1 }" style="width: 100%; height: 120px;">
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
 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>