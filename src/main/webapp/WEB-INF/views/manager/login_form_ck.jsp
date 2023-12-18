<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->


</head> 
 
<body>
<c:import url="/menu/top.do" />
 
  <DIV class='title_line'>관리자 로그인</DIV>

  <div class="login-wrapper" style='margin-left: 300px;'>
      <h2>Login</h2>
      <form name='frm' method='POST' action='./login.do' id="login-form">

          <input type="text" name='id' id='id' value='${cookie.ck_manager_id.value }' required="required" placeholder="Id" autofocus="autofocus">
          <input type="password" name='passwd' id='passwd' value='${cookie.ck_manager_passwd.value }' required="required" placeholder="Password">

          <label>   
          <input type='checkbox' name='id_save' value='Y' ${cookie.ck_manager_id_save.value == 'Y' ? "checked='checked'" : "" }> 아이디 저장
          </label>  
                    
          <Label>
          <input type='checkbox' name='passwd_save' value='Y' ${cookie.ck_manager_passwd_save.value == 'Y' ? "checked='checked'" : "" }> 비밀번호 저장
          </Label>     

          <input type="submit" value="Login">
          
      </form>
  </div>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>