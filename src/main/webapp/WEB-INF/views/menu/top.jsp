<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

<style>
  .top_menu_link:link{  /* 방문전 상태 */
    text-decoration: none; /* 밑줄 삭제 */
    color: #000000;
    font-weight: bold;
  }

  .top_menu_link:visited{  /* 방문후 상태 */
    text-decoration: none; /* 밑줄 삭제 */
    color: #000000;
    font-weight: bold;
  }

  .top_menu_link:hover{  /* A 태그에 마우스가 올라간 상태 */
    text-decoration: none; /* 밑줄 출력 */
    color: #000000;
    font-size: 1.05em;
  }
  
  .top_icon_label:hover{  /* A 태그에 마우스가 올라간 상태 */
    text-decoration: none; /* 밑줄 출력 */
    color: #000000;
  }
</style> 

<div class='container_main'>
  <div>
	  <div class='top_icon' style='float:left; margin-right: 10px;'></div>
	  <a class="top_icon_label" href="/">NBH Management</a>
	  
      <c:choose>
          <c:when test="${sessionScope.id == null}">
              <a class="nav-link top_menu_link" style='float:right; font-size: 20px; margin-top: 15px;' href="/customer/login.do">로그인</a>
          </c:when>
          <c:otherwise>
              <a class="nav-link top_menu_link" style='float:right; font-size: 20px; margin-top: 15px;' href='/customer/logout.do'>${sessionScope.cname } 로그아웃</a>
          </c:otherwise>
      </c:choose>   
  </div>

  <div class='top_img' style='margin-top: 26px;'>     </div> <!-- <div class='top_img'></div> 종료 -->
  

  <nav class="navbar navbar-expand-md navbar-dark bg-light">
      <a class="nav-link top_menu_link" style='margin-right: 10px;' href="/">HOME</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle Navigation">
        <span class="navbar-toggler-icon"></span>
      </button>    

      <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto">
            <%-- 게시판 목록 출력 --%>
            <c:forEach var="resVO" items="${list_top}">
              <c:set var="resno" value="${resVO.resno }" />
              <c:set var="resname" value="${resVO.resname }" />
              <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>
                <a class="nav-link top_menu_link" href="/rescontents/list_by_resno.do?resno=${resVO.resno }&now_page=1">${resVO.resname }</a> 
              </li>
            </c:forEach>
            
            <li class="nav-item"> <%-- 서브 메뉴가 없는 독립메뉴 --%>
              <a class="nav-link top_menu_link" href="/rescontents/list_all.do">전체 글 목록</a>
            </li>

            <li class="nav-item dropdown"> <%-- 회원 서브 메뉴 --%>
              <a class="nav-link top_menu_link dropdown-toggle" style='color: black;' data-bs-toggle="dropdown" href="#">회원</a>
              <div class="dropdown-menu">
                <c:choose>
                  <c:when test="${sessionScope.id == null }">
                    <a class="dropdown-item" href="/customer/create.do">회원 가입</a>
                    <a class="dropdown-item" href="#">아이디 찾기</a>
                    <a class="dropdown-item" href="#">비밀번호 찾기</a>
                  </c:when>
                  <c:otherwise>
                    <a class="dropdown-item" href="/requirement/read.do">마이페이지</a>
                    <a class="dropdown-item" href="/requirement/create.do">영양정보 관리</a>
                    <a class="dropdown-item" href="/customer/read_info.do">가입 정보</a>
                    <a class="dropdown-item" href="/customer/passwd_update.do">비밀번호 변경</a>
                    <a class="dropdown-item" href="/customer/read.do">회원 정보 수정</a>
                    <a class="dropdown-item" href="javascript: alert('개발 예정')">로그인 내역</a>
                    <a class="dropdown-item" href="javascript: alert('개발 예정')">회원 탈퇴</a>
                  </c:otherwise>
                </c:choose>
              </div>
            </li>
          
            <c:choose>
              <c:when test="${sessionScope.manager_id == null }">
                <li class="nav-item">
                  <a class="nav-link top_menu_link" href="/manager/login.do">관리자 로그인</a>
                </li>
              </c:when>
              <c:otherwise>
                <li class="nav-item dropdown"> <%-- 관리자 서브 메뉴 --%>
                  <a class="nav-link top_menu_link dropdown-toggle" style='color: black;' data-bs-toggle="dropdown" href="#">관리자</a>
                  <div class="dropdown-menu">
                    <a class="dropdown-item" href='/res/list_all.do'>카테고리 목록</a>
                    <a class="dropdown-item" href='/food/list_all.do'>음식 목록</a>
                    <a class="dropdown-item" href='/customer/list.do'>회원 목록</a>
                    <a class="dropdown-item" href='/manager/logout.do'>관리자 ${sessionScope.manager_id } 로그아웃</a>
                  </div>
                </li>
              </c:otherwise>
            </c:choose>
            
            
          </ul>
      </div>    
  </nav>
    
  <div class='content_body'> <!--  내용 시작 -->