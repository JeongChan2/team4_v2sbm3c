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

<body>
<c:import url="/menu/top.do" />

  <DIV class='title_line'>회원 정보 조회</DIV>

  <DIV class='content_body'>

  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>회원 가입</A>
    <span class='menu_divide' >│</span> 
    <A href='./list.do'>목록</A>
  </ASIDE> 

  <div class='menu_line'></div>
  
  <div style="width: 60%; margin: 0px auto ">
  <FORM name='frm' id='frm' method='POST' action='./update.do' class="">
    <input type="hidden" name="customerno" value="${customerVO.customerno }">
    
    <div class="form-group"> <%-- 줄이 변경되지 않는 패턴 --%>
      <label>아이디*:
        <input type='text' class="form-control form-control-sm" name='id' id='id' value='${customerVO.id }' required="required" placeholder="아이디*" autofocus="autofocus" disabled>
      </label>
    </div>   
  
    <div class="form-group"> <%-- label의 크기에따라 input 태그의 크기가 지정되는 형태 --%>
      <label>성명*:
        <input type='text' class="form-control form-control-sm" name='cname' id='cname' value='${customerVO.cname }' required="required" placeholder="성명" disabled>
      </label>
      <span id='cname_msg'></span>
    </div>   
    
    <div class="form-group"> <%-- label의 크기에따라 input 태그의 크기가 지정되는 형태 --%>
      <label>나이*:
        <input type='text' class="form-control form-control-sm" name='age' id='age' value='${customerVO.age }' required="required" placeholder="나이" disabled>
      </label>
    </div>  
    
    <div class="form-group"> <%-- label의 크기에따라 input 태그의 크기가 지정되는 형태 --%>
      <label>성별*:
        <input type='text' class="form-control form-control-sm" name='gender' id='gender' value='${customerVO.gender }' required="required" placeholder="성별" disabled>
      </label>
    </div>  

    <div class="form-group">
      <label>키*:
        <input type='text' class="form-control form-control-sm" name='height' id='height' value='${customerVO.height }' required="required" placeholder="키" disabled>
      </label>
    </div>    
    
    <div class="form-group">
      <label>몸무게*:
        <input type='text' class="form-control form-control-sm" name='weight' id='weight' value='${customerVO.weight }' required="required" placeholder="몸무게" disabled>
      </label>
    </div> 

    <div class="form-group"> <%-- label의 크기에따라 input 태그의 크기가 지정되는 형태, 줄이 변경되지 않는 패턴 --%>
      <label>전화 번호:
        <input type='text' class="form-control form-control-sm" name='tel' id='tel' value='${customerVO.tel }' required="required" placeholder="전화번호" disabled>
      </label>
    </div>   

    <div class="form-group"> <%-- 줄이 변경되지 않는 패턴 --%>
      <label>우편 번호:
        <input type='text' class="form-control form-control-sm" name='zipcode' id='zipcode' value='${customerVO.zipcode }' placeholder="우편번호" disabled>
      </label>
    </div>  

    <div class="form-group">
      <label style="width: 100%;">주소:</label> <%-- label의 크기를 변경하여 주소를 많이 입력받는 패턴 --%>
      <input type='text' class="form-control form-control-sm" name='address1' id='address1' value='${customerVO.address1 }' placeholder="주소" disabled>
    </div>   

    <div class="form-group">
      <label style="width: 100%;">상세 주소:</label>
      <input type='text' class="form-control form-control-sm" name='address2' id='address2' value='${customerVO.address2 }' placeholder="상세 주소" disabled>
    </div>   

    <div>


    </div>
    
 
  </FORM>
  </DIV>
  
  </DIV>
  
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>

