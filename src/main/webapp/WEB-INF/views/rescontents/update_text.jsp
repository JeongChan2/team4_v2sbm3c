<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="resno" value="${resVO.resno }" />

<c:set var="rescontentsno" value="${rescontentsVO.rescontentsno }" />
<c:set var="title" value="${rescontentsVO.title }" />
<c:set var="rescontent" value="${rescontentsVO.rescontent }" />
<c:set var="word" value="${rescontentsVO.word }" />
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
<link rel="shortcut icon" href="/images/shortcut.png" /> <%-- /static 기준 --%>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->

<script type="text/javascript">

  function send() { // 영양정보 생성
    //document.getElementById('calories').value = cnt_n;
	  rescontentsVO.foodno = document.getElementById('foodno')
	    
    document.getElementById('frm').submit(); // required="required" 작동 안됨.
  }  

</script>
</head>
 
<body>
<c:import url="/menu/top.do" />
 
  <DIV class='title_line'> ${resVO.resname } > ${title } > 수정</DIV>
  
  <aside class="aside_right">
    <a href="./create.do?resno=${resno }">등록</a>
    <span class='menu_divide' >│</span>
    <a href="javascript:location.reload();">새로고침</a>
    <span class='menu_divide' >│</span>    
    <a href="./list_by_resno.do?resno=${resno }&now_page=${param.now_page}&word=${param.word }">목록형</a>    
    <span class='menu_divide' >│</span>
    <a href="./list_by_resno_grid.do?resno=${resno }&now_page=${param.now_page}&word=${param.word }">갤러리형</a>
  </aside>`
  
  <div style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_resno_search_paging.do'>
      <input type='hidden' name='resno' value='${resVO.resno }'>  <%-- 게시판의 구분 --%>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word }' class='input_word'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' class='input_word'>
        </c:otherwise>
      </c:choose>
      <button type='submit' class='btn btn-secondary btn-sm' style="padding: 2px 8px 3px 8px; margin: 0px 0px 2px 0px;">검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' class='btn btn-secondary btn-sm' 
                    onclick="location.href='./list_by_resno.do?resno=${resVO.resno}&word='" style="padding: 2px 8px 3px 8px; margin: 0px 0px 2px 0px;">검색 취소</button>  
      </c:if>    
    </form>
  </div>
  
  <div class='menu_line'></div>
  
  <form name='frm' method='post' action='./update_text.do'>
    <input type="hidden" name="resno" value="${resno }">
    <input type="hidden" name="rescontentsno" value="${rescontentsno }">
    <input type="hidden" name="now_page" value="${param.now_page }">

    
    <div>
       <label>제목</label>
       <input type='text' name='title' value='${title }' required="required" 
                 autofocus="autofocus" class="form-control" style='width: 100%;'>
    </div>
    <div>
       <label>내용</label>
       <textarea name='rescontent' required="required" class="form-control" rows="12" style='width: 100%;'>${rescontent }</textarea>
    </div>
    <div>
      <label>검색어</label>
      <label style='float:right; margin-right: 180px'>메뉴</label>
      <input type='text' name='word' value="${word }" required="required" 
                 class="form-control" style='float:left; width: 80%;'>
	    
	    <select id='foodno' name="foodno" class="form-select " style='width: 20%; '>
	      <c:forEach var="foodVO" items="${list }" varStatus="info">
	        <option id='foodno' value='${foodVO.foodno }'>${foodVO.foodname }</option>
	      </c:forEach>
	    </select>
    </div>   
    
    <div>
      <label>패스워드</label>
      <input type='password' name='passwd' value='' required="required" 
                class="form-control" style='width: 50%;'>
    </div>
       
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-secondary btn-sm">저장</button>
      <button type="button" onclick="history.back();'" class="btn btn-secondary btn-sm">취소</button>
    </div>
  
  </form>

 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

