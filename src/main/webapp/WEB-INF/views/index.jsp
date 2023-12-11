<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" /> 
<title>http://localhost:9092/</title>
<link href="/css/style.css" rel="Stylesheet" type="text/css">

<style>
    body {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh; /* 화면 전체 높이를 설정 */
      margin: 0; /* body의 기본 마진 제거 */
    }

    .image-container {
      width: 70%;
      margin: 30px auto;
      display: flex;
    }

    .image-container img {
      width: 40%;
    }
</style>

</head>
<body>
<c:import url="/menu/top.do" />
 
 
<div class="image-container" id="imageContainer">
  <img src="/images/성규2.jpg" style="width:40%">
  <img src="/images/성규.jpg" style="width:40%; display: none">
</div>

<script>
  // 이미지 변경을 위한 함수
  function changeImages() {
    var imageContainer = document.getElementById("imageContainer");
    var images = imageContainer.getElementsByTagName("img");
    
    // 이미지의 display 속성을 toggle을 사용하여 변경
    images[0].style.display = (images[0].style.display === "none") ? "block" : "none";
    images[1].style.display = (images[1].style.display === "none") ? "block" : "none";
  }

  // 5초마다 changeImages 함수를 호출하여 이미지 변경
  setInterval(changeImages, 1000);
</script>


<jsp:include page="./menu/bottom.jsp" flush='false' /> 
</body>
</html>