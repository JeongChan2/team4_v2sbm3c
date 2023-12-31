<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="resname" value="${resVO.resname }" />

<c:set var="resno" value="${rescontentsVO.resno }" />
<c:set var="rescontentsno" value="${rescontentsVO.rescontentsno }" />
<c:set var="thumb1" value="${rescontentsVO.thumb1 }" />
<c:set var="file1saved" value="${rescontentsVO.file1saved }" />
<c:set var="title" value="${rescontentsVO.title }" />
<c:set var="rescontent" value="${rescontentsVO.rescontent }" />
<c:set var="rdate" value="${rescontentsVO.rdate }" />
<c:set var="youtube" value="${rescontentsVO.youtube }" />
<c:set var="map" value="${rescontentsVO.map }" />
<c:set var="file1" value="${rescontentsVO.file1 }" />
<c:set var="size1_label" value="${rescontentsVO.size1_label }" />
<c:set var="word" value="${rescontentsVO.word }" />
<c:set var="scustomerno" value="${sessionScope.customerno }" />
<c:set var="smanagerid" value="${sessionScope.manager_id }" />

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript">
window.onload = function(){
  var ele = document.getElementsByName('score');
    if(${score} == 5){
    	ele[0].checked = true;
    }else if(${score} == 4){
    	ele[1].checked = true;
    }
    else if(${score} == 3){
    	ele[2].checked = true;
    }
    else if(${score} == 2){
    	ele[3].checked = true;
    }
    else if(${score} == 1){
    	ele[4].checked = true;
    }
    
  }
</script>
</head> 
 
<body>
<c:import url="/menu/top.do" />
  <p id='scustom' style="display: none;">${scustomerno }</p>
  <p id='smanager' style="display: none;">${smanagerid }</p>
  <DIV class='title_line'><A href="./list_by_resno.do?resno=${resno }" class='title_link'>${resname }</A></DIV>

  <ASIDE class="aside_right">
    <%-- 관리자로 로그인해야 메뉴가 출력됨 --%>
    <c:if test="${sessionScope.manager_id != null }">
      <%--
      http://localhost:9091/rescontents/create.do?resno=1
      http://localhost:9091/rescontents/create.do?resno=2
      http://localhost:9091/rescontents/create.do?resno=3
      --%>
      <A href="./create.do?resno=${resno }">등록</A>
      <span class='menu_divide' >│</span>
      <A href="./update_text.do?rescontentsno=${rescontentsno}&now_page=${param.now_page}&word=${param.word }">글 수정</A>
      <span class='menu_divide' >│</span>
      <A href="./update_file.do?rescontentsno=${rescontentsno}&now_page=${param.now_page}">파일 수정</A>  
      <span class='menu_divide' >│</span>
      <A href="./map.do?resno=${resno }&rescontentsno=${rescontentsno}">지도</A>
      <span class='menu_divide' >│</span>
      <A href="./youtube.do?resno=${resno }&rescontentsno=${rescontentsno}">Youtube</A>
      <span class='menu_divide' >│</span>
      <A href="./delete.do?rescontentsno=${rescontentsno}&now_page=${param.now_page}&resno=${resno}">삭제</A>  
      <span class='menu_divide' >│</span>
    </c:if>

    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>    
    <A href="./list_by_resno.do?resno=${resno }&now_page=${param.now_page}&word=${param.word }">기본 목록형</A>    
    <span class='menu_divide' >│</span>
    <A href="./list_by_resno_grid.do?resno=${resno }&now_page=${param.now_page}&word=${param.word }">갤러리형</A>
  </ASIDE> 
  
  <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_resno.do'>
      <input type='hidden' name='resno' value='${resno }'>  <%-- 게시판의 구분 --%>
      
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word }' class='input_word'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' class='input_word'>
        </c:otherwise>
      </c:choose>
      <button type='submit' class='btn btn-secondary btn-sm'>검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' class='btn btn-secondary btn-sm' 
                    onclick="location.href='./list_by_resno.do?resno=${resno}&word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style="width: 100%; word-break: break-all;">
          <c:choose>
            <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}">
              <%-- /static/rescontents/storage/ --%>

