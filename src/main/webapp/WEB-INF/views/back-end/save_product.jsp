<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- sử dụng tiếng việt -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- SPRING FORM -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Add products - Dashboard</title>

<!-- Custom fonts for this template-->
<jsp:include page="/WEB-INF/views/back-end/common/fonts.jsp"></jsp:include>

<!-- Custom styles for this template-->
<jsp:include page="/WEB-INF/views/back-end/common/css.jsp"></jsp:include>

<!-- include summernote css-->
<link href="/summernote/summernote.css" rel="stylesheet" />
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
					<h1 class="h3 text-gray-800">Thêm sản phẩm</h1>

					<!-- add product form -->
					<div class="panel-body mb-4">
						<a href="${base}/admin/products" class="btn btn-primary a-btn-slide-text"> <i
							class="fas fa-arrow-circle-left"></i> <strong>Back</strong>
						</a>
					</div>
					<div class="panel-body">
						<c:if test="${not empty message}">
							<div class="alert alert-primary" role="alert">
								<c:out value="${message }"></c:out>
							</div>
						</c:if>
						<sf:form class="form-horizontal" role="form" method="post"
							action="${base}/admin/add-product" modelAttribute="product">

							<!-- form-group // -->

							<!-- <div class="form-group">
								<label for="tech" class="col-sm-3 control-label">Options</label>
								<div class="col-sm-3">
									<select class="form-control">
										<option value="">Vui lòng chọn</option>
										<option value="texnolog2">option 2</option>
										<option value="texnolog3">option 3</option>
									</select>
								</div>
							</div> -->
							<div class="form-group">
								<label for="category" class="col-sm-3 control-label">Thể
									loại</label>
								<sf:select path="categories.id" class="col-sm-3" id="category">
									<sf:options items="${categories}" itemValue="id"
										itemLabel="name" />
								</sf:select>
							</div>
							<!-- form-group // -->

							<div class="form-group">
								<label for="name" class="col-sm-3 control-label">Tên sản
									phẩm</label>
								<div class="col-sm-9">
									<sf:input path="title" type="text" cssClass="form-control"
										name="name" id="name" placeholder="Nhập tên sản phẩm" />
								</div>
							</div>
							<!-- form-group // -->
							<div class="form-group">
								<label for="price" class="col-sm-3 control-label">Giá</label>
								<div class="col-sm-3">
									<sf:input path="price" type="number" cssClass="form-control"
										name="price" id="price" placeholder="Nhập giá" min="0" />
								</div>
							</div>

							<div class="form-group">
								<label for="priceSale" class="col-sm-3 control-label">Giá
									khuyến mãi</label>
								<div class="col-sm-3">
									<sf:input path="priceSale" type="number"
										cssClass="form-control" name="priceSale" id="priceSale"
										placeholder="Nhập giá khuyến mãi" min="0" />
								</div>
							</div>

							<div class="form-group">
								<label for="seo" class="col-sm-3 control-label">Seo</label>
								<div class="col-sm-3">
									<sf:input path="seo" type="text" cssClass="form-control"
										name="seo" id="seo" placeholder="Nhập seo" min="0" />
								</div>
							</div>

							<!-- form-group // -->

							<%-- <div class="form-group">
								<label for="quantity" class="col-sm-3 control-label">Số
									lượng</label>
								<div class="col-sm-3">
									<sf:input path="quantity" type="number" cssClass="form-control"
										name="quantity" id="quantity"
										placeholder="Nhập số lượng sản phẩm" min = "1" value = "1"/>
								</div>
							</div> --%>
							<!-- form-group // -->

							<!-- <div class="form-group">
								<label class="col-sm-3 control-label">Thời gian</label>
								<div class="col-sm-3">
									<label class="control-label small" for="date_start">Ngày
										bắt đầu: </label> <input type="text" class="form-control"
										name="date_start" id="date_start" placeholder="Начало">
								</div>
								<div class="col-sm-3">
									<label class="control-label small" for="date_finish">Ngày
										kết thúc:</label> <input type="text" class="form-control"
										name="date_finish" id="date_finish" placeholder="Конец">
								</div>
							</div> -->
							<!-- form-group // -->
							<div class="form-group">
								<label for="avatar" class="col-sm-3 control-label">Ảnh
									sản phẩm</label>
								<div class="col-sm-3">
									<label class="control-label small" for="file_img">Hỗ
										trợ (jpg/png):</label>
									<sf:input path="avatar" type="file" name="avatar" />
								</div>
								<!-- <div class="col-sm-3">
									<label class="control-label small" for="file_img">File:</label>
									<input type="file" name="file_archive">
								</div> -->
							</div>

							<div class="form-group">
								<label for="shortDescription" class="col-sm-3 control-label">Mô
									tả ngắn</label>
								<div class="col-sm-9">
									<sf:textarea path="shortDescription" name="shortDescription"
										id="shortDescription" class="form-control summernote" />
								</div>
							</div>

							<div class="form-group">
								<label for="detailDescription" class="col-sm-3 control-label">Mô
									tả</label>
								<div class="col-sm-9">
									<sf:textarea path="detailDescription" name="detailDescription"
										id="detailDescription" class="form-control summernote" />
								</div>

							</div>
							<!-- form-group // -->
							<hr>
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-9">
									<button type="submit" class="btn btn-primary">Thêm <i class="fas fa-plus-circle"></i></button>
								</div>
							</div>
							<!-- form-group // -->
						</sf:form>

					</div>
					<!-- panel-body // -->

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