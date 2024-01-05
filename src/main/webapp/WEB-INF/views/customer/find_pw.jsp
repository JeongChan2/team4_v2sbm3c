<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=5.0, width=device-width" /> 
<title>Resort world</title>
 
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->


<script type="text/javascript">
  window.onload = () => {
    document.getElementById('btn_send').addEventListener('click', send);
  }
  
  
</script>

</head> 
 
 
<body>
<c:import url="/menu/top.do" />
 
  <DIV class='title_line'>
    비밀번호 초기화
  </DIV>

  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span> 
    <A href='./create.do'>회원 가입</A>
    <span class='menu_divide' >│</span> 
    <A href='./list.do'>목록</A>
  </ASIDE> 
  
  <div class='menu_line'></div>
  
  <div style='width: 60%; margin: 0px auto;'>  
  <form name='frm' id='frm' method='POST' action='./find_pw.do'>

    <div class="form-floating mb-1 mt-3">
      <input type="text" class="form-control" id="id" name="id" placeholder="아이디" autofocus="autofocus">
      <label for="current_passwd">아이디</label>
    </div>

    <div class="form-floating mb-1 mt-3">
      <input type="text" class="form-control" id="cname" name="cname" placeholder="이름">
      <label for="new_passwd">이름</label>
    </div>

    <div class="bottom_menu">
      <button type="submit" id='btn_send' class="input_button_form input_submit btn-sm">초기화</button>
      <button type="button" onclick="history.back()" class="input_button_form input_submit btn-sm">취소</button>
    </div>   

  </FORM>
  </div>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>
 