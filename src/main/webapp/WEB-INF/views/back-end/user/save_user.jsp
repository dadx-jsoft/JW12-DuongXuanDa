<!-- sử dụng tiếng việt -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- SPRING FORM -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!-- Custom fonts for this template-->
<jsp:include page="${base}/WEB-INF/views/common/variables.jsp"></jsp:include>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Lưu người dùng</title>

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
					<h1 class="h3 text-gray-800">Lưu user</h1>

					<!-- add product form -->
					<div class="panel-body mb-4">
						<a href="${base}/admin/users" class="btn btn-primary a-btn-slide-text"> <i
							class="fas fa-arrow-circle-left"></i> <strong>Trở lại</strong>
						</a>
					</div>
					<div class="panel-body">
						<sf:form class="form-horizontal" role="form" method="post" modelAttribute="userEdit"
							action="${base}/admin/users/save">
							<sf:hidden path="id"/>
							<div class="form-group">
								<label for="fullName" class="col-sm-3 control-label">Họ tên</label>
								<div class="col-sm-9">
									<sf:input path="fullName" type="text" cssClass="form-control"
										name="fullName" placeholder="Nhập họ tên user" required="required"/>
								</div>
							</div>
							<div class="form-group">
								<label for="username" class="col-sm-3 control-label">Username</label>
								<div class="col-sm-9">
									<sf:input path="username" type="text" cssClass="form-control"
										name="username" placeholder="Nhập username" required="required" />
								</div>
							</div>
							<div class="form-group">
								<label for="address" class="col-sm-3 control-label">Địa chỉ</label>
								<div class="col-sm-9">
									<sf:input path="address" type="text" cssClass="form-control"
										name="address" placeholder="Nhập địa chỉ" required="required" />
								</div>
							</div>
							<div class="form-group">
								<label for="phone" class="col-sm-3 control-label">Số điện thoại</label>
								<div class="col-sm-9">
									<sf:input path="phone" type="text" cssClass="form-control"
										name="phone" placeholder="Nhập số điện thoại" maxlength="10" required="required"/>
								</div>
							</div>
							<div class="form-group">
								<label for="email" class="col-sm-3 control-label">Email</label>
								<div class="col-sm-9">
									<sf:input path="email" type="text" cssClass="form-control"
										name="email" placeholder="Nhập email" required="required" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label text-danger">${saveError}</label>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-9">
									<button type="submit" class="btn btn-primary">Lưu <i class="fas fa-plus-circle"></i></button>
								</div>
							</div>
						</sf:form>

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
	<jsp:include page="${base}/WEB-INF/views/back-end/common/back_to_top.jsp"></jsp:include>

	<!-- Logout Modal-->
	<jsp:include page="${base}/WEB-INF/views/back-end/common/logout.jsp"></jsp:include>

	<jsp:include page="${base}/WEB-INF/views/back-end/common/js.jsp"></jsp:include>


	<!-- include summernote js-->
	<script src="${base}/summernote/summernote.js" type="text/javascript"></script>
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