<%--               <img src="/rescontents/storage/${file1saved }" style='width: 50%; float: left; margin-top: 0.5%; margin-right: 1%;'> 
            </c:when>
            <c:otherwise> <!-- 기본 이미지 출력 -->
              <img src="/rescontents/images/none1.png" style='width: 50%; float: left; margin-top: 0.5%; margin-right: 1%;'>  --%>

              <img src="/images/${thumb1 }" style='width: 50%; float: left; margin-top: 0.5%; margin-right: 1%;'> 
            </c:when>
            <c:otherwise> <!-- 기본 이미지 출력 -->
              <img src="/images/none1.png" style='width: 50%; float: left; margin-top: 0.5%; margin-right: 1%;'> 

            </c:otherwise>
          </c:choose>

          <span style="font-size: 1.5em; font-weight: bold;">${title }</span>
          <span style="font-size: 1em;"> ${rdate }</span><br>
          ${rescontent }
        </DIV>
      </li>
      
      <c:if test="${youtube.trim().length() > 0 }">
        <li class="li_none" style="clear: both; padding-top: 5px; padding-bottom: 5px;">
          <DIV style="text-align: center;">
            ${youtube }
          </DIV>
        </li>
      </c:if>
      
      <c:if test="${map.trim().length() > 0 }">
        <li class="li_none" style="clear: both; padding-top: 5px; padding-bottom: 5px;">
          <DIV style='text-align: center; width:640px; height: 360px; margin: 0px auto;'>
            ${map }
          </DIV>
        </li>
      </c:if>
      
      <li class="li_none" style="clear: both;">
        <DIV style='text-decoration: none;'>
          <br>
          검색어(키워드): ${word }
        </DIV>
      </li>
      <li class="li_none">
        <DIV>
          <c:if test="${file1.trim().length() > 0 }">

<%--             첨부 파일: <a href='/download?dir=/rescontents/storage&filename=${file1saved}&downname=${file1}'>${file1}</a> (${size1_label}) 
            <a href='/download?dir=/rescontents/storage&filename=${file1saved}&downname=${file1}'><img src="/rescontents/images/download.png"></a>
 --%>
            첨부 파일: <a href='/download?dir=/images&filename=${file1saved}&downname=${file1}'>${file1}</a> (${size1_label}) 
            <a href='/download?dir=/images&filename=${file1saved}&downname=${file1}'><img src="/rescontents/images/download.png"></a>

          </c:if>
        </DIV>
      </li>
      <li class="li_none" style="clear: both; float:left">
      평점 : <div class="star-ratings" style="float:right">     
                  <div class="star-ratings-fill" style="width: ${ avgScore*20 }%">
                      <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                  </div>
                  <div class="star-ratings-base">
                      <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                  </div>
              </div>
        
      
      
        <DIV style='text-decoration: none;'>
          <br>
            평점 : ${ avgScore }<br>
            
        </DIV>
        <c:choose>
        <c:when test="${sessionScope.id != null}">
        ${sessionScope.cname }의 평점 : ${score}
            <div style="text-align: center; vertical-align: middle;">
            <form name='frm2' id='frm2' method='get'>
                <input type="hidden" name="resno" value="${resno }" />
                <input type="hidden" name="rescontentsno" value="${rescontentsno }" />
                <input type="hidden" name="customerno" value="${customerno }" />
                    <div class="star-rating">
                      <input type="radio" id="5-stars" name="score" value="5" />
                      <label for="5-stars" class="star">&#9733;</label>
                      <input type="radio" id="4-stars" name="score" value="4" />
                      <label for="4-stars" class="star">&#9733;</label>
                      <input type="radio" id="3-stars" name="score" value="3" />
                      <label for="3-stars" class="star">&#9733;</label>
                      <input type="radio" id="2-stars" name="score" value="2" />
                      <label for="2-stars" class="star">&#9733;</label>
                      <input type="radio" id="1-star" name="score" value="1" />
                      <label for="1-star" class="star">&#9733;</label>
                    </div>
                    <br>
                    <button type="submit" formaction='./score.do' class="btn btn-primary btn-sm">등록</button>
                    <c:choose>
                        <c:when test="${score != 0}">
                            <button type="submit" formaction='./scoreDelete.do' class="btn btn-danger btn-sm">삭제</button>
                        </c:when>
                    </c:choose>
                    
                    <button type="button" onclick="location.href='./list_by_resno.do?resno=${param.resno }&now_page=1'" class="btn btn-primary btn-sm">취소</button> 
              </form>
              </div>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>
        
      </li>   
    </ul>
  </fieldset>
  <!-- Comments section-->
