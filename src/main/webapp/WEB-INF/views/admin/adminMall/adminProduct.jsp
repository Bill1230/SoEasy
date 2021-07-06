<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Product</title>

<link href="${pageContext.request.contextPath}/css/admin.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mall/malladmin/mall-bootstrap.min.css" href="${pageContext.request.contextPath}/css/mall/malladmin/mall-common.css" type="text/css">



<script type="text/javascript">
	window.onload = function(){
		let switch_moon = document.getElementById("switch_moon");
		let xhr = new XMLHttpRequest();
//		點擊日夜間模式設定session
		switch_moon.addEventListener("click", function(){
			document.documentElement.classList.toggle('dark');
			switch_moon.classList.toggle('active');
			xhr.open("GET", "<c:url value='/admin/switchMode.json' />", true);
			xhr.send();
		})
		
		if("${switchMode}" == "" || "${switchMode}" == "sun"){
		} else if("${switchMode}" == "moon"){
			document.documentElement.classList.toggle('dark');			
		}
	}
	
	
	
	
	

</script>
</head>
<body>
	<div class="app-container"">
  <div class="sidebar">
    <div class="sidebar-header">
      <div class="app-icon">
      	<a href="<c:url value="/"></c:url>">
      	
      	<svg version="1.0" xmlns="http://www.w3.org/2000/svg"
 width="222pt" height="124pt" viewBox="0 0 222 124"
 preserveAspectRatio="xMidYMid meet">

