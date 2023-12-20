<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/reservation/update.do</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>

</head>
<body>
<c:import url="/menu/top.do" />

    <div class='title_line'>지출내역 수정 목록</div>

		<aside class="aside_right">
		  <a href="./list_all_managerno.do">등록</a>
		  <span class='menu_divide' >│</span>
		  <a href="javascript:location.reload();">새로고침</a>
		</aside>
		<div class="menu_line"></div> 
		
		
		<form name='frm' method='post' action='/reservation/update.do'>
		  <input type='hidden' name='reserv_no' value='${reservationVO.reserv_no }'>
		  <div style="margin-left:350px;">
			  <div style="text-align: center;">
        <input type="text" class="form-control form-control-sm" name="reserv_name" value="${reservationVO.reserv_name }" required="required" placeholder="예약자 이름" autofocus="autofocus" 
                class="" style="width: 50%">
        </div>
				<div style="text-align: center;">
	        <input type="text" class="form-control form-control-sm" name="reserv_phone" value="${reservationVO.reserv_phone }" required="required" placeholder="예약자 전화번호" autofocus="autofocus" 
	                class="" style="width: 50%">
	      </div>
		    <div style="text-align: center;">
	        <input type="text" class="form-control form-control-sm" name="cnt" value="${reservationVO.cnt }" required="required" placeholder="인원수" autofocus="autofocus" 
	                class="" style="width: 50%">
		    </div>
		    
		    <div style="text-align: center;">
	        <label>예약날짜, 시간</label>
	        <input type="datetime-local" id="rdate" name="rdate" value="${reservationVO.rdate }">
        </div>
     
      </div>
		 <div class="content_body_bottom" style="text-align: center;">
		  <button type="submit" class="btn btn-primary btn-sm">수정</button>
      <button type="button" onclick="location.href='./list_all_managerno.do'" class="btn btn-primary btn-sm">목록</button> 
		 </div>
		</form>

<table class="table table-hover">
  <colgroup>
   <col style='width: 10%;'/>
   <col style='width: 20%;'/>
   <col style='width: 20%;'/>    
   <col style='width: 10%;'/>
   <col style='width: 20%;'/>
   <col style='width: 15%;'/>
   <col style='width: 5%;'/>
  </colgroup>
  <thead>
    <tr>
     <th class="th_bs">번호</th>
     <th class="th_bs">예약자 성함</th>
     <th class="th_bs">예약자 휴대폰 번호</th>
     <th class="th_bs">인원</th>
     <th class="th_bs">예약 날짜</th>
     <th class="th_bs">식당이름</th>
     <th class="th_bs">기타</th>
      </tr>
  </thead>
  <tbody>
    <c:forEach var="reservationVO" items="${list }" varStatus="info">
      <c:set var="reserv_no" value="${reservationVO.reserv_no }"/>
        <tr>
          <td class="td_bs">${info.count }</td>
          <td class="td_bs">${reservationVO.reserv_name }</td>
          <td class="td_bs">${reservationVO.reserv_phone }</td>
          <td class="td_bs">${reservationVO.cnt }</td>
          <td class="td_bs">${reservationVO.rdate }</td>
          <td class="td_bs">${reservationVO.res_name }</td>
          <td class="td_bs">
            <a href="./update.do?reserv_no=${reserv_no }"><img src="/res/images/update.png" class="icon"></a>
            <a href="./delete.do?reserv_no=${reserv_no }"><img src="/res/images/delete.png" class="icon"></a>
          </td>
        </tr>
    </c:forEach>

  </tbody>
</table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>