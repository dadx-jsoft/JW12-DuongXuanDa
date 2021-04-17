package com.devpro.shopdoda.cotroller.client;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shopdoda.dto.AjaxResponse;
import com.devpro.shopdoda.dto.cart.Cart;
import com.devpro.shopdoda.dto.cart.CartItem;
import com.devpro.shopdoda.entities.Product;
import com.devpro.shopdoda.entities.Saleorder;
import com.devpro.shopdoda.entities.SaleorderProduct;
import com.devpro.shopdoda.repositories.ProductRepo;
import com.devpro.shopdoda.repositories.SaleorderRepo;
import com.devpro.shopdoda.services.MailService;

@Controller
public class CartController extends BaseController {

	@Autowired
	ProductRepo productRepo;
	@Autowired
	SaleorderRepo saleOrderRepo;
	@Autowired
	private MailService mailService;

	private int getTotalItems(final HttpServletRequest request) {
		HttpSession httpSession = request.getSession();

		if (httpSession.getAttribute("cart") == null) {
			return 0;
		}

		Cart cart = (Cart) httpSession.getAttribute("cart");
		List<CartItem> cartItems = cart.getCartItems();

		int total = 0;
		for (CartItem item : cartItems) {
			total += item.getQuantity();
		}

		return total;
	}
	
	private Double getTotalPrice(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();

		if (httpSession.getAttribute("cart") == null) {
			return 0d;
		}

		Cart cart = (Cart) httpSession.getAttribute("cart");
		List<CartItem> cartItems = cart.getCartItems();

		Double totalPrice = 0d;
		for (CartItem item : cartItems) {
			totalPrice+= item.getPriceUnit().doubleValue() * item.getQuantity();
//			totalPrice.add(item.getPriceUnit().multiply(BigDecimal.valueOf(item.getQuantity())));
		}

		return totalPrice;
	}

	private void resetCart(final HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("cart", new Cart());
		httpSession.setAttribute("totalItems", getTotalItems(request));
	}

	@RequestMapping(value = { "/cart/payment" }, method = RequestMethod.POST)
	public String cartPayment(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession();
		Cart cart = (Cart) httpSession.getAttribute("cart");
		List<CartItem> cartItems = cart.getCartItems();
		System.out.println("cart size: " + cartItems.size());

		String errorMessage = null;
		if (cartItems.size() == 0) {
			errorMessage = "Không có sản phẩm nào trong giỏ hàng";
		}

		String customerName = request.getParameter("customerName");
		String customerAddress = request.getParameter("customerAddress");
		String customerPhone = request.getParameter("customerPhone");
		String customerEmail = request.getParameter("customerEmail");

		if (errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
			return "front-end/shopping_cart";
		}

		Saleorder saleOrder = new Saleorder();
		saleOrder.setCode("ORDER-" + System.currentTimeMillis());
		saleOrder.setSeo("ORDER-" + System.currentTimeMillis());
		saleOrder.setCustomerName(customerName);
		saleOrder.setCustomerAddress(customerAddress);
		saleOrder.setCustomerPhone(customerPhone);
		saleOrder.setCustomerEmail(customerEmail);
		saleOrder.setTotal(new BigDecimal(0));

		BigDecimal totalPrice = BigDecimal.ZERO;

		for (CartItem item : cartItems) {
			Date date = new Date();
			SaleorderProduct saleOrderProducts = new SaleorderProduct();
			saleOrderProducts.setCreatedDate(date);
			saleOrderProducts.setProduct(productRepo.getOne(item.getProductId()));
			saleOrderProducts.setQuantity(item.getQuantity());
			saleOrderProducts.setPriceUnit(item.getPriceUnit());
			saleOrder.addSaleorderProduct(saleOrderProducts);
			saleOrder.setCreatedDate(date);
			totalPrice = totalPrice.add(item.getPriceUnit().multiply(BigDecimal.valueOf(item.getQuantity())));
		}

		saleOrder.setTotal(totalPrice);

		saleOrderRepo.save(saleOrder);

		this.resetCart(request);

		mailService.sendEmail(customerEmail);

		return "redirect:/products";
	}

	@RequestMapping(value = { "/cart/view" }, method = RequestMethod.GET)
	public String cartView(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		HttpSession httpSession = request.getSession();

		Cart cart = null;
		if (httpSession.getAttribute("cart") != null) {
			cart = (Cart) httpSession.getAttribute("cart");
		} else {
			cart = new Cart();
			httpSession.setAttribute("cart", cart);
		}
		List<CartItem> cartItems = cart.getCartItems();

		model.addAttribute("cartItems", cartItems);

		return "front-end/shopping_cart";
	}

	@RequestMapping(value = { "/cart/add" }, method = RequestMethod.POST)
	public ResponseEntity<AjaxResponse> addToCart(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @RequestBody CartItem cartItem) {
		HttpSession httpSession = request.getSession();

		Cart cart = null;
		if (httpSession.getAttribute("cart") != null) { // có cart
			cart = (Cart) httpSession.getAttribute("cart");
		} else { // chưa có cart
			cart = new Cart();
			httpSession.setAttribute("cart", cart);
		}
		
		List<CartItem> cartItems = cart.getCartItems();
		boolean isExists = false;
		Double totalPrice = 0d;
		for (CartItem item : cartItems) {
			if (item.getProductId() == cartItem.getProductId()) { // trùng id trong giỏ hàng
				isExists = true;
				item.setQuantity(item.getQuantity() + cartItem.getQuantity());
			}
			totalPrice += item.getPriceUnit().doubleValue() * item.getQuantity();
		}

		if (!isExists) {
			Product productInDb = productRepo.getOne(cartItem.getProductId());
			cartItem.setProductName(productInDb.getTitle());
			cartItem.setProductAvatar(productInDb.getAvatar());
			cartItem.setPriceUnit(productInDb.getPrice());
			cartItem.setProductSeo(productInDb.getSeo());
			cart.getCartItems().add(cartItem);
			
			totalPrice += cartItem.getPriceUnit().doubleValue() * cartItem.getQuantity();
		}

		httpSession.setAttribute("totalItems", getTotalItems(request));
		httpSession.setAttribute("totalPrice", totalPrice);
		return ResponseEntity.ok(new AjaxResponse(200, getTotalItems(request)));
	}

	@RequestMapping(value = { "/cart/update" }, method = RequestMethod.POST)
	public ResponseEntity<AjaxResponse> updateCart(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @RequestBody CartItem cartItem) {
		HttpSession httpSession = request.getSession();

		Cart cart = (Cart) httpSession.getAttribute("cart");

		List<CartItem> cartItems = cart.getCartItems();
		Double totalPrice = 0d;
		for (CartItem item : cartItems) {
			if (item.getProductId() == cartItem.getProductId()) { // trùng id trong giỏ hàng
				item.setQuantity(cartItem.getQuantity());
			}
			totalPrice += item.getPriceUnit().doubleValue() * item.getQuantity();
		}
		
		httpSession.setAttribute("totalItems", getTotalItems(request));
		httpSession.setAttribute("totalPrice", totalPrice);
		String[] data = {String.valueOf(getTotalItems(request)), totalPrice.toString()};
		return ResponseEntity.ok(new AjaxResponse(200, data));
	}
	
}