<g transform="translate(0.000000,124.000000) scale(0.100000,-0.100000)"
fill="none" stroke="none">
<path d="M666 645 c-42 -63 -83 -115 -91 -115 -21 0 -19 17 6 52 l22 30 -21
20 c-27 28 -55 17 -92 -38 -17 -24 -39 -48 -50 -54 -28 -15 -60 -12 -60 5 0 9
9 15 23 15 42 0 81 66 53 89 -24 20 -53 3 -85 -49 -26 -42 -64 -71 -76 -58 -3
2 21 46 52 97 38 62 53 95 46 102 -7 7 -29 -22 -69 -90 -57 -97 -73 -111 -103
-92 -11 7 -5 25 34 91 30 52 44 85 38 91 -11 11 -102 -36 -110 -58 -9 -24 5
-26 28 -4 38 35 42 24 10 -31 -30 -52 -31 -53 -79 -53 -63 -1 -102 -25 -102
-64 0 -23 6 -31 28 -39 33 -11 67 0 103 34 23 22 29 23 52 13 14 -6 23 -17 21
-24 -3 -7 -1 -16 5 -20 6 -3 14 1 17 9 3 9 18 16 33 16 14 0 32 4 40 9 9 6 23
4 39 -7 20 -13 29 -13 58 -3 28 10 38 10 50 0 12 -10 20 -10 35 -1 15 10 23 9
38 0 16 -10 23 -9 39 5 17 16 21 16 32 2 16 -19 29 -19 53 2 18 17 19 17 28 0
16 -28 85 -16 132 23 35 29 38 30 32 11 -6 -21 8 -51 25 -51 16 0 70 52 70 66
0 9 -15 0 -37 -22 -47 -48 -54 -29 -13 36 47 73 28 82 -37 18 -61 -58 -56 -35
7 42 45 54 63 87 55 100 -11 17 -25 1 -90 -99 -64 -98 -76 -113 -108 -124 -30
-11 -31 21 -2 63 20 29 35 40 54 40 23 1 24 2 9 14 -12 9 -14 16 -7 23 8 8 7
13 -1 18 -7 4 -18 -2 -26 -14 -8 -12 -24 -21 -36 -21 -15 0 -18 -3 -10 -13 14
-17 -52 -107 -79 -107 -10 0 -19 3 -19 6 0 12 24 49 76 116 50 65 68 108 45
108 -6 0 -44 -52 -85 -115z m-216 -10 c0 -12 -32 -54 -46 -59 -20 -8 -17 7 7
38 18 23 39 34 39 21z m120 -11 c0 -25 -57 -94 -78 -94 -12 0 -11 5 3 28 33
51 75 89 75 66z m-406 -53 c9 -15 -64 -71 -93 -71 -21 0 -11 39 15 59 27 21
69 28 78 12z"/>
<path d="M1185 731 c-39 -23 -83 -75 -91 -107 -7 -28 14 -57 58 -80 39 -20 36
-38 -4 -31 -18 4 -44 9 -58 13 -18 5 -22 4 -15 -4 6 -6 31 -16 57 -23 38 -10
49 -10 62 0 27 22 18 39 -29 60 -50 22 -56 41 -30 92 17 31 89 89 112 89 41 0
-16 -97 -74 -126 -33 -16 -35 -19 -16 -22 61 -9 149 90 123 139 -14 24 -55 25
-95 0z"/>
<path d="M1602 733 c-19 -9 -36 -22 -39 -30 -10 -24 -25 -14 -19 13 5 19 3 25
-6 22 -17 -6 -23 -47 -9 -61 6 -6 11 -21 11 -33 0 -13 5 -26 10 -29 14 -9 12
-25 -3 -25 -18 0 -47 -32 -47 -53 0 -22 48 -57 79 -57 15 0 43 12 64 26 32 21
41 23 53 13 13 -10 20 -10 36 0 16 10 23 10 35 0 12 -10 19 -10 35 0 15 9 23
10 31 2 6 -6 24 -11 39 -11 26 0 28 3 28 40 0 22 7 57 15 76 14 35 13 54 -5
54 -4 0 -22 -21 -39 -47 -39 -60 -77 -103 -91 -103 -16 0 -12 19 11 52 l22 30
-21 20 c-28 28 -54 17 -97 -43 -50 -71 -136 -109 -165 -74 -16 19 0 41 47 60
30 13 46 35 25 35 -5 0 -17 7 -28 14 -17 14 -15 15 29 14 57 -1 100 26 105 64
2 18 -4 27 -23 36 -34 15 -43 15 -83 -5z m88 -17 c0 -24 -50 -56 -86 -56 -41
0 -43 12 -6 38 48 34 92 42 92 18z m90 -92 c0 -25 -57 -94 -78 -94 -12 0 -11
5 3 28 33 51 75 89 75 66z m96 -90 c-3 -8 -10 -14 -16 -14 -20 0 -22 31 -4 52
l19 20 3 -22 c2 -12 1 -28 -2 -36z"/>
<path d="M1271 621 c-12 -16 -24 -43 -27 -60 -5 -26 -3 -32 19 -42 22 -10 31
-9 52 5 14 9 29 15 34 12 13 -8 51 24 50 42 0 11 -2 12 -6 4 -2 -7 -14 -17
-25 -23 -20 -11 -21 -9 -15 21 14 68 -43 96 -82 41z m49 -28 c0 -29 -25 -63
-47 -63 -16 0 -10 30 13 68 19 30 34 28 34 -5z"/>
<path d="M1972 626 c-33 -43 -46 -84 -32 -101 7 -8 20 -15 29 -15 12 0 9 -6
-11 -23 -39 -33 -63 -87 -38 -87 6 0 26 22 45 49 20 27 46 52 59 55 24 6 76
52 76 68 0 6 -16 -4 -35 -21 -65 -57 -64 -40 2 66 9 13 10 23 4 27 -5 4 -28
-18 -50 -48 -42 -56 -59 -72 -67 -63 -6 5 15 47 42 84 22 30 0 39 -24 9z"/>
</g>
</svg>
</a>
      </div>
    </div>
    <ul class="sidebar-list">
      <li class="sidebar-list-item">
        <a href="<c:url value="/admin/adminLogin"></c:url>">
<!--           <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-home"><path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"/><polyline points="9 22 9 12 15 12 15 22"/></svg> -->
          <span>後台首頁</span>
        </a>
      </li>
      <li class="sidebar-list-item">
        <a href="<c:url value="/admin/adminManage/adminCustomer"></c:url>">
<!--           <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-shopping-bag"><path d="M6 2L3 6v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2V6l-3-4z"/><line x1="3" y1="6" x2="21" y2="6"/><path d="M16 10a4 4 0 0 1-8 0"/></svg> -->
          <span>會員</span>
        </a>
      </li>
      <li class="sidebar-list-item">
        <a href="<c:url value="/admin/adminManage/adminShop"></c:url>">
<!--           <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-pie-chart"><path d="M21.21 15.89A10 10 0 1 1 8 2.83"/><path d="M22 12A10 10 0 0 0 12 2v10z"/></svg> -->
          <span>廠商</span>
        </a>
      </li>
      <li class="sidebar-list-item  active">
        <a href="<c:url value="/admin/adminManage/adminProduct"></c:url>">