<div style="width:80%; margin: 0 auto;">
  <section class="mb-5">
      <div class="card bg-light">
          <div class="card-body">
              <!-- 댓글 입력 창 -->
              <c:choose>
                <c:when test="${sessionScope.id != null}"> <%-- 회원 로그인했을 경우 --%>
                  <form class="mb-4" name='frm3' id='frm3' method='get' action='./reply.do'>
                    <input type="hidden" name="rescontentsno" value="${rescontentsno }" />
                    <input type="hidden" name="resno" value="${resno }" />
                    <input type="hidden" name="customerno" value="${customerno }" />
                    <div>
                          <textarea class="form-control" style="width:90%; float:left;" name="replycontents" rows="3" placeholder="댓글 입력해주세요"></textarea>
                          <button type="submit"style="width:10%; height:86px; font-size:24px;" class="btn btn-primary btn-sm">등록</button>
                    </div>
                    <br>
                    <div>
                        <div></div>
                    </div>
                  </form>
                </c:when>
              </c:choose>
              
              <!-- 작성된 댓글 표현 창 -->
              <div style="width: 100%">
                  <c:forEach var="replyVO" items="${replylist }" varStatus="status">
                    <c:set var="replyno" value="${replyVO.replyno }" />
                    <c:set var="replycontents" value="${replyVO.replycontents }" />
                    <c:set var="rdate" value="${replyVO.rdate }" />
                    <c:set var="rescontentsno" value="${replyVO.rescontentsno }" />
                    <c:set var="customerno" value="${replyVO.customerno }" />
                    <c:set var="cname" value="${replyVO.cname }" />
                    <c:set var="re_reply" value="${replyVO.re_reply }" />
                     <div class="d-flex mb-4">
                        
                        <!-- Parent comment-->
                        <div class="flex-shrink-0"><img class="rounded-circle" style="width:60px;" src="/css/images/profile.png" alt="..." /></div>
                        <div class="ms-3">
                            <form class="mb-4" name='frm4' id='frm4' method='get' action='./reply_delete.do'>
                                <div class="fw-bold" style="display:inline;">${cname }        ${rdate }  </div>
                                  <input type="hidden" name="replyno" value="${replyno }" />
                                  <input type="hidden" name="resno" value="${resno }" />
                                  <c:choose>
                                    <c:when test="${sessionScope.customerno == customerno || sessionScope.manager_id != null}">
                                       <button type="submit" class="btn btn-sm" style="width:16px; vertical-align : bottom;"><img  src="/rescontents/images/delete.png" width="16"></button><br>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" class="btn btn-sm" style="visibility : hidden; width:16px; vertical-align : bottom;"><img  src="/rescontents/images/delete.png" width="16"></button><br>
                                    </c:otherwise>
                                  </c:choose>
                                  ${replycontents }
                            </form>
                            
                            <button type="button" class="btn btn-sm btn-secondary" onclick="showDependentButtons(${replyno})">답글 ${re_reply }</button>
                            
                            <!-- 댓글에 종속된 댓글들을 나타낼 div -->
                            <div id="dependentButtons_${replyno}" style="display:none; margin-top: 10px;">
                                
                            </div>
                            
                            <script>
                                function showDependentButtons(replyno) {
                               	   const url = '/reply_of_reply/reply_fetch.do?replyno=' + String(replyno); // http://localhost:9091/javascript/select_menu_fetch.do
                                   console.log('-> url: ' + url);
                              
                                   fetch(url, {
                                     method: 'GET', // HTTP GET or POST 요청
                                     // headers: {
                                     //   'Content-Type': 'application/json' // JSON 형식으로 데이터 전송을 알림
                                     // },
                                     // body: JSON.stringify({foodno}) // 보내는 데이터, {"foodno":foodno}, object -> json
                                   })
                                   .then((response) => response.json())  // response.json() or response.text()
                                   .then((rdata) => {// 서버 응답 처리
                                	     var scustom = document.getElementById('scustom').innerHTML;
                                	     var smanager = document.getElementById('smanager').innerHTML;
                                	     // console.log('-> ' + scustom); // string
                                	     // console.log('-> ' + smanager);
                                       
	                                	   var visualizationDiv = document.getElementById('dependentButtons_' + String(replyno));
	                                     visualizationDiv.innerHTML = '';

	                                     var visualizationHTML = '<div style="width: 100%">';

	                                     if (scustom != '') {
	                                    	  visualizationHTML += '<form class="mb-4" name="frm6" id="frm6" method="post" action="/reply_of_reply/reply.do">';
	                                    	  visualizationHTML += '<input type="hidden" name="replyno" value="'+ String(replyno) +'" />';
	                                    	  visualizationHTML += '<input type="hidden" name="customerno" value="${customerno }" />';
	                                    	  visualizationHTML += '<input type="hidden" name="resno" value="${resno }" />';
	                                    	  visualizationHTML += '<div>';
	                                    	  visualizationHTML += '<textarea class="form-control" style="width:70%; float:left;" name="replycontents" rows="2" placeholder="댓글 입력해주세요"></textarea>';
	                                    	  visualizationHTML += '<button type="submit"style="width:30%; height:35px; font-size:16px;" class="btn btn-primary btn-sm">등록</button>';
	                                    	  visualizationHTML += '</div><br><div><div></div></div></form>';
	                                     }

	                                     for (let rreply of rdata) { // Spring -> Js
	                                    	 visualizationHTML += '<div class="d-flex mb-4">';
	                                    	 visualizationHTML += '<div class="flex-shrink-0"><img class="rounded-circle" style="width:60px;" src="/css/images/profile.png" alt="..." /></div>';
	                                    	 visualizationHTML += '<div class="ms-3">';
	                                    	 visualizationHTML += '<form class="mb-4" name="frm" id="frm" method="get" action="/reply_of_reply/reply_of_reply_delete.do">';
	                                    	 visualizationHTML += '<div class="fw-bold" style="display:inline;">'+ rreply.cname +'   '+ rreply.rdate +' </div>';
	                                    	 visualizationHTML += '<input type="hidden" name="replyofreplyno" value="'+ rreply.replyofreplyno +'" />';
	                                    	 visualizationHTML += '<input type="hidden" name="resno" value="${resno }" />';

	                                    	 if (scustom == rreply.customerno || smanager != '') {
                                   	       visualizationHTML += '<button type="submit" class="btn btn-sm" style="width:16px; vertical-align : bottom;"><img  src="/rescontents/images/delete.png" width="16"></button><br>';
                                   	     } else {
                                   	       visualizationHTML += '<button type="submit" class="btn btn-sm" style="visibility : hidden; width:16px; vertical-align : bottom;"><img  src="/rescontents/images/delete.png" width="16"></button><br>';
                                   	     }

	                                    	 visualizationHTML += '<br>' + rreply.replycontents;
	                                    	 visualizationHTML += '</form></div></div></div>'
	                                       //console.log('->' + rreply.replyofreplyno);
	                                       //console.log('->' + rreply.replycontents);
	                                       //console.log('->' + rreply.rdate);
	                                       console.log('->' + rreply.customerno);
	                                       //console.log('->' + rreply.cname);
	                                       //console.log('->' + rreply.replyno);
	                                    	 console.log('->' + rreply.replyno);
	                                     }
	                                     visualizationDiv.innerHTML = visualizationHTML;
                                   })
                                   .catch(error => { // 오류 처리
                                     console.log('-> error:' + error);
                                     // document.getElementById('progress').style.display="none"; // hide
                                   });
                                   // 해당 댓글에 종속된 div를 나타내거나 숨김
                                   var dependentButtonsDiv = document.getElementById('dependentButtons_' + replyno);
                                   dependentButtonsDiv.style.display = (dependentButtonsDiv.style.display === 'none') ? 'block' : 'none';
                                }
                            </script>
                            
                            <hr/>
                            
                        </div>
                    </div>
                  </c:forEach>
                  <c:choose>
                    <c:when test="${replylist.isEmpty()} ">
                    </c:when>
                    <c:otherwise>
                        <DIV class='bottom_menu'>${paging }</DIV> 
                    </c:otherwise>
                  </c:choose>
              </div>

            </div>
        </div>
    </section>
</div>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>