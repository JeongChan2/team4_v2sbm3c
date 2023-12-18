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
 
  <DIV class='title_line'>
    ${resVO.resname }
    <c:if test="${param.word.length() > 0 }">
      > 「${param.word }」 검색 ${search_count } 건
    </c:if>
  </DIV>


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
    <form name='frm' id='frm' method='get' action='./list_by_resno.do'> <%-- 자기자신한테 submit --%>
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

  <DIV class='menu_line'></DIV>
  
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 80%;"></col>
      <col style="width: 10%;"></col>
    </colgroup>

    <thead>
      <tr>
        <th style='text-align: center;'>파일</th>
        <th style='text-align: center;'>제목</th>
        <th style='text-align: center;'>기타</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="rescontentsVO" items="${list }" varStatus="info">
          <c:set var="rescontentsno" value="${rescontentsVO.rescontentsno }" />
          <c:set var="thumb1" value="${rescontentsVO.thumb1 }" />
    
          <tr onclick="location.href='./read.do?rescontentsno=${rescontentsno}&word=${param.word}&now_page=${param.now_page == null ? 1 : param.now_page }&resno=${param.resno }'" style="cursor: pointer;">
            <td>
              <c:choose>
                <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
                  <%-- registry.addResourceHandler("/rescontents/storage/**").addResourceLocations("file:///" +  Contents.getUploadDir()); --%>
                  <img src="/rescontents/storage/${thumb1 }" style="width: 120px; height: 90px;">
                </c:when>
                <c:otherwise> <!-- 이미지가 없는 경우 기본 이미지 출력: /static/rescontents/images/none1.png -->
                  <img src="/rescontents/images/none1.png" style="width: 120px; height: 90px;">
                </c:otherwise>
              </c:choose>
            </td>
            <td class="td_bs_left">
              <span style="font-weight: bold;">${rescontentsVO.title }</span><br>
              <c:choose>
                <c:when test="${rescontentsVO.rescontent.length() > 160 }">
                  ${rescontentsVO.rescontent.substring(0, 160) }...
                </c:when>
                <c:otherwise>
                  ${rescontentsVO.rescontent }
                </c:otherwise>
              </c:choose>
              (${rescontentsVO.rdate.substring(0, 16) })
            </td>
            <td class="td_bs">
              <a href="#" title="삭제"><img src="/rescontents/images/map.png" class="icon"></a>
              <a href="#" title="삭제"><img src="/rescontents/images/youtube.png" class="icon"></a>
              <a href="#" title="삭제"><img src="/rescontents/images/delete.png" class="icon"></a>
            </td>
          </tr>
        </c:forEach>
    </tbody>
  </table>

  <!-- 페이지 목록 출력 부분 시작 -->
  <DIV class='bottom_menu'>${paging }</DIV> <%-- 페이지 리스트 --%>
  <!-- 페이지 목록 출력 부분 종료 -->
 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>