<!--           <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-inbox"><polyline points="22 12 16 12 14 15 10 15 8 12 2 12"/><path d="M5.45 5.11L2 12v6a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2v-6l-3.45-6.89A2 2 0 0 0 16.76 4H7.24a2 2 0 0 0-1.79 1.11z"/></svg> -->
          <span>商品</span>
        </a>
      </li>
      <li class="sidebar-list-item">
        <a href="<c:url value="/admin/adminManage/adminOrder"></c:url>">
<!--           <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-bell"><path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"/><path d="M13.73 21a2 2 0 0 1-3.46 0"/></svg> -->
          <span>訂單</span>
        </a>
      </li>
      <li class="sidebar-list-item">
        <a href="<c:url value="/admin/adminManage/adminLecture"></c:url>">
<!--           <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-bell"><path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"/><path d="M13.73 21a2 2 0 0 1-3.46 0"/></svg> -->
          <span>講座</span>
        </a>
      </li>
      <li class="sidebar-list-item">
        <a href="<c:url value="/admin/adminManage/adminArticle"></c:url>">
<!--           <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-bell"><path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"/><path d="M13.73 21a2 2 0 0 1-3.46 0"/></svg> -->
          <span>文章</span>
        </a>
      </li>
      <li class="sidebar-list-item">
        <a href="<c:url value="/admin/adminManage/adminNutritionist"></c:url>">
<!--           <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-bell"><path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"/><path d="M13.73 21a2 2 0 0 1-3.46 0"/></svg> -->
          <span>營養師</span>
        </a>
      </li>
      <li class="sidebar-list-item">
        <a href="<c:url value="/admin/adminManage/adminRecord"></c:url>">
<!--           <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-bell"><path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"/><path d="M13.73 21a2 2 0 0 1-3.46 0"/></svg> -->
          <span>日誌</span>
        </a>
      </li>      
      <li class="sidebar-list-item">
        <a href="<c:url value="/admin/adminManage/adminPost"></c:url>">
<!--           <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-bell"><path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"/><path d="M13.73 21a2 2 0 0 1-3.46 0"/></svg> -->
          <span>分享</span>
        </a>
      </li>
      <li class="sidebar-list-item">
        <a href="<c:url value="/admin/adminManage/adminSportMap"></c:url>">
<!--           <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-bell"><path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"/><path d="M13.73 21a2 2 0 0 1-3.46 0"/></svg> -->
          <span>地圖</span>
        </a>
      </li>
    </ul>
    <div class="account-info">
      <div class="account-info-picture">
<!--         <img src="https://images.unsplash.com/photo-1527736947477-2790e28f3443?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTE2fHx3b21hbnxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=900&q=60" alt="Account"> -->
      		<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                            stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M5.121 17.804A13.937 13.937 0 0112 16c2.5 0 4.847.655 6.879 1.804M15 10a3 3 0 11-6 0 3 3 0 016 0zm6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
      </div>
      <div class="account-info-name">Admin S.</div>
      <button class="account-info-more">
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-more-horizontal"><circle cx="12" cy="12" r="1"/><circle cx="19" cy="12" r="1"/><circle cx="5" cy="12" r="1"/></svg>
      </button>
    </div>
  </div>
  <div class="app-content">
    <div class="app-content-header">
      <h1 class="app-content-headerText">Products Management</h1>
<!--       月亮 -->
      <button class="mode-switch" title="Switch Theme" id="switch_moon">
        <svg class="moon" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" width="24" height="24" viewBox="0 0 24 24">
          <defs></defs>
          <path d="M21 12.79A9 9 0 1111.21 3 7 7 0 0021 12.79z"></path>
        </svg>
      </button>
<!--   ===================  新增 =========================================================================-->
      <a href="<c:url value='/admin/adminManage/add'/>">
      <button class="app-content-headerButton">新增產品</button></a>
    </div>
    <div class="app-content-actions">
    
  <!--   ===============(ENd) 新增 =========================================================================-->
    
<!--   ===================  搜尋 =========================================================================-->

