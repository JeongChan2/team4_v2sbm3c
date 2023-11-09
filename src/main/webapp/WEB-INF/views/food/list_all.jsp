<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9092/food/list_all.do</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>

</head>
<body>
<c:import url="/menu/top.do" />

    <div class='title_line'>음식 목록</div>

		<aside class="aside_right">
		  <a href="#">등록</a>
		  <span class='menu_divide' >│</span>
		  <a href="javascript:location.reload();">새로고침</a>
		</aside>
		<div class="menu_line"></div> 
		
		
		<form name='frm' method='post' action='/food/create.do'>
		<div style="margin-left:350px;">
		  <div style="text-align: center;">
			  <input type="text" class="form-control form-control-sm" name="foodname" value="" required="required" placeholder="음식 이름" autofocus="autofocus" 
			          class="" style="width: 50%">
		 </div>
		 <div style="text-align: center;">
			  <input type="text" class="form-control form-control-sm" name="f_calories" value="" required="required" placeholder="칼로리" autofocus="autofocus" 
			          class="" style="width: 50%">
		 </div>
		 <div style="text-align: center;">
			  <input type="text" class="form-control form-control-sm" name="f_carbohydrates" value="" required="required" placeholder="탄수화물" autofocus="autofocus" 
			          class="" style="width: 50%">
		 </div>
		 <div style="text-align: center;">
			  <input type="text" class="form-control form-control-sm" name="f_protein" value="" required="required" placeholder="단백질" autofocus="autofocus" 
			          class="" style="width: 50%">
		 </div>
		 <div style="text-align: center;">
        <input type="text" class="form-control form-control-sm" name="f_fat" value="" required="required" placeholder="지방" autofocus="autofocus" 
                class="" style="width: 50%">
     </div>
    </div>
		 <div class="content_body_bottom" style="text-align: center;">
		  <button type="submit" class="btn btn-primary btn-sm">등록</button>
      <button type="button" onclick="location.href='./list_all.do'" class="btn btn-primary btn-sm">목록</button> 
		 </div>
		</form>

<table class="table table-hover">
 <colgroup>
   <col style='width: 15%;'/>
   <col style='width: 20%;'/>
   <col style='width: 15%;'/>    
   <col style='width: 10%;'/>
   <col style='width: 10%;'/>
   <col style='width: 10%;'/>
   <col style='width: 20%;'/>
  </colgroup>
  <thead>
    <tr>
     <th class="th_bs">번호</th>
     <th class="th_bs">음식 이름</th>
     <th class="th_bs">칼로리</th>
     <th class="th_bs">탄수화물</th>
     <th class="th_bs">단백질</th>
     <th class="th_bs">지방</th>
     <th class="th_bs">기타</th>
      </tr>
  </thead>
  <tbody>
    <c:forEach var="foodVO" items="${list }" varStatus="info">
      <c:set var="foodno" value="${foodVO.foodno }"/>
		    <tr>
		      <td class="td_bs">${info.count }</td>
		      <td class="td_bs">${foodVO.foodname }</a></td>
		      <td class="td_bs">${foodVO.f_calories }kcal</td>
		      <td class="td_bs">${foodVO.f_carbohydrates }g</td>
		      <td class="td_bs">${foodVO.f_protein }g</td>
		      <td class="td_bs">${foodVO.f_fat }g</td>
		      <td class="td_bs">
		        <a href="./update.do?foodno=${foodno }"><img src="/res/images/update.png" class="icon"></a>
		        <a href="./delete.do?foodno=${foodno }"><img src="/res/images/delete.png" class="icon"></a>
		      </td>
		    </tr>
    </c:forEach>

  </tbody>
</table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>