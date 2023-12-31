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
String supplier_name = (String)request.getAttribute("supplier_name");
%>
<div class='title_line'>공급업체 카테고리 > 알림</div>
<div class='message'>
  <fieldset class='fieldset_basic'>
    <ul>
        <%
        if (code.equals("create_success")) {
        %>
          <li class="li_none">
            <span class="span_success">업체를 등록했습니다.</span><br>
            등록된 식당: <%=supplier_name %>
          </li>
          
        <%  
        } else if (code.equals("create_fail")) {
        %>
          <li class="li_none">
            <span class="span_fail">업체 등록에 실패했습니다.</span><br>
            등록 실패된 식당: <%=supplier_name %>
          </li>
        <%  
        }else if(code.equals("delete_success")){
          %>
          <li class='li_none'>
             <span class='span_success'>업체 삭제에 성공했습니다.</span><br>
             삭제된 식당: <%=supplier_name %>
           </li>
           <%
          }else if(code.equals("delete_fail")){
           %>
           <li class='li_none'>
             <span class='span_fail'>업체 삭제에 실패했습니다.</span><br>
             삭제 실패된 식당: <%=supplier_name %>
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