<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Liên hệ</title>
<!-- Variables -->
<jsp:include page="${base}/WEB-INF/views/common/variables.jsp"></jsp:include>
<meta charset="UTF-8">
<jsp:include page="${base}/WEB-INF/views/front-end/common/css.jsp"></jsp:include>

<!-- SPRING FORM -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

</head>
<body class="animsition">

	<jsp:include page="${base}/WEB-INF/views/front-end/common/header.jsp"></jsp:include>

	<!-- Title page -->
	<section class="bg-img1 txt-center p-lr-15 p-tb-92"
		style="background-image: url('images/bg-01.jpg');">
		<h2 class="cl0 txt-center">Liên hệ</h2>
	</section>


	<!-- Content page -->
	<section class="bg0 p-t-104 p-b-116">
		<div class="container">
			<div class="flex-w flex-tr">
				<div
					class="size-210 bor10 p-lr-70 p-t-55 p-b-70 p-lr-15-lg w-full-md">

					<!-- <form method="post" action="contact1">
						<h4 class="mtext-105 cl2 txt-center p-b-30">Send Us A Message
						</h4>

						<div class="bor8 m-b-20 how-pos4-parent">
							<input class="stext-111 cl2 plh3 size-116 p-l-62 p-r-30"
								type="text" name="email" placeholder="Your Email Address">

							<img class="how-pos4 pointer-none"
								src="${base}/images/icons/icon-email.png" alt="ICON">
						</div>

						<div class="bor8 m-b-30">
							<textarea class="stext-111 cl2 plh3 size-120 p-lr-28 p-tb-25"
								name="msg" placeholder="How Can We Help?"></textarea>
						</div>

						<button
							class="flex-c-m stext-101 cl0 size-121 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer">
							Submit</button>
					</form> -->

					<!-- Cách 2 -->
					<%-- <sf:form method="post" action="${base}/contact2" modelAttribute="contact">
						<h4 class="mtext-105 cl2 txt-center p-b-30">Send Us A Message
						</h4>

						<div class="bor8 m-b-20 how-pos4-parent">
							<sf:input path="email"
								cssClass="stext-111 cl2 plh3 size-116 p-l-62 p-r-30" type="text"
								name = "email" placeholder="Your Email Address" />

							<img class="how-pos4 pointer-none"
								src="${base}/images/icons/icon-email.png" alt="ICON">
						</div>

						<div class="bor8 m-b-30">
							<sf:textarea path="msg"
								cssClass="stext-111 cl2 plh3 size-120 p-lr-28 p-tb-25" name="msg"
								placeholder="How Can We Help?" />
						</div>

						<button
							class="flex-c-m stext-101 cl0 size-121 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer">
							Submit</button>
					</sf:form> --%>

					<!-- Cách 3 -->
					<form>
						<h4 class="mtext-105 cl2 txt-center p-b-30">Liên hệ với chúng tôi
						</h4>

						<div class="bor8 m-b-20 how-pos4-parent">
							<input class="cl2 plh3 size-116 p-l-62 p-r-30"
								type = "text" id="email" placeholder="Địa chỉ email">

							<img class="how-pos4 pointer-none"
								src="${base}/images/icons/icon-email.png" alt="ICON">
						</div>
						
						<div class="bor8 m-b-20 how-pos4-parent">
							<input class="cl2 plh3 size-116 p-l-62 p-r-30"
								type="text" id="fName" placeholder="Họ tên">
							<img class="how-pos4 pointer-none"
								src="${base}/images/icons/icon-heart-01.png" alt="ICON">
						</div>

						<div class="bor8 m-b-30">
							<textarea class="cl2 plh3 size-120 p-lr-28 p-tb-25"
								id="msg" placeholder="Chúng tôi có thể giúp gì cho bạn?"></textarea>
						</div>

						<button type="button" onclick="SaveContact()"
							class="flex-c-m cl0 size-121 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer">
							Gửi</button>
					</form>
				</div>
				<div
					class="size-210 bor10 flex-w flex-col-m p-lr-93 p-tb-30 p-lr-15-lg w-full-md">
					<div class="flex-w w-full p-b-42">
						<span class="fs-18 cl5 txt-center size-211"> <span
							class="lnr lnr-map-marker"></span>
						</span>

						<div class="size-212 p-t-2">
							<span class="mtext-110 cl2"> Địa chỉ </span>

							<p class="cl6 size-213 p-t-18">${shopInfo.address}</p>
						</div>
					</div>

					<div class="flex-w w-full p-b-42">
						<span class="fs-18 cl5 txt-center size-211"> <span
							class="lnr lnr-phone-handset"></span>
						</span>

						<div class="size-212 p-t-2">
							<span class="mtext-110 cl2"> Hotline </span>

							<p class="cl1 size-213 p-t-18">${shopInfo.phone}</p>
						</div>
					</div>

					<div class="flex-w w-full">
						<span class="fs-18 cl5 txt-center size-211"> <span
							class="lnr lnr-envelope"></span>
						</span>

						<div class="size-212 p-t-2">
							<span class="mtext-110 cl2"> Email hỗ trợ </span>

							<p class="cl1 size-213 p-t-18">${shopInfo.email}
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>


	<!-- Map -->
	<div class="map">
		<div class="size-303" id="google_map" data-map-x="40.691446"
			data-map-y="-73.886787" data-pin="${base}/images/icons/pin.png"
			data-scrollwhell="0" data-draggable="1" data-zoom="11"></div>
	</div>



	<!-- Footer -->
	<jsp:include page="${base}/WEB-INF/views/front-end/common/footer.jsp"></jsp:include>


	<!-- Back to top -->
	<jsp:include page="${base}/WEB-INF/views/front-end/common/back_to_top.jsp"></jsp:include>

	<jsp:include page="${base}/WEB-INF/views/front-end/common/js.jsp"></jsp:include>

	<!--===============================================================================================-->
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAKFWBqlKAGCeS1rMVoaNlwyayu0e0YRes"></script>
	<script src="js/map-custom.js"></script>
	<!--===============================================================================================-->
	<script src="${base}/vendor/sweetalert/sweetalert.min.js"></script>
	
	<script>
		function SaveContact() {
			var email = $("#email").val();
			if(email == ""){
				alert("Email không được để trống!")
				return;
			}
			if(validateEmail(email)){
				alert("Email không đúng định dạng!")
				return;
			}
			if($("#fName").val() == ""){
				alert("Họ tên không được để trống!")
				return;
			}
			if($("#msg").val() == ""){
				alert("Nội dung không được để trống!")
				return;
			}
			// javascript object.
			var data = {};
			data["email"] = $("#email").val();
			data["fullName"] = $("#fName").val();
			data["msg"] = $("#msg").val();

			$("#email").val('');
			$("#fName").val('');
			$("#msg").val();
			
			$.ajax({
				url : "/contact3",
				type : "post",
				contentType : "application/json",
				data : JSON.stringify(data),

				dataType : "json",
				success : function(jsonResult) {
					if (jsonResult.statusCode == 200) {
						swal("Thành công!!", "Chúng tôi sẽ liên hệ với bạn trong thời gian sớm nhất", "success");
					} else {
						alert(jsonResult.data);
					}
				},
				error : function(jqXhr, textStatus, errorMessage) { // error callback 

				}
			});
		}
	</script>
</body>
</html>