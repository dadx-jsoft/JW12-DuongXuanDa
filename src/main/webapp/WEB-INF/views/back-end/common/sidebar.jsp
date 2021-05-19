<!-- sử dụng tiếng việt -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<ul
	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
	id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<a
		class="sidebar-brand d-flex align-items-center justify-content-center"
		href="${base}/admin">
		<div class="sidebar-brand-icon rotate-n-15">
			<i class="fas fa-clock"></i>
		</div>
		<div class="sidebar-brand-text mx-3">
			Wada shop
		</div>
	</a>

	<!-- Divider -->
	<!-- <hr class="sidebar-divider"> -->

	<!-- Heading -->
	<!-- <div class="sidebar-heading">Interface</div> -->

	<!-- Nav Item - Utilities Collapse Menu -->
	<!-- <li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseUtilities"
		aria-expanded="true" aria-controls="collapseUtilities"> <i
			class="fas fa-fw fa-wrench"></i> <span>Utilities</span>
	</a>
		<div id="collapseUtilities" class="collapse"
			aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Custom Utilities:</h6>
				<a class="collapse-item" href="utilities-color.html">Colors</a> <a
					class="collapse-item" href="utilities-border.html">Borders</a> <a
					class="collapse-item" href="utilities-animation.html">Animations</a>
				<a class="collapse-item" href="utilities-other.html">Other</a>
			</div>
		</div></li> -->

	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Heading -->
	<div class="sidebar-heading">Quản lý</div>

	<!-- Nav Item - Charts -->
	<%-- <li class="nav-item"><a class="nav-link" href="${base}/charts"> <i
			class="fas fa-fw fa-chart-area"></i> <span>Charts</span>
	</a></li> --%>
	
	<li class="nav-item"><a class="nav-link" href="${base}/admin">
			<i class="fas fa-users"></i> <span>Thống kê</span>
	</a></li>
	
	<!-- Nav Item - Tables -->
	<li class="nav-item"><a class="nav-link" href="${base}/admin/saleorders">
			<i class="fas fa-dollar-sign"></i> <span>Đơn hàng</span>
	</a></li>
	
	<li class="nav-item"><a class="nav-link" href="${base}/admin/categories">
			<i class="fas fa-fw fa-table"></i> <span>Danh mục</span>
	</a></li>
	
	<!-- Nav Item - Tables -->
	<li class="nav-item"><a class="nav-link" href="${base}/admin/products">
			<i class="fab fa-product-hunt"></i> <span>Sản phẩm</span>
	</a></li>
	
	<li class="nav-item"><a class="nav-link" href="${base}/admin/blogs">
			<i class="fas fa-blog"></i> <span>Tin tức</span>
	</a></li>
	
	<li class="nav-item"><a class="nav-link" href="${base}/admin/contacts">
			<i class="fas fa-id-card-alt"></i> <span>Liên hệ</span>
	</a></li>
	
	<li class="nav-item"><a class="nav-link" href="${base}/admin/users">
			<i class="fas fa-users"></i> <span>Người dùng</span>
	</a></li>
	
	<li class="nav-item"><a class="nav-link" href="${base}/admin/shop/info">
			<i class="fas fa-users"></i> <span>Thông tin cửa hàng</span>
	</a></li>
	
	<!-- Divider -->
	<hr class="sidebar-divider d-none d-md-block">

	<!-- Nav Item - Pages Collapse Menu -->
	<%-- <li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapsePages"
		aria-expanded="true" aria-controls="collapsePages"> <i
			class="fas fa-fw fa-folder"></i> <span>Pages</span>
	</a>
		<div id="collapsePages" class="collapse"
			aria-labelledby="headingPages" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Login Screens:</h6>
				<a class="collapse-item" href="${base}/login">Login</a> <a
					class="collapse-item" href="${base}/register">Register</a> <a
					class="collapse-item" href="${base}/password/forgot">Forgot Password</a>
				<div class="collapse-divider"></div>
			</div>
		</div>
	</li> --%>
		
	
	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>

</ul>