<!-- sử dụng tiếng việt -->
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

<title>Products - Dashboard</title>

<!-- Custom fonts for this template-->
<jsp:include page="/WEB-INF/views/back-end/common/fonts.jsp"></jsp:include>

<!-- Custom styles for this template-->
<jsp:include page="/WEB-INF/views/back-end/common/css.jsp"></jsp:include>

<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<jsp:include page="/WEB-INF/views/back-end/common/sidebar.jsp"></jsp:include>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<jsp:include page="/WEB-INF/views/back-end/common/topbar.jsp"></jsp:include>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<!-- <h1 class="h3 mb-2 text-gray-800">Sản phẩm</h1> -->

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Danh sách sản
								phẩm</h6>
						</div>
						<div class="card-body py-3">
							<a href="${base}/admin/products/add"
								class="btn btn-primary a-btn-slide-text"> <strong>Add</strong>
								<i class="fas fa-plus-circle"></i>
							</a>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>Title</th>
											<th>Avatar</th>
											<th>Price</th>
											<th>Price Sale</th>
											<th>Category</th>
											<th>Action</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>Title</th>
											<th>Avatar</th>
											<th>Price</th>
											<th>Price Sale</th>
											<th>Category</th>
											<th>Action</th>
										</tr>
									</tfoot>
									<tbody>
										<c:forEach items="${productList}" var="product">
											<c:if test="${product.status == true}">
												<tr>
													<td>${product.title}</td>
													<td><img src="${base}/upload/${product.avatar}"
														width="100px"></td>
													<td>${product.price}</td>
													<td>${product.priceSale}</td>
													<td>${product.categories.id}</td>
													<td><a
														href="${base}/admin/products/edit/${product.id}"
														class="btn btn-secondary a-btn-slide-text"> <strong>Edit</strong>
															<i class="fas fa-edit"></i>
													</a> <a href="#" class="btn btn-primary a-btn-slide-text">
															<strong>View</strong> <i class="fas fa-eye"></i>
													</a> <a href="${base}/admin/products/delete/${product.id}"
														class="btn btn-danger a-btn-slide-text"
														onclick="return confirmDeleteItem();"
														data-bs-toggle="modal" data-bs-target="#exampleModal">
															<strong>Delete</strong> <i class="fas fa-trash-alt"></i>
													</a></td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>

								<!-- Modal -->
								<div class="modal fade" id="exampleModal" tabindex="-1"
									aria-labelledby="exampleModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">Modal
													title</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">...</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Close</button>
												<button type="button" class="btn btn-primary">Save
													changes</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<jsp:include page="/WEB-INF/views/back-end/common/footer.jsp"></jsp:include>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<jsp:include page="/WEB-INF/views/back-end/common/back_to_top.jsp"></jsp:include>

	<!-- Logout Modal-->
	<jsp:include page="/WEB-INF/views/back-end/common/logout.jsp"></jsp:include>

	<jsp:include page="/WEB-INF/views/back-end/common/js.jsp"></jsp:include>

	<!-- Page level plugins -->
	<script src="vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="js/demo/datatables-demo.js"></script>
</body>

</html>