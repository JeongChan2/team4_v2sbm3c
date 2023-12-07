<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/expense/list_all_managerno.do</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>

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
		
		
		<form name='frm' method='post' action='/expense/create.do'>
		<div style="margin-left:350px;">
		  <div style="text-align: center;">
			  <input type="text" class="form-control form-control-sm" name="name" value="" required="required" placeholder="지출내역 이름" autofocus="autofocus" 
			          class="" style="width: 50%">
		 </div>
		 <div style="text-align: center;">
        <input type="text" class="form-control form-control-sm" name="cnt" value="" required="required" placeholder="개수" autofocus="autofocus" 
                class="" style="width: 50%">
     </div>
     <div style="text-align: center;">
        <input type="text" class="form-control form-control-sm" name="price" value="" required="required" placeholder="지출금액" autofocus="autofocus" 
                class="" style="width: 50%">
     </div>

     <input type="hidden" name="managerno" value="${managerno }">
     <input type="hidden" name="resno" value="${managerno }"> <!-- resno = managerno -->
     
     <div style="text-align: center;">
       <label for="supplierno">공급업체 분류</label>
       <select name="supplierno" id="supplierno">
         <c:forEach var="supplierVO" items="${supplier_list }" varStatus="info">
           <c:set var="supplierno" value="${supplierVO.supplierno }"/>
           <c:set var="supplier_name" value="${supplierVO.name }"/>
           <option value="${supplierno }">${supplier_name }</option>
         </c:forEach>
       </select>
     </div>
     
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
     <th class="th_bs">지출금액</th>
     <th class="th_bs">지출날짜</th>
     <th class="th_bs">업체이름</th>
     <th class="th_bs">식당이름</th>
     <th class="th_bs">기타</th>
      </tr>
  </thead>
  <tbody>
    <c:forEach var="expense_JoinVO" items="${list }" varStatus="info">
      <c:set var="expenseno" value="${expense_JoinVO.expenseno }"/>
		    <tr>
		      <td class="td_bs">${info.count }</td>
		      <td class="td_bs">${expense_JoinVO.name }</td>
		      <td class="td_bs">${expense_JoinVO.cnt }</td>
		      <td class="td_bs">${expense_JoinVO.price }</td>
		      <td class="td_bs">${expense_JoinVO.rdate }</td>
		      <td class="td_bs">${expense_JoinVO.supplier_name }</td>
		      <td class="td_bs">${expense_JoinVO.resname }</td>
		      <td class="td_bs">
		        <a href="./update.do?expenseno=${expenseno }"><img src="/res/images/update.png" class="icon"></a>
		        <a href="./delete.do?expenseno=${expenseno }"><img src="/res/images/delete.png" class="icon"></a>
		      </td>
		    </tr>
    </c:forEach>

  </tbody>
</table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>