<div  style="width:500px">
<form action="<c:url value='/admin/adminManage/adminProduct/1'/>">
<input type="hidden" name="sortField"  value="${sortField}"/>
<input type="hidden" name="sortDir"  value="${sortDir}"/>
<input class="search-bar" placeholder="Search..."  type="text" name="keyword"  value="${keyword}" required/>
<input  class="app-content-headerButton" type="submit" value="確定查詢"/>
<input  class="app-content-headerButton" type="button" value="Reset" onclick="clearFilter()">
</form>

</div>  
		  
		 
<!--   ===================  (End)搜尋 =========================================================================-->
  
  
  
  
      <div class="app-content-actions-wrapper">
        <div class="filter-button-wrapper">
<!--         過濾器 -->
          <button class="action-button filter jsFilter"><span>Filter</span><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-filter"><polygon points="22 3 2 3 10 12.46 10 19 14 21 14 12.46 22 3"/></svg></button>
          <div class="filter-menu">
            <label>Category</label>
            <select>
              <option>All Categories</option>
              <option>Furniture</option>                     
              <option>Decoration</option>
              <option>Kitchen</option>
              <option>Bathroom</option>
            </select>
            <label>Status</label>
            <select>
              <option>All Status</option>
              <option>Active</option>
              <option>Disabled</option>
            </select>
            <div class="filter-menu-buttons">
              <button class="filter-button reset">
                Reset
              </button>
              <button class="filter-button apply">
                Apply
              </button>
            </div>
          </div>
        </div>
        <button class="action-button list active" title="List View">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-list"><line x1="8" y1="6" x2="21" y2="6"/><line x1="8" y1="12" x2="21" y2="12"/><line x1="8" y1="18" x2="21" y2="18"/><line x1="3" y1="6" x2="3.01" y2="6"/><line x1="3" y1="12" x2="3.01" y2="12"/><line x1="3" y1="18" x2="3.01" y2="18"/></svg>
        </button>
        <button class="action-button grid" title="Grid View">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-grid"><rect x="3" y="3" width="7" height="7"/><rect x="14" y="3" width="7" height="7"/><rect x="14" y="14" width="7" height="7"/><rect x="3" y="14" width="7" height="7"/></svg>
        </button>
      </div>
    </div>
    
    <div class="products-area-wrapper tableView">
