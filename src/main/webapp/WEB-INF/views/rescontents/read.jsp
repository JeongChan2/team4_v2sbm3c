<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="resname" value="${resVO.resname }" />

<c:set var="resno" value="${rescontentsVO.resno }" />
<c:set var="rescontentsno" value="${rescontentsVO.rescontentsno }" />
<c:set var="thumb1" value="${rescontentsVO.thumb1 }" />
<c:set var="file1saved" value="${rescontentsVO.file1saved }" />
<c:set var="title" value="${rescontentsVO.title }" />
<c:set var="rescontent" value="${rescontentsVO.rescontent }" />
<c:set var="rdate" value="${rescontentsVO.rdate }" />
<c:set var="youtube" value="${rescontentsVO.youtube }" />
<c:set var="map" value="${rescontentsVO.map }" />
<c:set var="file1" value="${rescontentsVO.file1 }" />
<c:set var="size1_label" value="${rescontentsVO.size1_label }" />
<c:set var="word" value="${rescontentsVO.word }" />
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">

</head> 
 
<body>
<c:import url="/menu/top.do" />
  <DIV class='title_line'><A href="./list_by_resno.do?resno=${resno }" class='title_link'>${resname }</A></DIV>

  <ASIDE class="aside_right">
    <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.manager_id != null }">
      <%--
      http://localhost:9091/rescontents/create.do?resno=1
      http://localhost:9091/rescontents/create.do?resno=2
      http://localhost:9091/rescontents/create.do?resno=3
      --%>
      <A href="./create.do?resno=${resno }">등록</A>
      <span class='menu_divide' >│</span>
      <A href="./update_text.do?rescontentsno=${rescontentsno}&now_page=${param.now_page}&word=${param.word }">글 수정</A>
      <span class='menu_divide' >│</span>
      <A href="./update_file.do?rescontentsno=${rescontentsno}&now_page=${param.now_page}">파일 수정</A>  
      <span class='menu_divide' >│</span>
      <A href="./map.do?resno=${resno }&rescontentsno=${rescontentsno}">지도</A>
      <span class='menu_divide' >│</span>
      <A href="./youtube.do?resno=${resno }&rescontentsno=${rescontentsno}">Youtube</A>
      <span class='menu_divide' >│</span>
      <A href="./delete.do?rescontentsno=${rescontentsno}&now_page=${param.now_page}&resno=${resno}">삭제</A>  
      <span class='menu_divide' >│</span>
    </c:if>

    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>    
    <A href="./list_by_resno.do?resno=${resno }&now_page=${param.now_page}&word=${param.word }">기본 목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./list_by_resno_grid.do?resno=${resno }&now_page=${param.now_page}&word=${param.word }">갤러리형</A>
  </ASIDE> 
  
  <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_resno.do'>
      <input type='hidden' name='resno' value='${resno }'>  <%-- 게시판의 구분 --%>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word }' class='input_word'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' class='input_word'>
        </c:otherwise>
      </c:choose>
      <button type='submit' class='btn btn-secondary btn-sm'>검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' class='btn btn-secondary btn-sm' 
                    onclick="location.href='./list_by_resno.do?resno=${resno}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 100%; word-break: break-all;">
          <c:choose>
            <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}">
              <%-- /static/rescontents/storage/ --%>
              <img src="<spring:url value='/image/${thumb1 }'/>" style='width: 50%; float: left; margin-top: 0.5%; margin-right: 1%;'> 
            </c:when>
            <c:otherwise> <!-- 기본 이미지 출력 -->
              <img src="/images/none1.png" style='width: 50%; float: left; margin-top: 0.5%; margin-right: 1%;'> 
            </c:otherwise>
          </c:choose>

          <span style="font-size: 1.5em; font-weight: bold;">${title }</span>
          <span style="font-size: 1em;"> ${rdate }</span><br>
          ${rescontent }
        </DIV>
      </li>
      
      <c:if test="${youtube.trim().length() > 0 }">
        <li class="li_none" style="clear: both; padding-top: 5px; padding-bottom: 5px;">
          <DIV style="text-align: center;">
            ${youtube }
          </DIV>
        </li>
      </c:if>
      
      <c:if test="${map.trim().length() > 0 }">
        <li class="li_none" style="clear: both; padding-top: 5px; padding-bottom: 5px;">
          <DIV style='text-align: center; width:640px; height: 360px; margin: 0px auto;'>
            ${map }
          </DIV>
        </li>
      </c:if>
      
      <li class="li_none" style="clear: both;">
        <DIV style='text-decoration: none;'>
          <br>
          검색어(키워드): ${word }
        </DIV>
      </li>
      <li class="li_none">
        <DIV>
          <c:if test="${file1.trim().length() > 0 }">
            첨부 파일: <a href='/download?dir=/images&filename=${file1saved}&downname=${file1}'>${file1}</a> (${size1_label}) 
            <a href='/download?dir=/images&filename=${file1saved}&downname=${file1}'><img src="/rescontents/images/download.png"></a>
          </c:if>
        </DIV>
      </li>
      <li class="li_none" style="clear: both; float:left">
      평점 : <div class="star-ratings" style="float:right">     
                  <div class="star-ratings-fill" style="width: ${ avgScore*20 }%">
                      <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                  </div>
                  <div class="star-ratings-base">
                      <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                  </div>
              </div>
        
      
      
        <DIV style='text-decoration: none;'>
          <br>
            평점 : ${ avgScore }
    
        </DIV>
      </li>   
    </ul>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>