<%@ page contentType="text/html; charset=UTF-8" %>
  
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>world</title>
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>

<link href="/css/style.css" rel="Stylesheet" type="text/css"> <%-- /static/css/style.css --%> 

</head> 
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
 
<%
String code = (String)request.getAttribute("code");
int cnt = (int)request.getAttribute("cnt");
String reserv_name = (String)request.getAttribute("reserv_name");
%>
<div class='title_line'>예약 > 알림</div>
<div class='message'>
  <fieldset class='fieldset_basic'>
    <ul>
        <%
        if (code.equals("create_success")) {
        %>
          <li class="li_none">
            <span class="span_success">예약을 등록했습니다.</span><br>
            예약되셨습니다: <%=reserv_name %>
          </li>
          
        <%  
        } else if (code.equals("create_fail")) {
        %>
          <li class="li_none">
            <span class="span_fail">예약 등록에 실패했습니다.</span><br>
            예약 실패되셨습니다.: <%=reserv_name %>
          </li>
        <%  
        }else if(code.equals("delete_success")){
          %>
          <li class='li_none'>
             <span class='span_success'>예약 삭제에 성공했습니다.</span><br>
             예약 삭제되셨습니다.: <%=reserv_name %>
           </li>
           <%
          }else if(code.equals("delete_fail")){
           %>
           <li class='li_none'>
             <span class='span_fail'>예약 삭제에 실패했습니다.</span><br>
             예약 삭제 실패되셨습니다: <%=reserv_name %>
           </li>
           <%
          }else if(code.equals("update_fail")){
            %>
            <li class='li_none'>
              <span class='span_fail'>예약 업데이트에 실패했습니다.</span><br>
              업데이트 실패되셨습니다: <%=reserv_name %>
            </li>
            <%
           }
            %>
        
        <li class="li_none">
          <br>
          <%
          if (cnt == 1) {
          %>
            <button type="button" onclick="location.href='./list_all_managerno.do'" class="btn btn-secondary btn-sm">업체 계속 등록</button>
          <%  
          } else {
          %>
            <button type="button" onclick="history.back()" class="btn btn-secondary btn-sm">다시 시도</button>
          <%  
          }
          %>
          <button type="button" onclick="location.href='./list_all_managerno.do'" class="btn btn-secondary btn-sm">전체 목록</button>
        </li>
        
    </ul>
  </fieldset>

</div>

<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>