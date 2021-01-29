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
			<i class="fas fa-laugh-wink"></i>
		</div>
		<div class="sidebar-brand-text mx-3">
			Dashboard
		</div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Heading -->
	<div class="sidebar-heading">Interface</div>

	<!-- Nav Item - Pages Collapse Menu -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true"
		aria-controls="collapseTwo"> <i class="fas fa-fw fa-cog"></i> <span>Components</span>
	</a>
		<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
			data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Custom Components:</h6>
				<a class="collapse-item" href="buttons.html">Buttons</a> <a
					class="collapse-item" href="cards.html">Cards</a>
			</div>
		</div></li>

	<!-- Nav Item - Utilities Collapse Menu -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
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
		</div></li>

	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Heading -->
	<div class="sidebar-heading">Addons</div>

	<!-- Nav Item - Pages Collapse Menu -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
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
					class="collapse-item" href="${base}/forgot_password">Forgot Password</a>
				<div class="collapse-divider"></div>
				<h6 class="collapse-header">Other Pages:</h6>
				<a class="collapse-item" href="404.html">404 Page</a> <a
					class="collapse-item" href="${base}/admin/blank">Blank Page</a>
			</div>
		</div></li>

	<!-- Nav Item - Charts -->
	<li class="nav-item"><a class="nav-link" href="${base}/charts"> <i
			class="fas fa-fw fa-chart-area"></i> <span>Charts</span>
	</a></li>

	<!-- Nav Item - Tables -->
	<li class="nav-item"><a class="nav-link" href="${base}/admin/contacts">
			<i class="fas fa-fw fa-table"></i> <span>Liên hệ</span>
	</a></li>

	<!-- Nav Item - Add product -->
	<li class="nav-item"><a class="nav-link" href="${base}/admin/categories">
			<i class="fas fa-fw fa-table"></i> <span>Danh mục</span>
	</a></li>
	
	<!-- Nav Item - Tables -->
	<li class="nav-item"><a class="nav-link" href="${base}/admin/products">
			<i class="fas fa-fw fa-table"></i> <span>Sản phẩm</span>
	</a></li>

	<!-- Nav Item - Tables -->
	<li class="nav-item"><a class="nav-link"
		href="${base}/admin/products_images"> <i class="fas fa-fw fa-table"></i> <span>Ảnh
				sản phẩm</span>
	</a></li>

	<!-- Nav Item - Add product -->
	<li class="nav-item"><a class="nav-link" href="${base}/admin/add-product"> <i
			class="fas fa-fw fa-table"></i> <span>Thêm sản phẩm</span>
	</a></li>

	<!-- Nav Item - Tables -->
	<li class="nav-item"><a class="nav-link" href="${base}/admin/roles">
			<i class="fas fa-fw fa-table"></i> <span>Vai trò</span>
	</a></li>
	
	<!-- Nav Item - Tables -->
	<li class="nav-item"><a class="nav-link" href="${base}/admin/users">
			<i class="fas fa-fw fa-table"></i> <span>Users</span>
	</a></li>
	
	<!-- Nav Item - Tables -->
	<li class="nav-item"><a class="nav-link" href="${base}/admin/users_roles">
			<i class="fas fa-fw fa-table"></i> <span>Phân quyền</span>
	</a></li>
	
	<!-- Nav Item - Tables -->
	<li class="nav-item"><a class="nav-link" href="${base}/admin/saleorders">
			<i class="fas fa-fw fa-table"></i> <span>Đơn hàng</span>
	</a></li>
	
	<!-- Nav Item - Tables -->
	<li class="nav-item"><a class="nav-link" href="${base}/admin/saleorders_products">
			<i class="fas fa-fw fa-table"></i> <span>Sản phẩm của đơn hàng</span>
	</a></li>


	<!-- Divider -->
	<hr class="sidebar-divider d-none d-md-block">

	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>


</ul>