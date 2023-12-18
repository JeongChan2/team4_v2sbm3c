<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, width=device-width" /> 
<title>Resort world</title>

<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

<script type="text/javascript">

  function send(cnt_n) { // 영양정보 생성
	  //document.getElementById('calories').value = cnt_n;
	  if(cnt_n == 1){
		  document.getElementById('frm').action = './update.do';
	  }else{
		  document.getElementById('frm').action = './create.do';
	  }
    document.getElementById('frm').submit(); // required="required" 작동 안됨.
  }  

  function calc() { //  
	    let age = document.getElementById('age');
	    let gender = document.getElementById('gender');
	    let height = document.getElementById('height');
	    let weight = document.getElementById('weight');
	    let plan = document.querySelector('input[name="plan"]:checked').value;

	    // 계산식 참고 사이트
	    // https://m.e-jnh.org/pdf/10.4163/jnh.2021.54.6.573 
	    let alpha, beta, gamma, delta;
	    if(gender.value == "남"){
    	  if(age.value >= 19){
    		  alpha = 662;
    		  beta = -9.53;
    		  gamma = 15.91;
    		  delta = 539.6;
    	  }else{
    		  alpha = 88.5;
    		  beta = -61.9;
    		  gamma = 26.7;
    		  delta = 903.0;
    	  }
	    }else if(gender.value == "여"){
	    	if(age.value >= 19){
          alpha = 354;
          beta = -6.91;
          gamma = 9.36;
          delta = 726.0;
	    	}else{
    		  alpha = 135.3;
    		  beta = -30.8;
    		  gamma = 10.0;
    		  delta = 934.0;
	    	}
	    }else{
	    	weight_msg.innerHTML= '입력을 바르게 해주세요.';
	    	weight_msg.classList.add('span_warning');    // class 적용
	    }
	    let kcal = parseInt(alpha + (beta*age.value) + (gamma*weight.value) + (delta*height.value)/100);

	    if(plan == "증량"){  // radiobutton의 value를 가져와 문자열과 비교하는게 잘 되지않아 length로 구분함
	    	kcal = parseInt(kcal * 1.1);  // 증량이 목표인 경우 kcal를 1.1배로 설정
	    	//document.getElementById('calories').value = "증량";
	    }else if(plan == "유지"){
		    ;
	    	//kcal = parseInt(kcal * 1.0);  // 유지가 목표인 경우 kcal를 1.0배로 설정
	    	//document.getElementById('calories').value = "유지";
	    }else{
	    	kcal = parseInt(kcal * 0.9);  // 감량이 목표인 경우 kacl를 0.9배로 설정
	    	//document.getElementById('calories').value = "감량";
	    }

	     let carbohydrates = (kcal*0.5)/4;
	     let protein = (kcal*0.3)/4;
	     let fat = (kcal*0.2)/9;
	    
	    document.getElementById('calories').value = kcal.toString()+'kcal';
	    document.getElementById('carbohydrates').value = (carbohydrates.toFixed(1)).toString()+'g';
	    document.getElementById('protein').value = (protein.toFixed(1)).toString()+'g';
	    document.getElementById('fat').value = (fat.toFixed(1)).toString()+'g';
	  }  
  
</script>
</head> 


<body>
<c:import url="/menu/top.do" />

  <DIV class='title_line'>영양정보 관리</DIV>

  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>회원 가입</A>
    <span class='menu_divide' >│</span> 
    <A href='./list.do'>목록</A>
  </ASIDE> 

  <div class='menu_line'></div>
  
  <div style="width: 100%; margin: 0px auto; float: left">
  <form name='frm' id='frm' method='POST' action='./create.do' class="">
    <input type="hidden" name="customerno" value="${customerVO.customerno }">
    
    <div style="float: left;  margin-right:300px; background-color: ;">
	    <div class="form-group"> <%-- 줄이 변경되지 않는 패턴 --%>
	      <label><b>아이디:${customerVO.id }</b>
	        <input type='text' class="create_label form-control" name='id' id='id' value='${customerVO.id }' required="required" placeholder="아이디*" autofocus="autofocus" disabled>
	      </label>
	      <span id='id_msg'></span>
	    </div>    
	    
	    <div class="form-group"> <%-- label의 크기에따라 input 태그의 크기가 지정되는 형태 --%>
	      <label><b>성명:${customerVO.cname }</b>
	        <input type='text' class="create_label form-control" name='cname' id='cname' value='${customerVO.cname }' required="required" placeholder="성명" disabled>
	      </label>
	      <span id='cname_msg'></span>
	    </div>    
	    
	    <div class="form-group"> <%-- label의 크기에따라 input 태그의 크기가 지정되는 형태 --%>
	      <label><b>나이:${customerVO.age }</b>
	        <input type='text' class="create_label form-control" name='age' id='age' value='${customerVO.age }' required="required" placeholder="나이" disabled>
	      </label>
	      <span id='age_msg'></span>
	    </div>  
	    
	    <div class="form-group"> <%-- label의 크기에따라 input 태그의 크기가 지정되는 형태 --%>
	      <label><b>성별:${customerVO.gender }</b>
	        <input type='text' class="create_label form-control" name='gender' id='gender' value='${customerVO.gender }' required="required" placeholder="성별" disabled>
	      </label>
	      <span id='gender_msg'></span>
	    </div>  
	    
	    <div class="form-group">
	      <label><b>키:${customerVO.height }</b>
	        <input type='text' class="create_label form-control" name='height' id='height' value='${customerVO.height }' required="required" placeholder="키" disabled>
	      </label>
	      <span id='height_msg'></span>
	    </div>    
	    
	    <div class="form-group">
	      <label><b>몸무게:${customerVO.weight }</b>
	        <input type='text' class="create_label form-control" name='weight' id='weight' value='${customerVO.weight }' required="required" placeholder="몸무게" disabled>
	      </label>
	      <span id='weight_msg'></span>
	    </div> 
    </div>
    
    
    <div style="float: left">
    <h2 class='login-wrapper_h2'>권장 섭취량</h2>
	    <div>
		    <label>
		      <input type='text' style='margin-bottom: 5px;' class=" form-control" name='calories' id='calories' value='' required="required" placeholder="칼로리">
		    </label>
	    </div>
	    
	    <div>
		    <label>
		      <input type='text' style='margin-bottom: 5px;' class=" form-control" name='carbohydrates' id='carbohydrates' value='' required="required" placeholder="탄수화물">
		    </label>
	    </div>
	    
	    <div>
		    <label>
		      <input type='text' style='margin-bottom: 5px;' class=" form-control" name='protein' id='protein' value='' required="required" placeholder="단백질">
		    </label>
	    </div>
	    
	    <div>
		    <label>
		      <input type='text' class=" form-control" name='fat' id='fat' value='' required="required" placeholder="지방">
		    </label>
	    </div>
	    
      <div style='margin-left: 20px;'>
        <input type ="radio" name="plan" id="plan" value="증량">증량
        <input type ="radio" name="plan" id="plan" value="유지" checked>유지
        <input type ="radio" name="plan" id="plan" value="감량">감량
      </div>
      
	    <div class="bottom_menu">
        <button type="button" id='btn_send' onclick="send(${cnt_n })" class="input_button_form input_submit">저장</button>
        <button type="button" id='btn_create' onclick="calc()" class="input_button_form input_submit">생성</button>
      </div>  
    </div>
    
  </form>
  </div>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>