<!--  --------------------------- ----表頭欄位--------------------------- -->

      <div class="products-header">
        <div class="product-cell image">
          產品編號
          <a href="<c:url value='/admin/adminManage/adminProduct/${currentPage}?sortField=productId&sortDir=${reverSortDir}'/>">
          <button class="sort-button">
            <svg xmlns="http://www.w3.org/2000/svg" width="5" height="16" viewBox="0 0 512 512"><path fill="currentColor" d="M496.1 138.3L375.7 17.9c-7.9-7.9-20.6-7.9-28.5 0L226.9 138.3c-7.9 7.9-7.9 20.6 0 28.5 7.9 7.9 20.6 7.9 28.5 0l85.7-85.7v352.8c0 11.3 9.1 20.4 20.4 20.4 11.3 0 20.4-9.1 20.4-20.4V81.1l85.7 85.7c7.9 7.9 20.6 7.9 28.5 0 7.9-7.8 7.9-20.6 0-28.5zM287.1 347.2c-7.9-7.9-20.6-7.9-28.5 0l-85.7 85.7V80.1c0-11.3-9.1-20.4-20.4-20.4-11.3 0-20.4 9.1-20.4 20.4v352.8l-85.7-85.7c-7.9-7.9-20.6-7.9-28.5 0-7.9 7.9-7.9 20.6 0 28.5l120.4 120.4c7.9 7.9 20.6 7.9 28.5 0l120.4-120.4c7.8-7.9 7.8-20.7-.1-28.5z"/></svg>
          </button>
          </a>
          
        </div>
        <div class="product-cell category">Name
         <a href="<c:url value='/admin/adminManage/adminProduct/${currentPage}?sortField=productId&sortDir=${reverSortDir}'/>">
          <button class="sort-button">
            <svg xmlns="http://www.w3.org/2000/svg" width="5" height="16" viewBox="0 0 512 512"><path fill="currentColor" d="M496.1 138.3L375.7 17.9c-7.9-7.9-20.6-7.9-28.5 0L226.9 138.3c-7.9 7.9-7.9 20.6 0 28.5 7.9 7.9 20.6 7.9 28.5 0l85.7-85.7v352.8c0 11.3 9.1 20.4 20.4 20.4 11.3 0 20.4-9.1 20.4-20.4V81.1l85.7 85.7c7.9 7.9 20.6 7.9 28.5 0 7.9-7.8 7.9-20.6 0-28.5zM287.1 347.2c-7.9-7.9-20.6-7.9-28.5 0l-85.7 85.7V80.1c0-11.3-9.1-20.4-20.4-20.4-11.3 0-20.4 9.1-20.4 20.4v352.8l-85.7-85.7c-7.9-7.9-20.6-7.9-28.5 0-7.9 7.9-7.9 20.6 0 28.5l120.4 120.4c7.9 7.9 20.6 7.9 28.5 0l120.4-120.4c7.8-7.9 7.8-20.7-.1-28.5z"/></svg>
          </button>
          </a>
          
        </div>
        <div class="product-cell category">Category<a href="<c:url value='/admin/adminManage/adminProduct/${currentPage}?sortField=productId&sortDir=${reverSortDir}'/>">
          <button class="sort-button">
            <svg xmlns="http://www.w3.org/2000/svg" width="5" height="16" viewBox="0 0 512 512"><path fill="currentColor" d="M496.1 138.3L375.7 17.9c-7.9-7.9-20.6-7.9-28.5 0L226.9 138.3c-7.9 7.9-7.9 20.6 0 28.5 7.9 7.9 20.6 7.9 28.5 0l85.7-85.7v352.8c0 11.3 9.1 20.4 20.4 20.4 11.3 0 20.4-9.1 20.4-20.4V81.1l85.7 85.7c7.9 7.9 20.6 7.9 28.5 0 7.9-7.8 7.9-20.6 0-28.5zM287.1 347.2c-7.9-7.9-20.6-7.9-28.5 0l-85.7 85.7V80.1c0-11.3-9.1-20.4-20.4-20.4-11.3 0-20.4 9.1-20.4 20.4v352.8l-85.7-85.7c-7.9-7.9-20.6-7.9-28.5 0-7.9 7.9-7.9 20.6 0 28.5l120.4 120.4c7.9 7.9 20.6 7.9 28.5 0l120.4-120.4c7.8-7.9 7.8-20.7-.1-28.5z"/></svg>
          </button>
          </a>
          
        </div>
        
        <div class="product-cell sales">Price <a href="<c:url value='/admin/adminManage/adminProduct/${currentPage}?sortField=productId&sortDir=${reverSortDir}'/>">
          <button class="sort-button">
            <svg xmlns="http://www.w3.org/2000/svg" width="5" height="16" viewBox="0 0 512 512"><path fill="currentColor" d="M496.1 138.3L375.7 17.9c-7.9-7.9-20.6-7.9-28.5 0L226.9 138.3c-7.9 7.9-7.9 20.6 0 28.5 7.9 7.9 20.6 7.9 28.5 0l85.7-85.7v352.8c0 11.3 9.1 20.4 20.4 20.4 11.3 0 20.4-9.1 20.4-20.4V81.1l85.7 85.7c7.9 7.9 20.6 7.9 28.5 0 7.9-7.8 7.9-20.6 0-28.5zM287.1 347.2c-7.9-7.9-20.6-7.9-28.5 0l-85.7 85.7V80.1c0-11.3-9.1-20.4-20.4-20.4-11.3 0-20.4 9.1-20.4 20.4v352.8l-85.7-85.7c-7.9-7.9-20.6-7.9-28.5 0-7.9 7.9-7.9 20.6 0 28.5l120.4 120.4c7.9 7.9 20.6 7.9 28.5 0l120.4-120.4c7.8-7.9 7.8-20.7-.1-28.5z"/></svg>
          </button>
          </a>
        </div>
        
        
        <div class="product-cell stock">Cost<a href="<c:url value='/admin/adminManage/adminProduct/${currentPage}?sortField=productId&sortDir=${reverSortDir}'/>">
          <button class="sort-button">
            <svg xmlns="http://www.w3.org/2000/svg" width="5" height="16" viewBox="0 0 512 512"><path fill="currentColor" d="M496.1 138.3L375.7 17.9c-7.9-7.9-20.6-7.9-28.5 0L226.9 138.3c-7.9 7.9-7.9 20.6 0 28.5 7.9 7.9 20.6 7.9 28.5 0l85.7-85.7v352.8c0 11.3 9.1 20.4 20.4 20.4 11.3 0 20.4-9.1 20.4-20.4V81.1l85.7 85.7c7.9 7.9 20.6 7.9 28.5 0 7.9-7.8 7.9-20.6 0-28.5zM287.1 347.2c-7.9-7.9-20.6-7.9-28.5 0l-85.7 85.7V80.1c0-11.3-9.1-20.4-20.4-20.4-11.3 0-20.4 9.1-20.4 20.4v352.8l-85.7-85.7c-7.9-7.9-20.6-7.9-28.5 0-7.9 7.9-7.9 20.6 0 28.5l120.4 120.4c7.9 7.9 20.6 7.9 28.5 0l120.4-120.4c7.8-7.9 7.8-20.7-.1-28.5z"/></svg>
          </button>
          </a>
        </div>
        
        
        <div class="product-cell status-cell">Amount<a href="<c:url value='/admin/adminManage/adminProduct/${currentPage}?sortField=productId&sortDir=${reverSortDir}'/>">
          <button class="sort-button">
            <svg xmlns="http://www.w3.org/2000/svg" width="5" height="16" viewBox="0 0 512 512"><path fill="currentColor" d="M496.1 138.3L375.7 17.9c-7.9-7.9-20.6-7.9-28.5 0L226.9 138.3c-7.9 7.9-7.9 20.6 0 28.5 7.9 7.9 20.6 7.9 28.5 0l85.7-85.7v352.8c0 11.3 9.1 20.4 20.4 20.4 11.3 0 20.4-9.1 20.4-20.4V81.1l85.7 85.7c7.9 7.9 20.6 7.9 28.5 0 7.9-7.8 7.9-20.6 0-28.5zM287.1 347.2c-7.9-7.9-20.6-7.9-28.5 0l-85.7 85.7V80.1c0-11.3-9.1-20.4-20.4-20.4-11.3 0-20.4 9.1-20.4 20.4v352.8l-85.7-85.7c-7.9-7.9-20.6-7.9-28.5 0-7.9 7.9-7.9 20.6 0 28.5l120.4 120.4c7.9 7.9 20.6 7.9 28.5 0l120.4-120.4c7.8-7.9 7.8-20.7-.1-28.5z"/></svg>
          </button>
          </a>
        </div>
        <div class="product-cell status-cell">Date<a href="<c:url value='/admin/adminManage/adminProduct/${currentPage}?sortField=productId&sortDir=${reverSortDir}'/>">
          <button class="sort-button">
            <svg xmlns="http://www.w3.org/2000/svg" width="5" height="16" viewBox="0 0 512 512"><path fill="currentColor" d="M496.1 138.3L375.7 17.9c-7.9-7.9-20.6-7.9-28.5 0L226.9 138.3c-7.9 7.9-7.9 20.6 0 28.5 7.9 7.9 20.6 7.9 28.5 0l85.7-85.7v352.8c0 11.3 9.1 20.4 20.4 20.4 11.3 0 20.4-9.1 20.4-20.4V81.1l85.7 85.7c7.9 7.9 20.6 7.9 28.5 0 7.9-7.8 7.9-20.6 0-28.5zM287.1 347.2c-7.9-7.9-20.6-7.9-28.5 0l-85.7 85.7V80.1c0-11.3-9.1-20.4-20.4-20.4-11.3 0-20.4 9.1-20.4 20.4v352.8l-85.7-85.7c-7.9-7.9-20.6-7.9-28.5 0-7.9 7.9-7.9 20.6 0 28.5l120.4 120.4c7.9 7.9 20.6 7.9 28.5 0l120.4-120.4c7.8-7.9 7.8-20.7-.1-28.5z"/></svg>
          </button>
          </a>
        </div>
        
        
        <div class="product-cell price">
          Update
        </div>
        
        
        
        <div class="product-cell price">
          Delete
        </div>
        
        
      </div>
	<!--       ----------------------一筆資料內容--開始--------------------- -->
     <c:forEach var='product' items="${product}">
					<div class="products-row">
						<button class="cell-more-button">
							<svg xmlns="http://www.w3.org/2000/svg" width="10" height="18"
								viewBox="0 0 24 24" fill="none" stroke="currentColor"
								stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
								class="feather feather-more-vertical">
								<circle cx="12" cy="12" r="1" />
								
								<circle cx="12" cy="5" r="1" />
								
								<circle cx="12" cy="19" r="1" /></svg>
						</button>
					
						<div class="product-cell category">
							<span class="cell-label">Id:</span> ${product.productId}
						</div>
						<div class="product-cell category">
							<span class="cell-label">Name:</span><a href="<c:url value='/mall/lists/product/${product.productId}'/>">
							${product.productName}</a>
							
						</div>
						<div class="product-cell category">
							<span class="cell-label">Category:</span> ${product.category}
						</div>
						
						<div class="product-cell stock">
							<span class="cell-label">Price:</span>${product.productPrice}
						</div>
						<div class="product-cell stock">
							<span class="cell-label">Cost:</span>${product.productCost}
						</div>
						
						<div class="product-cell stock">
							<span class="cell-label">Amount:</span>${product.productAmount}
						</div>
						
						<div class="product-cell stock">
							<span class="cell-label">Date:</span>${product.productDate}
						</div>

									<!--       更新(JSPu頁面建置)/刪除 按鈕 -->
					
	
						 <div class="product-cell price"><a	href="<c:url value='/admin/adminManage/adminUpdate/${product.productId}'/>"><button class="app-content-headerButton">Update</button></a></div>
                  
      					<div class="product-cell price"><a onclick="return del(this)" href="<c:url value='/admin/adminManage/adminDelete/${product.productId}'/>"><button class="app-content-headerButton">Delete</button></a></div>
					</div>
				</c:forEach>
				<!--       一筆資料內容--結尾 -->

			</div>
		
		
		
 <!-- ---------------------------控制分頁用----------------------------------- -->
 <span>產品總數:<c:out value="${totalItems}"/></span>
 <nav style="width:300px;margin:0px auto">
 
 
