<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/supplier/list_all_managerno.do</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>

</head>
<body>
<c:import url="/menu/top.do" />

    <div class='title_line'>공급업체 목록
	    <c:if test="${param.word.length() > 0 }">
	      > 「${param.word }」 검색 ${search_count } 건
	    </c:if>
	  </div>

		<aside class="aside_right">
		  <c:if test="${sessionScope.manager_id != null }">
			  <a href="#">등록</a>
			  <span class='menu_divide' >│</span>
		  </c:if>
		  <a href="javascript:location.reload();">새로고침</a>
		</aside>
		<div class="menu_line"></div> 
		
		<%-- 동적 sql --%>
   <div style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_all_managerno.do'> <%-- 자기자신한테 submit --%>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우는 검색어를 출력 --%>
          <input type='text' name='word' id='word' value='${param.word }' class='input_word'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' class='input_word'>
        </c:otherwise>
      </c:choose>
      <button type='submit' class='btn btn-secondary btn-sm'>검색</button>
      <c:if test="${param.word.length() > 0 }"> <%-- 검색 상태라면 '검색 취소' 버튼을 출력한다 --%>
        <button type='button' class='btn btn-secondary btn-sm' 
                    onclick="location.href='./list_all_managerno.do?word='">검색 취소</button>  
      </c:if>    
    </form>
  </div>
		
		
		<form name='frm' method='post' action='/supplier/create.do'>
		<div style="margin-left:350px;">
		  <div style="text-align: center;">
			  <input type="text" class="form-control form-control-sm" name="name" value="" required="required" placeholder="공급업체 이름" autofocus="autofocus" 
			          class="" style="width: 50%">
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
   <col style='width: 25%;'/>
   <col style='width: 25%;'/>
   <col style='width: 25%;'/>
   <col style='width: 25%;'/> 
  </colgroup>
  <thead>
    <tr>
     <th class="th_bs">번호</th>
     <th class="th_bs">업체 이름</th>
     <th class="th_bs">계약 날짜</th>
     <th class="th_bs">기타</th>
      </tr>
  </thead>
  <tbody>
    <c:forEach var="supplierVO" items="${list }" varStatus="info">
      <c:set var="supplierno" value="${supplierVO.supplierno }"/>
		    <tr>
		      <td class="td_bs">${info.count }</td>
		      <td class="td_bs">${supplierVO.name }</a></td>
		      <td class="td_bs">${supplierVO.rdate }</td>
		      <td class="td_bs">
		        <a href="./update.do?supplierno=${supplierno }"><img src="/res/images/update.png" class="icon"></a>
		        <a href="./delete.do?supplierno=${supplierno }"><img src="/res/images/delete.png" class="icon"></a>
		      </td>
		    </tr>
    </c:forEach>

  </tbody>
</table>

<!-- 페이지 목록 출력 부분 시작 -->
  <DIV class='bottom_menu'>${paging }</DIV> <%-- 페이지 리스트 --%>
<!-- 페이지 목록 출력 부분 종료 -->
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>