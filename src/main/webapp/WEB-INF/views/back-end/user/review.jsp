<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- Paging -->
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/pagingTagLibs.tld"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Đánh giá sản phẩm</title>

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
					<div class="panel-body mb-4">
						<a href="${base}/admin/products" class="btn btn-primary a-btn-slide-text"> <i
							class="fas fa-arrow-circle-left"></i> <strong>Trở lại</strong>
						</a>
					</div>
					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Danh sách review sản phẩm</h6>

						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>Product</th>
											<th>Full name</th>
											<th>Message</th>
											<th>Action</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>Product</th>
											<th>Full name</th>
											<th>Message</th>
											<th>Action</th>
										</tr>
									</tfoot>
									<tbody>
										<c:forEach items="${reviews}" var="review">
											<tr>
												<td>${review.product.title}</td>
												<td>${review.user.fullName}</td>
												<td>${review.message}</td>
												<td class="w-25">
												<c:if test="${review.status == false }">
													<button id="unapprovedReview_${review.id}" onclick="approveReview(${review.id})"
														class="btn btn-secondary a-btn-slide-text">Appr
															<i class="fas fa-window-close"></i>
													</button> 
												</c:if>
												<c:if test="${review.status == true }">
													<button
														class="btn btn-success a-btn-slide-text">Appr
															<i class="fas fa-check-square"></i>
													</button> 
												</c:if>
												
												<a href="#" class="btn btn-primary a-btn-slide-text"> <strong>View</strong>
														<i class="fas fa-eye"></i>
												</a> <a href="#" class="btn btn-danger a-btn-slide-text"> <strong>Xóa</strong>
														<i class="fas fa-trash-alt"></i>
												</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<!-- Paging -->
								<c:set var="req" value="${pageContext.request}" />
								<c:set var="baseURL" value="${fn:replace(req.requestURL, req.requestURI, '')}" />
								<c:set var="params" value="${requestScope['javax.servlet.forward.query_string']}" />
								<c:set var="requestPath" value="${requestScope['javax.servlet.forward.request_uri']}" />
								<c:set var="pageUrl" value="${ baseURL }${ requestPath }${ not empty params ? '?'+=params+='&':'' }" />
								<tag:paginate offset="${reviewSearch.offset }"
									count="${reviewSearch.count }" uri="${pageUrl}" />
								<!-- End Paging -->
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
	
	<script>
		function approveReview(reviewId){
			// javascript object.
			var data = {};
			data["id"] = reviewId;
			var unapprovedReviewVar = "#unapprovedReview_" + reviewId;
			$.ajax({
				url: "/admin/reviews/approve",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(data),
				dataType: "json",
				success: function(jsonResult) {
					$(unapprovedReviewVar).removeClass("btn-secondary");
					$(unapprovedReviewVar).addClass("btn-success");
					$(unapprovedReviewVar).html('Appr<i class="fas fa-check-square"></i>');
				},
				error: function(jqXhr, textStatus, errorMessage) { // error callback 
				}
			});
		}
	</script>
</body>

</html>