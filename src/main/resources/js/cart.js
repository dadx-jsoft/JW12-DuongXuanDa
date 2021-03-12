
function addToCart(productId, quantity) {
		// javascript object.
		var data = {};
		data["productId"] = productId;
		data["quantity"] = quantity;

		$.ajax({
			url : "/cart/add",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(data),

			dataType : "json",
			success : function(jsonResult) {
				$("#totalItemsInCart").html(jsonResult.data)
			},
			error : function(jqXhr, textStatus, errorMessage) { // error callback 

			}
		});
	}