<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/sell/list_all_managerno.do</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>

<script>
function updateResName(selectElement) {
    // 선택된 resno 값을 가져옴
    var selectedResno = Number(selectElement.value);

    let foodnoSelect = document.getElementById("foodno");

    // 기존 옵션을 제거
    while (foodnoSelect.options.length > 0) {
        foodnoSelect.remove(0);
    }
    
    // 선택된 resno에 기반하여 새로운 옵션을 추가
    <c:forEach var="foodVO" items="${food_list}">
      <c:if test="${foodVO.resno == selectedResno}">
          var option = document.createElement("option");
          option.value = ${foodVO.foodno};
          option.text = "${foodVO.foodname}";
          foodnoSelect.add(option);
      </c:if>
    </c:forEach>
}



	 function updateFoodName(selectElement) {
	     let selectedOption = selectElement.options[selectElement.selectedIndex];
	     let foodnameInput = document.getElementById('foodname');
	
	     // 변경된 값을 바로 input 엘리먼트에 반영
	     foodnameInput.value = selectedOption.text;
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
		
		
		<form name='frm' method='post' action='/sell/create.do'>
		<div style="margin-left:350px;">
		
		<div style="text-align: center;">
		  <label for="resno">식당 분류</label>
		  <select name="resno" id="resno" onchange="updateResName(this)">
		    <c:forEach var="resVO" items="${res_list}" varStatus="info">
		      <c:set var="resno" value="${resVO.resno}"/>
		      <c:set var="resname" value="${resVO.resname}"/>
		      <option value="${resno}">${resname}</option>
		    </c:forEach>
		  </select>
		</div>
     
     <div style="text-align: center;">
       <label for="foodno">음식 이름</label>
       <select name="foodno" id="foodno" onchange="updateFoodName(this)">
         <c:forEach var="foodVO" items="${food_list }" varStatus="info">
           <c:if test="${foodVO.resno == resno}">
	           <c:set var="foodno" value="${foodVO.foodno }"/>
	           <c:set var="foodname" value="${foodVO.foodname }"/>
	           <option value="${foodno }">${foodname }</option>
           </c:if>
         </c:forEach>
       </select>
     </div>
     
     <input name="foodname" id="foodname" value="">

		 <div style="text-align: center;">
        <input type="text" class="form-control form-control-sm" name="cnt" value="" required="required" placeholder="개수" autofocus="autofocus" 
                style="width: 50%">
     </div>
     
     <div style="text-align: center;">
        <input type="text" class="form-control form-control-sm" name="price" value="" required="required" placeholder="판매금액" autofocus="autofocus" 
                style="width: 50%">
     </div>

     <input type="hidden" name="managerno" value="${managerno }">
     
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
   <col style='width: 10%;'/>    
   <col style='width: 15%;'/>
   <col style='width: 15%;'/>
   <col style='width: 10%;'/>
   <col style='width: 10%;'/>
   <col style='width: 10%;'/>
  </colgroup>
  <thead>
    <tr>
     <th class="th_bs">번호</th>
     <th class="th_bs">이름</th>
     <th class="th_bs">개수</th>
     <th class="th_bs">판매금액</th>
     <th class="th_bs">지출날짜</th>
     <th class="th_bs">식당이름</th>
     <th class="th_bs">기타</th>
      </tr>
  </thead>
  <tbody>
    <c:forEach var="Sell_JoinVO" items="${list }" varStatus="info">
      <c:set var="sellno" value="${Sell_JoinVO.sellno }"/>
		    <tr>
		      <td class="td_bs">${info.count }</td>
		      <td class="td_bs">${Sell_JoinVO.name }</td>
		      <td class="td_bs">${Sell_JoinVO.cnt }</td>
		      <td class="td_bs">${Sell_JoinVO.price }</td>
		      <td class="td_bs">${Sell_JoinVO.rdate }</td>
		      <td class="td_bs">${Sell_JoinVO.resname }</td>
		      <td class="td_bs">
		        <a href="./update.do?sellno=${sellno }"><img src="/res/images/update.png" class="icon"></a>
		        <a href="./delete.do?sellno=${sellno }"><img src="/res/images/delete.png" class="icon"></a>
		      </td>
		    </tr>
    </c:forEach>

  </tbody>
</table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>