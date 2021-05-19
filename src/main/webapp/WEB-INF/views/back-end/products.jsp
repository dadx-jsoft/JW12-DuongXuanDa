<!-- sử dụng tiếng việt -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<title>Sản phẩm</title>

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
				<jsp:include page="${base}/WEB-INF/views/back-end/common/topbar_product.jsp"></jsp:include>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<!-- <h1 class="h3 mb-2 text-gray-800">Sản phẩm</h1> -->
					
					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary"><a href="${base}/admin/products">Danh sách sản
								phẩm</a></h6>
						</div>
						<div class="card-body py-3">
							<a href="${base}/admin/products/add"
								class="btn btn-primary a-btn-slide-text"> <strong>Thêm</strong>
								<i class="fas fa-plus-circle"></i>
							</a>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>#</th>
											<th>Tiêu đề</th>
											<th>Avatar</th>
											<th>Giá (VNĐ)</th>
											<th>Giá Sale (VNĐ)</th>
											<th>Danh mục</th>
											<th></th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>#</th>
											<th>Tiêu đề</th>
											<th>Avatar</th>
											<th>Giá (VNĐ)</th>
											<th>Giá Sale (VNĐ)</th>
											<th>Danh mục</th>
											<th></th>
										</tr>
									</tfoot>
									<tbody>
										<c:forEach items="${productList}" var="product" varStatus="loop">
											<c:if test="${product.status == true}">
												<tr>
													<td><span>${loop.index+1}</span></td>
													<td>${product.title}</td>
													<td><img src="${base}/upload/${product.avatar}"
														width="80px"></td>
													<td>
														<fmt:formatNumber type="number" pattern="###,###" value="${product.price}" />
													</td>
													<td>
														<fmt:formatNumber type="number" pattern="###,###" value="${product.priceSale}" />
													</td>
													<td>${product.categories.name}</td>
													<td>
													<a
														href="${base}/admin/products/edit/${product.id}"
														class="btn btn-secondary a-btn-slide-text"> <strong>Sửa</strong>
															<i class="fas fa-edit"></i>
													</a> 
													<a href="${base}/admin/reviews/${product.id}" class="btn btn-primary a-btn-slide-text">
															<strong>Đánh giá</strong> <i class="fas fa-comment"></i>
													</a> 
													<a href="#" data-toggle="modal"
														class="btn btn-danger a-btn-slide-text" data-target="#deleteModal">
															<strong>Xóa</strong> <i class="fas fa-trash-alt"></i>
													</a>
													</td>
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
																<a class="btn btn-primary" href="${base}/admin/products/delete/${product.id}">Xóa</a>
															</div>
														</div>
													</div>
												</div>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
								
								<!-- Paging -->
								<c:set var="req" value="${pageContext.request}" />
								<c:set var="baseURL" value="${fn:replace(req.requestURL, req.requestURI, '')}" />
								<c:set var="params" value="${requestScope['javax.servlet.forward.query_string']}" />
								<c:set var="requestPath" value="${requestScope['javax.servlet.forward.request_uri']}" />
								<c:set var="pageUrl" value="${ baseURL }${ requestPath }${ not empty params ? '?'+=params+='&':'' }" />
								<tag:paginate offset="${productSearch.offset }"
									count="${productSearch.count }" uri="${pageUrl}" />
								<!-- End Paging -->
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
		</div>
		<!-- End of Page Wrapper -->

		<!-- Scroll to Top Button-->
		<jsp:include
			page="${base}/WEB-INF/views/back-end/common/back_to_top.jsp"></jsp:include>

		<!-- Logout Modal-->
		<jsp:include page="${base}/WEB-INF/views/back-end/common/logout.jsp"></jsp:include>

		<jsp:include page="${base}/WEB-INF/views/back-end/common/js.jsp"></jsp:include>
		
</body>

</html>