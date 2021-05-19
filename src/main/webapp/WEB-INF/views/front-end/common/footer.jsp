<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<footer class="bg3 p-t-75 p-b-32">
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-lg-3 p-b-50">
				<h4 class="stext-301 cl0 p-b-30">Danh mục</h4>
				<ul>
					<li class="p-b-10"><a href="${base}/category/day-da-dong-ho"
						class="cl7 hov-cl1 trans-04"> Dây da đồng hồ </a></li>
					<li class="p-b-10"><a href="${base}/category/vi-da"
						class="cl7 hov-cl1 trans-04">Ví da </a></li>
					<li class="p-b-10"><a href="${base}/category/tui-deo-cheo-da"
						class="cl7 hov-cl1 trans-04">Túi đeo chéo da </a></li>
				</ul>
			</div>

			<div class="col-sm-6 col-lg-3 p-b-50">
				<h4 class="stext-301 cl0 p-b-30">Trợ giúp</h4>

				<ul>
					<li class="p-b-10"><a href="#"
						class="stext-107 cl7 hov-cl1 trans-04"> Vận chuyển </a></li>

					<li class="p-b-10"><a href="#"
						class="stext-107 cl7 hov-cl1 trans-04"> Câu hỏi </a></li>
				</ul>
			</div>

			<div class="col-sm-6 col-lg-3 p-b-50">
				<h4 class="stext-301 cl0 p-b-30">Tin tức</h4>
				<a class="cl7 size-201" href="${base}/blog">Tin tức</a>
			</div>

			<div class="col-sm-6 col-lg-3 p-b-50">
				<h4 class="stext-301 cl0 p-b-30">Nhận ưu đãi</h4>

				<form>
					<div class="wrap-input1 w-full p-b-4">
						<input class="input1 bg-none plh1 stext-107 cl7" type="text"
							id="emailSubscribe" placeholder="email@example.com">
						<div class="focus-input1 trans-04"></div>
					</div>

					<div class="p-t-18">
						<button type="button" onclick="SaveSubscribe();"
							class="flex-c-m cl0 size-103 bg1 bor1 hov-btn2 p-lr-15 trans-04">
							Gửi</button>
					</div>
				</form>
			</div>
		</div>

		<div class="p-t-40">
			<div class="flex-c-m flex-w p-b-18">
				<a href="#" class="m-all-1"> <img
					src="/images/icons/icon-pay-01.png" alt="ICON-PAY">
				</a> <a href="#" class="m-all-1"> <img
					src="/images/icons/icon-pay-02.png" alt="ICON-PAY">
				</a> <a href="#" class="m-all-1"> <img
					src="/images/icons/icon-pay-03.png" alt="ICON-PAY">
				</a> <a href="#" class="m-all-1"> <img
					src="/images/icons/icon-pay-04.png" alt="ICON-PAY">
				</a> <a href="#" class="m-all-1"> <img
					src="/images/icons/icon-pay-05.png" alt="ICON-PAY">
				</a>
			</div>

			<p class="stext-107 cl6 txt-center">
				<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
				Copyright &copy;
				<script>
					document.write(new Date().getFullYear());
				</script>
				All rights reserved | This template is made with <i
					class="fa fa-heart-o" aria-hidden="true"></i> by <a
					href="https://colorlib.com" target="_blank">Colorlib</a>
				<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
			</p>
		</div>
	</div>
</footer>

<script>
	function SaveSubscribe() {
		// javascript object.
		var data = {};
		data["emailSubscribe"] = $("#emailSubscribe").val();

		$.ajax({
			url : "/subscribe",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(data),

			dataType : "json",
			success : function(jsonResult) {
				if (jsonResult.statusCode == 200) {
					alert(jsonResult.data);
				} else {
					alert(jsonResult.data);
				}
			},
			error : function(jqXhr, textStatus, errorMessage) { // error callback 

			}
		});
	}
</script>