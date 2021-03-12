<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Users - Dashboard</title>

<!-- Custom fonts for this template-->
<jsp:include page="${base}/WEB-INF/views/back-end/common/fonts.jsp"></jsp:include>

<!-- Custom styles for this template-->
<jsp:include page="${base}/WEB-INF/views/back-end/common/css.jsp"></jsp:include>

<!-- Custom styles for this page -->
<link href="${base}/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<jsp:include page="${base}/WEB-INF/views/back-end/common/sidebar.jsp"></jsp:include>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<jsp:include page="${base}/WEB-INF/views/back-end/common/topbar.jsp"></jsp:include>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">Users</h1>
					<p class="mb-4">
						DataTables is a third party plugin that is used to generate the
						demo table below. For more information about DataTables, please
						visit the <a target="_blank" href="https://datatables.net">official
							DataTables documentation</a>.
					</p>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Danh sách users</h6>

						</div>
						<div class="card-body py-3">
							<a href="#" class="btn btn-primary a-btn-slide-text"> <strong>Add</strong>
								<i class="fas fa-plus-circle"></i>
							</a>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>User name</th>
											<th>email</th>
											<th>Action</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>User name</th>
											<th>email</th>
											<th>Action</th>
										</tr>
									</tfoot>
									<tbody>
										<tr>
											<td>Tiger Nixon</td>
											<td>System Architect</td>
											<td><a href="#"
												class="btn btn-secondary a-btn-slide-text"> <strong>Edit</strong>
													<i class="fas fa-edit"></i>
											</a> <a href="#" class="btn btn-primary a-btn-slide-text"> <strong>View</strong>
													<i class="fas fa-eye"></i>
											</a> <a href="#" class="btn btn-danger a-btn-slide-text"> <strong>Delete</strong>
													<i class="fas fa-trash-alt"></i>
											</a></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<jsp:include page="${base}/WEB-INF/views/back-end/common/footer.jsp"></jsp:include>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<jsp:include page="${base}/WEB-INF/views/back-end/common/back_to_top.jsp"></jsp:include>

	<!-- Logout Modal-->
	<jsp:include page="${base}/WEB-INF/views/back-end/common/logout.jsp"></jsp:include>

	<jsp:include page="${base}/WEB-INF/views/back-end/common/js.jsp"></jsp:include>

	<!-- Page level plugins -->
	<script src="${base}/vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="${base}/vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="${base}/js/demo/datatables-demo.js"></script>

</body>

</html>