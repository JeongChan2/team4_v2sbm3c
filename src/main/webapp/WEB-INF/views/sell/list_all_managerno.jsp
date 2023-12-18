<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9093/sell/list_all_managerno.do</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css"> <!-- /static 기준 -->
<link rel="shortcut icon" href="/images/star.png" /> <%-- /static 기준 --%>

<script type="text/javascript">

  window.onload = function() { // 자동 실행
    // select 태그의 변경 이벤트를 감지하고 선택된 항목의 레이블을 출력하는 함수
    document.getElementById("resno").addEventListener("change", function() {
    	document.getElementById("cnt").value = 0
      document.getElementById("price").value = 0
      // 선택된 항목의 값을 가져옴
      let resno = document.getElementById("resno").value; // foodno
      //if (resno==0) {
      //  document.getElementById('progress').innerHTML='음식점(식당)을 선택하세요.';
      //  document.getElementById('progress').style.display=""; // show
      //  return; // 함수 종료
      //}
      // 선택된 항목의 레이블을 가져옴
      // let selectedLabel = document.querySelector('option[value="' + selectedValue + '"]').innerHTML;
     
      // 결과를 출력
      // document.getElementById("selectedFruitLabel").textContent = "선택한 과일: " + selectedLabel;
      const url = './select_menu_fetch.do?resno=' + resno; // http://localhost:9091/javascript/select_menu_fetch.do
      console.log('-> url: ' + url);
      // const dataToSend = {foodno};
 
      fetch(url, {
        method: 'GET', // HTTP GET or POST 요청
        // headers: {
        //   'Content-Type': 'application/json' // JSON 형식으로 데이터 전송을 알림
        // },
        // body: JSON.stringify({foodno}) // 보내는 데이터, {"foodno":foodno}, object -> json
      })
      .then((response) => response.json())  // response.json() or response.text()
      .then((rdata) => {// 서버 응답 처리
        // console.log('-> 서버 응답옴 rdata: ' + rdata);
        // document.getElementById('progress').style.display="none"; // hide
        let foodno = document.getElementById('foodno'); // <select>
        
        // 기존에 등록된 메뉴 삭제 - 최상단 제외
        while (foodno.options.length > 1) {
        	foodno.remove(1);
        }

        for (let menu of rdata) { // Spring -> Js
          // console.log('-> menu.menuno:' + menu.menuno);
          // <option value="1" data-price="4000">라면</option>
          let option = document.createElement("option");
          option.text = menu.foodname; // 옵션 텍스트
          option.value = menu.foodno; // 옵션 값
          option.setAttribute("data-price", menu.price);
          foodno.appendChild(option);
        }
        foodno.focus();
      })
      .catch(error => { // 오류 처리
        console.log('-> error:' + error);
        // document.getElementById('progress').style.display="none"; // hide
      });
 
      // document.getElementById('progress').innerHTML = '<img src="/images/progress.gif" style="width: 5%;">'; // static 기준
      // document.getElementById('progress').style.display=""; // show
  });


  document.getElementById("foodno").addEventListener("change", function() {
    let menu = document.getElementById('foodno');
    var selectedIndex = menu.selectedIndex; // 선택된 옵션의 인덱스
    var selectedOption = menu.options[selectedIndex];

    let menuno = selectedOption.value; // menuno
    let name = selectedOption.textContent;
    let price = selectedOption.getAttribute("data-price");

    document.getElementById('name').value = name;
    document.getElementById('data-price').value = price;

    document.getElementById("cnt").value = 0
    document.getElementById("price").value = 0
    //console.log('-> menuno: ' + menuno);
    //console.log('-> name: ' + name);
    //console.log('-> price: ' + price);
  });

  document.getElementById("cnt").addEventListener("change", function() {
      // id가 "cnt"인 input 요소의 값을 가져오기
      var cntValue = document.getElementById("cnt").value;
      console.log('-> cnt' + cntValue);
      // 숫자인지 확인하고, 숫자이면 가격 계산
      if (!isNaN(cntValue) && cntValue !== "") {
        var totalPrice = cntValue * document.getElementById('data-price').value;
        
        // id가 "price"인 input 요소에 결과 반영
        document.getElementById("price").value = totalPrice;
      } else {
        // 숫자가 아닌 경우 또는 빈 값인 경우 가격을 초기화
        document.getElementById("price").value = "";
      }
    });

  }

</script>


</head>
<body>
<c:import url="/menu/top.do" />

    <div class='title_line'>판매내역 목록</div>

		<aside class="aside_right">
		  <a href="#">등록</a>
		  <span class='menu_divide' >│</span>
		  <a href="javascript:location.reload();">새로고침</a>
		</aside>
		<div class="menu_line"></div> 
		
		<!-- INSERT INTO sell(sellno, name, cnt, price, rdate, managerno, foodno, resno) -->
		<form name='frm' method='post' action='/sell/create.do'>
		<div style="margin-left:350px;">
		
		<div style="text-align: center; margin: 10px;">
		  <label for="resno">식당 분류</label>
		  <select name="resno" id="resno">
		    <option value="0">식당을 선택해주세요.</option>
		    <c:forEach var="resVO" items="${res_list}" varStatus="info">
		      <c:set var="resno" value="${resVO.resno}"/>
		      <c:set var="resname" value="${resVO.resname}"/>
		      <option value="${resno}">${resname}</option>
		    </c:forEach>
		  </select>
		</div>
     
     <div style="text-align: center; margin: 10px;">
       <label for="foodno">음식 이름</label>
       <select name="foodno" id="foodno">
         <option value="0">메뉴를 선택해주세요.</option>
       </select>
     </div>
     
     <input type="hidden" name="name" id="name" value="">
     <input type="hidden" name="data-price" id="data-price" value="">

		 <div style="text-align: center; margin: 10px;">
		    <label for="cnt" style="width: 10%; text-align: right;">개수</label>
        <input type="number" class="form-control form-control-sm" name="cnt" id="cnt" value="" required="required" placeholder="개수" autofocus="autofocus" 
                style="width: 20%; display: inline-block;">
     </div>
     
     <div style="text-align: center; margin: 10px;">
        <label for="price" style="width: 10%; text-align: right;">판매금액</label>
        <input type="text" class="form-control form-control-sm" name="price" id="price" value="" required="required" placeholder="판매금액" autofocus="autofocus" 
                style="width: 20%; display: inline-block;" readonly>
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
     <th class="th_bs">판매금액</th>
     <th class="th_bs">지출날짜</th>
     <th class="th_bs">식당이름</th>
     <th class="th_bs">기타</th>
      </tr>
  </thead>
  <tbody>
    <c:forEach var="Sell_JoinVO" items="${list }" varStatus="info">
      <c:set var="sellno" value="${Sell_JoinVO.sellno }"/>
		    <tr>
		      <td class="td_bs">${info.count }</td>
		      <td class="td_bs">${Sell_JoinVO.name }</td>
		      <td class="td_bs">${Sell_JoinVO.cnt }</td>
		      <td class="td_bs">${Sell_JoinVO.price }</td>
		      <td class="td_bs">${Sell_JoinVO.rdate }</td>
		      <td class="td_bs">${Sell_JoinVO.resname }</td>
		      <td class="td_bs">
		        <a href="./update.do?sellno=${sellno }"><img src="/res/images/update.png" class="icon"></a>
		        <a href="./delete.do?sellno=${sellno }"><img src="/res/images/delete.png" class="icon"></a>
		      </td>
		    </tr>
    </c:forEach>

  </tbody>
</table>
 
<jsp:include page="../menu/bottom.jsp" flush='false' /> 
</body>
</html>