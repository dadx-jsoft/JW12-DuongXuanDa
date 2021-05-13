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

<title>Lưu vai trò</title>

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
					<h1 class="h3 text-gray-800">Thêm vai trò</h1>

					<!-- add product form -->
					<div class="panel-body mb-4">
						<a href="${base}/admin/categories" class="btn btn-primary a-btn-slide-text"> <i
							class="fas fa-arrow-circle-left"></i> <strong>Back</strong>
						</a>
					</div>
					<div class="panel-body">
						<sf:form class="form-horizontal" role="form" method="post" modelAttribute="roleEdit"
							action="${base}/admin/roles/save">
							<sf:hidden path="id"/>
							<div class="form-group">
								<label for="name" class="col-sm-3 control-label">Tên vai trò</label>
								<div class="col-sm-9">
									<sf:input path="name" type="text" cssClass="form-control"
										name="name" placeholder="Nhập tên vai trò" />
								</div>
							</div>
							
							<div class="form-group">
								<label for="description" class="col-sm-3 control-label">Mô
									tả</label>
								<div class="col-sm-9">
									<sf:textarea path="description" name="description"
										id="description" class="form-control summernote" />
								</div>
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