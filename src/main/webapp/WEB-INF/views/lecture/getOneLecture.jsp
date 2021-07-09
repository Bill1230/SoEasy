<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>講座介紹</title>
<link
	href="http://www.francescomalagrino.com/BootstrapPageGenerator/3/css/bootstrap-combined.min.css"
	rel="stylesheet" media="screen">
<link
	href="https://fonts.googleapis.com/css2?family=Caudex&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Lobster&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="css/categoryNav.css">
<link href="css/header&footer.css" rel="stylesheet">

<!-- 標題字型 -->
<link
	href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;500&display=swap"
	rel="stylesheet">
<!-- 輪播 -->
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<script src='https://kit.fontawesome.com/a076d05399.js'
	crossorigin='anonymous'></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/lecture/lectureIndex.css">
</head>

<body>
	<!-- 引入共同的頁首  copy這行-->
	<jsp:include page="/fragment/header.jsp" />

	<!-- 	綠底標頭 -->
	<div class="mainWrapper">
		<section id="offer">
			<div style="background-color: #C9D8BF; text-align: center;">
				<div style="margin: 5% auto;">
					<h1 style="color: #1d3b2a; font-family: Caudex;">So Lecture</h1>
					<p style="color: #325b43; font-family: Lobster; font-size: 18px;">
						吃對食物才會越吃越瘦、正確運動才不會造成運動傷害！</p>

					<p style="color: #325b43; font-family: Lobster; font-size: 18px;">
						讓各領域專業講師帶您一起瘦回健康體態！</p>
				</div>
			</div>
		</section>
	</div>

	<!---------------------- 內容開始區 ---------------------->

	<!------- 小表頭區 ------->
	<div id='pageWrapper'>
		<div id='headerBlank'>
			<a href='<c:url value='/'></c:url>'><button	class="custom-btn btn-5">首頁</button></a> 
			<a href='<c:url value='/lecturerIndex' />'><button class="custom-btn btn-5">講師介紹</button></a> 
			<a href='<c:url value='/lectureIndex' />'><button class="custom-btn btn-5">進行中講座</button></a> 
			<a href='<c:url value='/lectureIndex' />'><button class="custom-btn btn-5">即將舉辦講座</button></a>
			<a href='<c:url value='/lectureController/lecture/archivedLectures' />'><button class="custom-btn btn-5">精彩回顧講座</button></a> 
			<input type="text" class="search-bar" placeholder="搜尋講座">
		</div>
		<hr>
	</div>

	
	<!-- 進行中講座區 -->
	<div id='pageWrapper'>
		<div class="header-title">${lecture.lectureStatus} Lecture</div>
		<hr>

		<!-------------------------------- 輪播測試區 -------------------------------->
		<div class="wrapper">
			<ul class="slides">
		<c:forEach varStatus="" var='lecture' items='${lectures}'>
				<li>
<!-- 					用box把圖片和文字包起來 -->
					<div class="box">
						<div class="lectureImg">
							<img src="<c:url value='/lectureController/getLectureImage/${lecture.lectureId}' />" alt="">
						</div>
						<div class="boxRight">
						<br>
							<font class="lectureTitle">${lecture.lectureTitle}</font>
							<p class="lectureDate">講座時間：<font>${lecture.lectureDate}</font></p>
							<p class="lectureCategory">講座分類：<font>${lecture.lectureCategory == "Eat"? "飲食": "運動"}</font></p>
							<p class="lectureContent">講座內容：<font>${fn:substring(lecture.lectureContent,0,200)}...</font></p>
<%-- 							<p class=lectureStatus>講座狀態：<font>${lecture.lectureStatus == 2}</font></p> --%>
																
							<a href="<c:url value='/lectureController/lecture/${lecture.lectureId}' />"><button
									class="custom2-btn btn-4">(查看更多...)</button></a>
						</div>
					</div>
<!-- 					用box把圖片和文字包起來 -->
				</li>
						</c:forEach>
			</ul>
			
			<ul class="dot">
			<c:forEach var='lecture' items='${lectures}'>
				<li id="${lecture.lectureId}"></li>
			</c:forEach>
			</ul>
			<div id="prevSlide" class="slide_btn">
				<i class="fas fa-caret-left"></i>
			</div>
			<div id="nextSlide" class="slide_btn">
				<i class="fas fa-caret-right"></i>
			</div>
		
		</div>
		
		
		<hr>
		<script
			src="${pageContext.request.contextPath}/js/lecture/lectureIndex.js"></script>
		<!--  ------ -->
	</div>
	
	<!-- ------------------------------------------------------------------------------>

	<!-- 即將舉辦講座區 -->
	<div id='pageWrapper'>
		<div class="header-title">Upcoming Lecture</div>
		<hr>


	</div>


	<!-- 精采回顧區 -->
	<div id='pageWrapper'>
		<div class="header-title">Archived Lecture</div>
		<hr>

	</div>

	<!-- 引入共同的頁尾  copy這行-->
	<jsp:include page="/fragment/footer.jsp" />
</body>
</html>