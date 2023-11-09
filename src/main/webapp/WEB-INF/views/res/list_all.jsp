<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9092/res/list_all.do</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>

</head>
<body>
<c:import url="/menu/top.do" />

    <div class='title_line'>식당 목록</div>

		<aside class="aside_right">
		  <a href="#">등록</a>
		  <span class='menu_divide' >│</span>
		  <a href="javascript:location.reload();">새로고침</a>
		</aside>
		<div class="menu_line"></div> 
		
		
		<form name='frm' method='post' action='/res/create.do'>
		  <div style="text-align: center;">
			  <label>식당 이름</label>
			  <input type="text" name="resname" value="" required="required" autofocus="autofocus" 
			          class="" style="width: 50%">
		 </div>
		 <div style="text-align: center;">
			  <label>식당 주소</label>
			  <input type="text" name="resaddress" value="" required="required" autofocus="autofocus" 
			          class="" style="width: 50%">
		 </div>
		 <div style="text-align: center;">
			  <label>식당 번호</label>
			  <input type="text" name="resphone" value="" required="required" autofocus="autofocus" 
			          class="" style="width: 50%">
		 </div>
		 <div style="text-align: center;">
			  <label>영업 시간</label>
			  <input type="text" name="restime" value="" required="required" autofocus="autofocus" 
			          class="" style="width: 50%">
		 </div>
		 <div class="content_body_bottom" style="text-align: center;">
		  <button type="submit" class="btn btn-primary btn-sm">등록</button>
      <button type="button" onclick="location.href='./list_all.do'" class="btn btn-primary btn-sm">목록</button> 
		 </div>
		</form>

<table class="table table-hover">
 <colgroup>
   <col style='width: 10%;'/>
   <col style='width: 40%;'/>
   <col style='width: 15%;'/>    
   <col style='width: 20%;'/>
   <col style='width: 15%;'/>
  </colgroup>
  <thead>
    <tr>
     <th class="th_bs">순서</th>
     <th class="th_bs">식당 이름</th>
     <th class="th_bs">식당 번호</th>
     <th class="th_bs">영업 시간</th>
     <th class="th_bs">기타</th>
      </tr>
  </thead>
  <tbody>
    <c:forEach var="resVO" items="${list }" varStatus="info">
      <c:set var="resno" value="${resVO.resno }"/>
		    <tr>
		      <td class="td_bs">${info.count }</td>
		      <td><a href="./read.do?resno=${resno }" style="display: block;">${resVO.resname }</a></td>
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