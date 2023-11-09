<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "dev.mvc.food.FoodVO" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, width=device-width" /> 
<title>Resort world</title>

<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

<script type="text/javascript">

  function send() { // 섭취 영양소 갱신
    document.getElementById('frm').submit(); // required="required" 작동 안됨.
  }  

  function eat(f_calories, f_carbohydrates, f_protein, f_fat) { // 섭취 영양소 갱신
	  console.log(document.getElementById('f_calories').value);
    document.getElementById('calories').value = f_calories;
    document.getElementById('carbohydrates').value = f_carbohydrates;
    document.getElementById('protein').value = f_protein;
    document.getElementById('fat').value = f_fat;
  }  

</script>
</head> 


<body>
<c:import url="/menu/top.do" />

  <DIV class='title_line'>마이페이지</DIV>


  <!-- <form name='frm1' id='frm1' method='POST' action='./take/update.do' class="">  -->
  <!-- <form name='frm' id='frm' method='get' action='./read.do' class=""> -->
  <div style="width: 100%; margin: 0px auto; float: left">
  <form name='frm' id='frm' method='POST' action='../take/update.do' class="">
    <input type="hidden" name="customerno" value="${requirementVO.customerno }">
    
      <div style='display:flex;'>
        <div class='box basic-drop-shadow' style='margin-left: 300px;'>
          <div style='width: 150px; text-align: center; margin-bottom: 10px'>
            <label><b>권장 섭취량</b></label>
          </div>
		      <div style='margin-left:30px'>
		        <div>
		          <label>칼로리:${requirementVO.calories }</label>
		        </div>
		        
		        <div>
		          <label>탄수화물:${requirementVO.carbohydrates }</label>
		        </div>
		        
		        <div>
		          <label>단백질:${requirementVO.protein }</label>
		        </div>
		        
		        <div>
		          <label>지방:${requirementVO.fat }</label>
		        </div>
		      </div>
	      </div>


	      <div class='box basic-drop-shadow' style='margin-top:20px ; margin-right: 300px;'>
	        <div style='width: 150px; text-align: center; margin-bottom: 10px'>
            <label><b>현재 섭취량</b></label>
          </div>
          <div style='margin-left:30px'>
		        <div>
		          <label>칼로리:${takeVO.calories }kcal</label>
		        </div>
		        
		        <div>
		          <label>탄수화물:${takeVO.carbohydrates }g</label>
		        </div>
		        
		        <div>
		          <label>단백질:${takeVO.protein }g</label>
		        </div>
		        
		        <div>
		          <label>지방:${takeVO.fat }g</label>
		        </div>
	        </div>
	      </div>
	      
      </div>
      
      <div style='margin-top: 100px; text-align: center;'>
        <div class="form-group"> <%-- 줄이 변경되지 않는 패턴 --%>
          <label>
            <input type='text' class="input_form" name='calories' id='calories' value='' required="required" placeholder="칼로리" autofocus="autofocus">
          </label>
        </div>
        
        <div class="form-group"> <%-- 줄이 변경되지 않는 패턴 --%>
          <label>
            <input type='text' class="input_form" name='carbohydrates' id='carbohydrates' value='' required="required" placeholder="탄수화물" autofocus="autofocus">
          </label>
        </div>
        
        <div class="form-group"> <%-- 줄이 변경되지 않는 패턴 --%>
          <label>
            <input type='text' class="input_form" name='protein' id='protein' value='' required="required" placeholder="단백질" autofocus="autofocus">
          </label>
        </div>
        
        <div class="form-group"> <%-- 줄이 변경되지 않는 패턴 --%>
          <label>
            <input type='text' class="input_form" name='fat' id='fat' value='' required="required" placeholder="지방" autofocus="autofocus">
          </label>
        </div>
        
        <div style='margin-bottom: 20px;'>
          <button type="button" id='btn_send' onclick="send()" class="input_button_form input_submit">추가</button>
          <button type="button" onclick="history.back()" class="input_button_form input_submit">취소</button>
        </div> 
      </div>
      
  </form>
  </div>
 
  <table class="table table-hover">
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 65%;"></col>
      <col style="width: 10%;"></col>
      <col style="width: 5%;"></col>
      <col style="width: 5%;"></col>
      <col style="width: 5%;"></col>
      </colgroup>
      <thead>
        <tr>
          <th style='text-align: center;'>파일</th>
          <th style='text-align: center;'>제목</th>
          <th class="th_bs">칼로리</th>
          <th class="th_bs">탄</th>
          <th class="th_bs">단</th>
          <th class="th_bs">지</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="joinVO" items="${list }" varStatus="info">
          <c:set var="rescontentsno" value="${joinVO.rescontentsno }" />
          <c:set var="thumb1" value="${joinVO.thumb1 }" />
          
    
          <!-- <tr onclick="location.href='./read.do?rescontentsno=${rescontentsno}'" style="cursor: pointer;">  -->
          <tr onclick="eat(${joinVO.f_calories}, ${joinVO.f_carbohydrates}, ${joinVO.f_protein}, ${joinVO.f_fat})" style="cursor: pointer;">
            <td>
            <c:choose>
              <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}"> <%-- 이미지인지 검사 --%>
                <%-- registry.addResourceHandler("/rescontents/storage/**").addResourceLocations("file:///" +  Contents.getUploadDir()); --%>
                <img src="/rescontents/storage/${thumb1 }" style="width: 120px; height: 90px;">
              </c:when>
              <c:otherwise> <!-- 이미지가 없는 경우 기본 이미지 출력: /static/rescontents/images/none1.png -->
                <img src="/rescontents/images/none1.png" style="width: 120px; height: 90px;">
              </c:otherwise>
            </c:choose></td>
            <td class="td_bs_left">
              <span style="font-weight:bold;">${joinVO.title }</span><br>
              <c:choose>
                <c:when test="${joinVO.rescontent.length() > 160 }">
                  ${joinVO.rescontent.substring(0,160) }...
                </c:when>
                <c:otherwise>
                  ${joinVO.rescontent }
                </c:otherwise>
              </c:choose>
              ${joinVO.rdate.substring(0, 16) }
            </td>
            <td id="f_calories" class="td_bs">${joinVO.f_calories }kcal</td>
            <td id="f_carbohydrates" class="td_bs">${joinVO.f_carbohydrates }g</td>
            <td id="f_protein" class="td_bs">${joinVO.f_protein }g</td>
            <td id="f_fat" class="td_bs">${joinVO.f_fat }g</td>
          </tr>
        </c:forEach>
      </tbody>
      
  </table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>

