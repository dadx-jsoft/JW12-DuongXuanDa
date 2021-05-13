<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<title>Đơn hàng</title>

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
				<jsp:include
					page="${base}/WEB-INF/views/back-end/common/topbar_saleorder.jsp"></jsp:include>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Danh sách đơn
								hàng</h6>

						</div>
						<!-- <div class="card-body py-3">
							<a href="#" class="btn btn-primary a-btn-slide-text"> <strong>Thêm</strong>
								<i class="fas fa-plus-circle"></i>
							</a>
						</div> -->
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" width="100%" cellspacing="0">
									<thead>
										<tr>
											<th>#</th>
											<th>Tên khách hàng</th>
											<th>Địa chỉ</th>
											<th>Số điện thoại</th>
											<th>Email</th>
											<th>Thành tiền(VNĐ)</th>
											<th>Trạng thái đơn</th>
											<th></th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>#</th>
											<th>Tên khách hàng</th>
											<th>Địa chỉ</th>
											<th>Số điện thoại</th>
											<th>Email</th>
											<th>Thành tiền(VNĐ)</th>
											<th>Trạng thái đơn</th>
											<th></th>
										</tr>
									</tfoot>
									<tbody>
										<c:forEach items="${saleorders}" var="saleorder"
											varStatus="loop">
											<tr>
												<td>${loop.index+1}</td>
												<td>${saleorder.customerName}</td>
												<td>${saleorder.customerAddress}</td>
												<td>${saleorder.customerPhone}</td>
												<td>${saleorder.customerEmail}</td>
												<td>
													<label style="color:red;">
														<fmt:formatNumber type="number" pattern="###,###" value="${saleorder.total}"/>
													</label>
												</td>
												<td>
												<c:if test="${saleorder.orderStatus == 0}">
													<span id="approveSaleorder_${saleorder.id}">
													<button class="btn btn-secondary" onclick="approveSaleorder(${saleorder.id})">Duyệt</button>
													</span>
												</c:if>
												<c:if test="${saleorder.orderStatus == 1}">
													<span id="deliverySaleorder_${saleorder.id}">
													<button class="btn btn-primary" onclick="deliverySaleorder(${saleorder.id})">Giao hàng</button>
													</span>
												</c:if>
												<c:if test="${saleorder.orderStatus == 2}">
													<span id="completeSaleorder_${saleorder.id}">
													<button class="btn btn-success" onclick="completeSaleorder(${saleorder.id})">Hoàn thành</button>
													</span>
												</c:if>
												<c:if test="${saleorder.orderStatus == 3}">
													<span class="text-success"><i class="fas fa-clipboard-check"></i> Hoàn thành</span>
												</c:if>
												</td>
												
												<td style="width: 25%;"> <a href="${base}/admin/saleorders/detail/${saleorder.id}"
													class="btn btn-primary a-btn-slide-text"> <strong>Xem</strong>
														<i class="fas fa-eye"></i>
												</a> <a href="#" class="btn btn-danger a-btn-slide-text" data-toggle="modal" data-target="#deleteModal"> <strong>Xóa</strong>
														<i class="fas fa-trash-alt"></i>
												</a></td>
											</tr>
											<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
												aria-labelledby="exampleModalLabel" aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h5 class="modal-title" id="exampleModalLabel">Xác nhận xóa?</h5>
															<button class="close" type="button" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">X</span>
															</button>
														</div>
														<div class="modal-body">Chọn nút "xóa" để xóa mục này.</div>
														<div class="modal-footer">
															<button class="btn btn-secondary" type="button" data-dismiss="modal">Hủy</button>
															<a class="btn btn-primary" href="${base}/admin/saleorders/delete/${saleorder.id}">Xóa</a>
														</div>
													</div>
												</div>
											</div>
										</c:forEach>
									</tbody>
								</table>
								<!-- Paging -->
								<c:set var="req" value="${pageContext.request}" />
								<c:set var="baseURL"
									value="${fn:replace(req.requestURL, req.requestURI, '')}" />
								<c:set var="params"
									value="${requestScope['javax.servlet.forward.query_string']}" />
								<c:set var="requestPath"
									value="${requestScope['javax.servlet.forward.request_uri']}" />
								<c:set var="pageUrl"
									value="${ baseURL }${ requestPath }${ not empty params ? '?'+=params+='&':'' }" />
								<tag:paginate offset="${saleorderSearch.offset }"
									count="${saleorderSearch.count }" uri="${pageUrl}" />
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
	<jsp:include
		page="${base}/WEB-INF/views/back-end/common/back_to_top.jsp"></jsp:include>

	<!-- Logout Modal-->
	<jsp:include page="${base}/WEB-INF/views/back-end/common/logout.jsp"></jsp:include>

	<jsp:include page="${base}/WEB-INF/views/back-end/common/js.jsp"></jsp:include>

	<!-- Page level plugins -->
	<script src="${base}/vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="${base}/vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="${base}/js/demo/datatables-demo.js"></script>
	
	<script>
		function approveSaleorder(saleorderId){
			// javascript object.
			var data = {};
			data["id"] = saleorderId;
			var approveSaleorder = "#approveSaleorder_" + saleorderId;
			var htmlStr = '<button id="deliverySaleorder_' + saleorderId + '" class="btn btn-primary" onclick="deliverySaleorder('+saleorderId+')">Giao hàng</button>';
			$.ajax({
				url: "/admin/saleorders/approve",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(data),
				dataType: "json",
				success: function(jsonResult) {
					$(approveSaleorder).html(htmlStr);
					$(approveSaleorder).attr("id","deliverySaleorder_" + saleorderId);
				},
				error: function(jqXhr, textStatus, errorMessage) { // error callback 
				}
			});
		}
		
		function deliverySaleorder(saleorderId){
			// javascript object.
			var data = {};
			data["id"] = saleorderId;
			var deliverySaleorder = "#deliverySaleorder_" + saleorderId;
			var htmlStr = '<button id="completeSaleorder_' + saleorderId + '" class="btn btn-success" onclick="completeSaleorder('+saleorderId+')">Hoàn thành</button>';
			$.ajax({
				url: "/admin/saleorders/delivery",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(data),
				dataType: "json",
				success: function(jsonResult) {
					$(deliverySaleorder).html(htmlStr);
					$(deliverySaleorder).attr("id","deliverySaleorder_" + saleorderId);
				},
				error: function(jqXhr, textStatus, errorMessage) { // error callback 
				}
			});
		}
		
		function completeSaleorder(saleorderId){
			// javascript object.
			var data = {};
			data["id"] = saleorderId;
			var completeSaleorder = "#completeSaleorder_" + saleorderId;
			$.ajax({
				url: "/admin/saleorders/complete",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(data),
				dataType: "json",
				success: function(jsonResult) {
					$(completeSaleorder).removeClass("btn btn-success");
					$(completeSaleorder).addClass("text-danger");
					$(completeSaleorder).html('<i class="fas fa-clipboard-check"></i> Hoàn thành')
				},
				error: function(jqXhr, textStatus, errorMessage) { // error callback 
				}
			});
		}
	</script>

</body>

</html>