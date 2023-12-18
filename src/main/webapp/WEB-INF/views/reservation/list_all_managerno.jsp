<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/reservation/list_all_managerno.do</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>

<script type="text/javascript">

  window.onload = function() { // 자동 실행
	  
	  document.getElementById("resno").addEventListener("change", function() {
		    let restaurant = document.getElementById('resno');
		    var selectedIndex = restaurant.selectedIndex; // 선택된 옵션의 인덱스
		    var selectedOption = restaurant.options[selectedIndex];

		    let res_name = selectedOption.textContent;

		    document.getElementById('res_name').value = res_name;
		  });

  }

</script>

</head>
<body>
<c:import url="/menu/top.do" />

    <div class='title_line'>지출내역 목록</div>

		<aside class="aside_right">
		  <a href="#">등록</a>
		  <span class='menu_divide' >│</span>
		  <a href="javascript:location.reload();">새로고침</a>
		</aside>
		<div class="menu_line"></div> 
		
		
		<form name='frm' method='post' action='/reservation/create.do'>
		<div style="margin-left:350px;">
		 <div style="text-align: center;">
        <input type="text" class="form-control form-control-sm" name="reserv_name" value="" required="required" placeholder="예약자 성함" autofocus="autofocus" 
                class="" style="width: 50%">
     </div>
     <div style="text-align: center;">
        <input type="text" class="form-control form-control-sm" name="reserv_phone" value="" required="required" placeholder="예약자 휴대폰 번호" autofocus="autofocus" 
                class="" style="width: 50%">
     </div>
     <div style="text-align: center;">
        <input type="text" class="form-control form-control-sm" name="cnt" value="" required="required" placeholder="예약 인원" autofocus="autofocus" 
                class="" style="width: 50%">
     </div>
     
     <div style="text-align: center;">
        <label>예약날짜, 시간</label>
        <input type="datetime-local" name="rdate">
     </div>

     <input type="hidden" name="managerno" value="${managerno }">
     
     <div style="text-align: center;">
       <label for="resno">식당</label>
       <select name="resno" id="resno">
         <c:forEach var="resVO" items="${res_list }" varStatus="info">
           <c:set var="resno" value="${resVO.resno }"/>
           <c:set var="resname" value="${resVO.resname }"/>
           <option value="${resno }">${resname }</option>
         </c:forEach>
       </select>
     </div>
     
     <input type="hidden" id="res_name" name="res_name" value="">
     
    </div>
		 <div class="content_body_bottom" style="text-align: center;">
		  <button type="submit" class="btn btn-primary btn-sm">등록</button>
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