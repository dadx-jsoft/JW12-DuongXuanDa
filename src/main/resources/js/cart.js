function addToCart(productId, quantity) {
	var numberProductId = '#numberProduct_' + productId;
	if($('#numberProduct').val() == 0){
		return;
	}else{
		if($(numberProductId).val() > 1){
			quantity = $(numberProductId).val();
		}
		// javascript object.
		var data = {};
		data["productId"] = productId;
		data["quantity"] = quantity;
		
		
		$.ajax({
			url: "/cart/add",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(data),
			dataType: "json",
			success: function(jsonResult) {
				// thông báo thành công
				swal("Thành công", "Đã thêm sản phẩm vào giỏ hàng !", "success");
				
				//$("#totalItemsInCart").html(jsonResult.data)
				// Một cách siêu củ chuối để update number của cart trên giao diện @@
				var base = '${base}'
				$("#totalItemsInCart").after(
					"<div id='totalItemsInCart1' class='icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti' data-notify='"+jsonResult.data+"'> <a href='${base}/cart/view'><i class='zmdi zmdi-shopping-cart'></i></a> </div>"
				)
				$("#totalItemsInCart").remove()
				$("#totalItemsInCart1").after(
					"<div id='totalItemsInCart' class='icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti' data-notify='"+jsonResult.data+"'> <a href='/cart/view'><i class='zmdi zmdi-shopping-cart'></i></a> </div>"
				)
				$("#totalItemsInCart1").remove()
				
				// Mobile 
				$("#totalItemsInCartMobile").after(
					"<div id='totalItemsInCartMobile1' class='icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti' data-notify='"+jsonResult.data+"'> <a href='${base}/cart/view'><i class='zmdi zmdi-shopping-cart'></i></a> </div>"
				)
				$("#totalItemsInCartMobile").remove()
				$("#totalItemsInCartMobile1").after(
					"<div id='totalItemsInCartMobile' class='icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti' data-notify='"+jsonResult.data+"'> <a href='/cart/view'><i class='zmdi zmdi-shopping-cart'></i></a> </div>"
				)
				$("#totalItemsInCartMobile1").remove()
			},
			error: function(jqXhr, textStatus, errorMessage) { // error callback 
			}
		});
	}	
};
