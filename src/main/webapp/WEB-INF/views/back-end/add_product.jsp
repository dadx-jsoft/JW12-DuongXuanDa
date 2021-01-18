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

<title>SB Admin 2 - Dashboard</title>

<!-- Custom fonts for this template-->
<jsp:include page="/WEB-INF/views/back-end/common/fonts.jsp"></jsp:include>

<!-- Custom styles for this template-->
<jsp:include page="/WEB-INF/views/back-end/common/css.jsp"></jsp:include>
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
					<h1 class="h3 mb-4 text-gray-800">Thêm sản phẩm</h1>

					<!-- add product form -->

					<div class="panel-body">

						<sf:form class="form-horizontal" role="form" method="post"
							action="add_product" modelAttribute="product">

							<!-- <div class="form-group">
								<label for="sex" class="col-sm-3 control-label">Giới
									tính</label>
								<div class="col-sm-9">
									<label class="radio-inline"> <input type="radio"
										name="inlineRadioOptions" id="inlineRadio1" value="Nam"
										checked="checked"> Nam
									</label> <label class="radio-inline"> <input type="radio"
										name="inlineRadioOptions" id="inlineRadio2" value="Nữ">
										Nữ
									</label>
								</div>
							</div> -->
							<!-- form-group // -->

							<div class="form-group">
								<label for="name" class="col-sm-3 control-label">Tên sản
									phẩm</label>
								<div class="col-sm-9">
									<sf:input path="name" type="text" cssClass="form-control"
										name="name" id="name" placeholder="Nhập tên sản phẩm" />
								</div>
							</div>
							<!-- form-group // -->
							<div class="form-group">
								<label for="name" class="col-sm-3 control-label">Giá</label>
								<div class="col-sm-3">
									<sf:input path="price" type="number" cssClass="form-control"
										name="price" id="price" placeholder="Nhập giá" min = "0"/>
								</div>
							</div>
							<!-- form-group // -->

							<div class="form-group">
								<label for="quantity" class="col-sm-3 control-label">Số
									lượng</label>
								<div class="col-sm-3">
									<sf:input path="quantity" type="number" cssClass="form-control"
										name="quantity" id="quantity"
										placeholder="Nhập số lượng sản phẩm" min = "1" value = "1"/>
								</div>
							</div>
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
								<label for="name" class="col-sm-3 control-label">Ảnh sản
									phẩm</label>
								<div class="col-sm-3">
									<label class="control-label small" for="file_img">Hỗ
										trợ (jpg/png):</label>
									<sf:input path="image" type="file" name="file_img" />
								</div>
								<!-- <div class="col-sm-3">
									<label class="control-label small" for="file_img">File:</label>
									<input type="file" name="file_archive">
								</div> -->
							</div>
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
							<!-- form-group // -->

							<div class="form-group">
								<label for="about" class="col-sm-3 control-label">Mô tả</label>
								<div class="col-sm-9">
									<sf:textarea path="description" name="description"
										id="description" class="form-control" />
								</div>
							</div>
							<!-- form-group // -->
							<hr>
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-9">
									<button type="submit" class="btn btn-primary">Thêm</button>
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

</body>

</html>