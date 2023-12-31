<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9092/res/list_all.do</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
<script type="text/javascript">
  window.onload = function(){
  	document.getElementById('lang').value=${resVO.typenum};
  }

</script>

</head>
<body>
<c:import url="/menu/top.do" />

<div class='title_line'>카테고리 수정</div>

<aside class="aside_right">
  <a href="./create.do?resno=${resVO.resno }">등록</a>
  <span class='menu_divide' >│</span>
  <a href="javascript:location.reload();">새로고침</a>
</aside>
<div class="menu_line"></div> 

<form name='frm' method='post' action='/res/update.do'>
  <input type='hidden' name='resno' value='${resVO.resno }'>
  <div style="text-align: center;">
    <div>
      <label>식당 이름</label>
      <input type="text" name="resname" value="${resVO.resname }" required="required" autofocus="autofocus" 
                 class="" style="width: 50%">
    </div>
    <div style="text-align: center;">
    
        <label for="lang">식당 음식 분류</label>
        <select name="typenum" id="lang">
          <option value="1">족발, 보쌈</option>
          <option value="2">짬, 탕, 찌개</option>
          <option value="3">돈까스, 회, 일식</option>
          <option value="4">피자, 치킨</option>
          <option value="5">고기</option>
          <option value="6">양식</option>
          <option value="7">중식</option>
          <option value="8">백반, 죽, 국수</option>
          <option value="9">도시락</option>
          <option value="10">분식</option>
          <option value="11">카페, 디저트</option>
          <option value="12">패스트푸드</option>
        </select>

    </div>
    <div>
      <label>식당 주소</label>
      <input type="text" name="resaddress" value="${resVO.resaddress }" required="required" autofocus="autofocus" 
                 class="" style="width: 50%">
    </div>
    <div>
    <label>전화번호</label>
    <input type="text" name="resphone" value="${resVO.resphone }" required="required" autofocus="autofocus" 
               class="" style="width: 50%">
    </div>
    <div>
    <label>영업시간</label>
    <input type="text" name="restime" value="${resVO.restime }" required="required" autofocus="autofocus" 
               class="" style="width: 50%">
    </div>           
    <button type="submit" class="btn btn-primary btn-sm" style="text-align: center;">저장</button>
    <button type="button" onclick="history.back();" class="btn btn-primary btn-sm" style="text-align: center;">취소</button> 
    
  </div>
</form>

<table class="table table-hover">
   <colgroup>
       <col style='width: 10%;'/>
       <col style='width: 40%;'/>
       <col style='width: 10%;'/>
       <col style='width: 10%;'/>    
       <col style='width: 15%;'/>
       <col style='width: 15%;'/>
    </colgroup>
    <thead>
      <tr>
         <th class="th_bs">순서</th>
         <th class="th_bs">식당 이름</th>
         <th class="th_bs">식당 음식 분류</th>
         <th class="th_bs">식당 번호</th>
         <th class="th_bs">영업 시간</th>
         <th class="th_bs">기타</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="resVO" items="${list }" varStatus="info">
        <c:set var="resno" value="${resVO.resno }" />
  
        <tr>
          <td class="td_bs">${info.count }</td>
          <td><a href="./read.do?resno=${resno }" style="display: block;">${resVO.resname }</a></td>
          <td class="td_bs">
                    <c:if test="${resVO.typenum == 1}">
                      족발, 보쌈
                    </c:if>
                    <c:if test="${resVO.typenum == 2}">
                      짬, 탕, 찌개
                    </c:if>
                    <c:if test="${resVO.typenum == 3}">
                      돈까스, 회, 일식
                    </c:if>
                    <c:if test="${resVO.typenum == 4}">
                      피자, 치킨
                    </c:if>
                    <c:if test="${resVO.typenum == 5}">
                      고기
                    </c:if>
                    <c:if test="${resVO.typenum == 6}">
                      양식
                    </c:if>
                    <c:if test="${resVO.typenum == 7}">
                      중식
                    </c:if>
                    <c:if test="${resVO.typenum == 8}">
                      백반, 죽, 국수
                    </c:if>
                    <c:if test="${resVO.typenum == 9}">
                      도시락
                    </c:if>
                    <c:if test="${resVO.typenum == 10}">
                      분식
                    </c:if>
                    <c:if test="${resVO.typenum == 11}">
                      카페, 디저트
                    </c:if>
                    <c:if test="${resVO.typenum == 12}">
                      패스트푸드
                    </c:if>
          </td>
          <td class="td_bs">${resVO.resphone }</td>
          <td class="td_bs">${resVO.restime }</td>
          <td class="td_bs">
            <a href="../rescontents/create.do?resno=${resno } " title="추가"><img src="/res/images/create.png" class="icon"></a>
            <c:choose>
              <c:when test="${resVO.visible == 'Y' }">
                <a href="./update_visible_n.do?resno=${resno }" title="카테고리 공개 설정"><img src="/res/images/show.png" class="icon"></a>
              </c:when>
              <c:otherwise>
                <a href="./update_visible_y.do?resno=${resno }" title="카테고리 비공개 설정"><img src="/res/images/hide.png" class="icon"></a>
              </c:otherwise>
            </c:choose>
            
            <a href="./update_seqno_forward.do?resno=${resno }" title="우선 순위 높임"><img src="/res/images/decrease.png" class="icon"></a>
            <a href="./update_seqno_backward.do?resno=${resno }" title="우선 순위 낮춤"><img src="/res/images/increase.png" class="icon"></a>
            <a href="./update.do?resno=${resno }"><img src="/res/images/update.png" class="icon"></a>
            <a href="./delete.do?resno=${resno }"><img src="/res/images/delete.png" class="icon"></a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
    
</table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>