<ul class=pager>

<!--  上頁 -->
<li class=previous> 
	<c:choose>
		<c:when test="${currentPage > 1}">
			<a href="<c:url value='/admin/adminManage/adminProduct/${currentPage - 1}?sortField=${sortField}&sortDir=${reverSortDir}'/>">Previous</a>
		</c:when>
		<c:otherwise>
			<a>Previous</a>
		</c:otherwise>
	</c:choose>
</li>
<!-- 上頁結束 -->

<li style="font-style:italic;color:	#00477D">Page &nbsp;<c:out value="${currentPage}"/>&nbsp; of &nbsp;&nbsp;<c:out value="${totalPages}"/></li>

&nbsp;

 <!--  下頁 -->
<li class=next> 
	<c:choose>
		<c:when test="${currentPage < totalPages}">
			<a href="<c:url value='/admin/adminManage/adminProduct/${currentPage + 1}?sortField=${sortField}&sortDir=${reverSortDir}'/>">Next</a>
		</c:when>
		<c:otherwise>
			<a>Previous</a>
		</c:otherwise>
	</c:choose>
</li>
<!-- 下頁結束 -->
 
</ul>
 </nav>

<!-- ------------------------------控制分頁結束區塊--------------------------------- -->


 
		
		</div>
		
		
	</div>
	<!-- partial -->
	<script src="${pageContext.request.contextPath}/js/admin.js"></script>>
	<!-- 	---------------------用來接delete的post--------------------------	 -->
	<form method='POST'>
		<input type='hidden' name='_method' value='DELETE'>
	</form>

	<!-- ----------------------------刪除單筆資料用-------------------------------- -->

  <script type="text/javascript">
	//將 get 請求轉換為 post 請求提交
	function del(tag) {
		//獲取當前請求路徑
       var href = tag.href;
       //提交
       $("form").attr("action", href).submit();
       return delmag();
       
       function delmag() {
    	   var msg = "您確定要刪除這樣產品嗎？";
    	   if (confirm(msg)==true){
    	   return true;
    	   }else{
    	   return false;
    	   }
    	   }
     }
  </script>
	
	
	 <!-- ======================    綁定清除查詢 ================================-->
  
  
  <script type="text/javascript">
    function clearFilter(){
		window.location='/soeasy/admin/adminManage/adminProduct/';     
    }
  </script>
  
  <!-- ==========================   (End) 綁定清除查詢========================-->
  
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/mall-bootstrap.min.js" ></script>
 
 <script src="https//ajax.google"></script><!-- 載入 jQuery 套件 -->
 <script src="js/mall-bootstrap3.js"></script>

</body>
</html>