<!-- sử dụng tiếng việt -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING FORM -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!-- Custom fonts for this template-->
<jsp:include page="${base}/WEB-INF/views/common/variables.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Lưu sản phẩm</title>

<!-- Custom fonts for this template-->
<jsp:include page="${base}/WEB-INF/views/back-end/common/fonts.jsp"></jsp:include>

<!-- Custom styles for this template-->
<jsp:include page="${base}/WEB-INF/views/back-end/common/css.jsp"></jsp:include>

<!-- include summernote css-->
<link href="${base}/summernote/summernote.css" rel="stylesheet" />
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
					<h1 class="h3 text-gray-800">Chi tiết đơn hàng</h1>

					<div class="panel-body mb-4">
						<a href="${base}/admin/saleorders"
							class="btn btn-primary a-btn-slide-text"> <i
							class="fas fa-arrow-circle-left"></i> <strong>Trở lại</strong>
						</a>
					</div>
					<div class="panel-body">
						<%-- <c:if test="${not empty message}">
							<div class="alert alert-primary" role="alert">
								<c:out value="${message }"></c:out>
							</div>
						</c:if> --%>
						<form>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="customerName">Họ và tên</label> <input type="text"
										class="form-control" id="customerName"
										placeholder="${saleorder.customerName}" readonly="readonly">
								</div>
								<div class="form-group col-md-6">
									<label for="customerPhone">Số điện thoại</label> <input
										type="text" class="form-control" id="customerPhone"
										placeholder="${saleorder.customerPhone}" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail4">Email</label> <input type="email"
									class="form-control" id="inputEmail4"
									placeholder="${saleorder.customerEmail}" readonly="readonly">
							</div>
							<div class="form-group">
								<label for="inputAddress">Địa chỉ</label> <input type="text"
									class="form-control" id="inputAddress"
									placeholder="${saleorder.customerAddress}" readonly="readonly">
							</div>

							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">Danh sách
										sản phẩm</h6>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table class="table table-bordered" width="100%"
											cellspacing="0">
											<thead>
												<tr>
													<th>#</th>
													<th>Tên sản phẩm</th>
													<th>Giá</th>
													<th>Số lượng</th>
													<!-- <th>Action</th> -->
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th>#</th>
													<th>Tên sản phẩm</th>
													<th>Giá</th>
													<th>Số lượng</th>
													<!-- <th>Action</th> -->
												</tr>
											</tfoot>
											<tbody>
												<c:forEach items="${saleorderProducts}" var="sp"
													varStatus="loop">
													<tr>
														<td>${loop.index+1}</td>
														<td>${sp.product.title}</td>
														<td>${sp.priceUnit}VNĐ</td>
														<td>${sp.quantity}</td>
														<%-- <td style="width: 25%;"><a href="#"
															class="btn btn-secondary a-btn-slide-text"> <strong>Sửa</strong>
																<i class="fas fa-edit"></i>
														</a> <a href="${base}/admin/saleorders/detail/${saleorder.id}"
															class="btn btn-primary a-btn-slide-text"> <strong>View</strong>
																<i class="fas fa-eye"></i>
														</a> <a href="#" class="btn btn-danger a-btn-slide-text">
																<strong>Xóa</strong> <i class="fas fa-trash-alt"></i>
														</a></td> --%>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label for="total">Tổng tiền phải thanh toán:</label> <label
									style="color: red;"><fmt:formatNumber type="number"
										pattern="###,###VNĐ" value="${saleorder.total}" /></label>
							</div>

						</form>

					</div>
					<!-- panel-body // -->

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
	<jsp:include
		page="${base}/WEB-INF/views/back-end/common/back_to_top.jsp"></jsp:include>

	<!-- Logout Modal-->
	<jsp:include page="${base}/WEB-INF/views/back-end/common/logout.jsp"></jsp:include>

	<jsp:include page="${base}/WEB-INF/views/back-end/common/js.jsp"></jsp:include>


	<!-- include summernote js-->
	<script src="/summernote/summernote.js" type="text/javascript"></script>
	<script type="text/javascript">
		$('.summernote').summernote({
			height : 200, //set editable area's height
			codemirror : { // codemirror options
				theme : 'monokai'
			}
		});
	</script>
</body>

</html>