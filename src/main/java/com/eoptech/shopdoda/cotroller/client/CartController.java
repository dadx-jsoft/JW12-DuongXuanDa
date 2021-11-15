package com.eoptech.shopdoda.cotroller.client;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.eoptech.shopdoda.dto.AjaxResponse;
import com.eoptech.shopdoda.dto.cart.Cart;
import com.eoptech.shopdoda.dto.cart.CartItem;
import com.eoptech.shopdoda.entities.Product;
import com.eoptech.shopdoda.entities.Saleorder;
import com.eoptech.shopdoda.entities.SaleorderProduct;
import com.eoptech.shopdoda.entities.User;
import com.eoptech.shopdoda.repositories.ProductRepo;
import com.eoptech.shopdoda.repositories.SaleorderRepo;
import com.eoptech.shopdoda.services.MailService;
import com.eoptech.shopdoda.services.SaleorderService;

@Controller
public class CartController extends BaseController {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    SaleorderRepo saleOrderRepo;

    @Autowired
    private SaleorderService saleorderService;

    @Autowired
    private MailService mailService;

    private int getTotalItems(final HttpServletRequest request) {
        HttpSession httpSession = request.getSession();

        if (httpSession.getAttribute("cart") == null) {
            return 0;
        }

        Cart cart = (Cart) httpSession.getAttribute("cart");
        List<CartItem> cartItems = cart.getCartItems();

        return cartItems.size();
    }

//	private Double getTotalPrice(HttpServletRequest request) {
//		HttpSession httpSession = request.getSession();
//
//		if (httpSession.getAttribute("cart") == null) {
//			return 0d;
//		}
//
//		Cart cart = (Cart) httpSession.getAttribute("cart");
//		List<CartItem> cartItems = cart.getCartItems();
//
//		Double totalPrice = 0d;
//		for (CartItem item : cartItems) {
//			totalPrice += item.getPriceUnit().doubleValue() * item.getQuantity();
////			totalPrice.add(item.getPriceUnit().multiply(BigDecimal.valueOf(item.getQuantity())));
//		}
//
//		return totalPrice;
//	}

    private void resetCart(final HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("cart", new Cart());
        httpSession.setAttribute("totalItems", 0);
        httpSession.setAttribute("totalPrice", Double.valueOf(0));
    }

    @PostMapping(value = { "/cart/payment" })
    public String cartPayment(final ModelMap model, final HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Cart cart = (Cart) httpSession.getAttribute("cart");
        List<CartItem> cartItems = cart.getCartItems();
        System.out.println("cart size: " + cartItems.size());

        String errorMessage = null;
        if (cartItems.size() == 0) {
            errorMessage = "Không có sản phẩm nào trong giỏ hàng";
        }

        int userId = -1;
        String customerName = null;
        String customerAddress = null;
        String customerPhone = null;
        String customerEmail = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) { // user đã đăng nhập -> lưu thông tin user vào saleorder
            User u = (User) principal;
            userId = u.getId();
            customerName = u.getFullName();
            customerAddress = u.getAddress();
            customerPhone = u.getPhone();
            customerEmail = u.getEmail();
        } else {
            customerName = request.getParameter("customerName");
            customerAddress = request.getParameter("customerAddress");
            customerPhone = request.getParameter("customerPhone");
            customerEmail = request.getParameter("customerEmail");
        }

        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            return "front-end/shopping_cart";
        }

        Saleorder saleOrder = new Saleorder();
        saleOrder.setCode("ORDER-" + System.currentTimeMillis());
        saleOrder.setSeo("ORDER-" + System.currentTimeMillis());
        if (userId != -1) {
            saleOrder.setUserId(userId);
        }
        saleOrder.setCustomerName(customerName);
        saleOrder.setCustomerAddress(customerAddress);
        saleOrder.setCustomerPhone(customerPhone);
        saleOrder.setCustomerEmail(customerEmail);
        saleOrder.setTotal(new BigDecimal(0));
        saleOrder.setOrderStatus(0);

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

        try {
            mailService.sendMailAfterOrderSuccess(customerEmail, saleOrder);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        model.addAttribute("orderSuccess", "Chúc mừng bạn đã đặt hàng thành công, vui lòng kiểm tra email!");
        return "front-end/order_success";
    }

    @GetMapping(value = { "/cart/view" })
    public String cartView(final ModelMap model, final HttpServletRequest request) {
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

    @GetMapping(value = { "/order/history" })
    public String orderHistoryView(final ModelMap model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) { // user đã đăng nhập. Hiển thị lịch sử đơn hàng
            User u = (User) principal;
            List<Saleorder> saleorders = saleorderService.findSaleordersByUserId(u.getId());
            model.addAttribute("orderHistory", saleorders);
        }

        return "front-end/order_history";
    }

    @PostMapping(value = { "/cart/add" })
    public ResponseEntity<AjaxResponse> addToCart(final ModelMap model, final HttpServletRequest request,
            @RequestBody CartItem cartItem) {
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

    @PostMapping(value = { "/cart/update" })
    public ResponseEntity<AjaxResponse> updateCart(final ModelMap model, final HttpServletRequest request,
            @RequestBody CartItem cartItem) {
        HttpSession httpSession = request.getSession();

        Cart cart = (Cart) httpSession.getAttribute("cart");

        List<CartItem> cartItems = cart.getCartItems();
        Double totalPrice = 0d;
        for (CartItem item : cartItems) {
            if (item.getProductId() == cartItem.getProductId()) { // trùng id trong giỏ hàng
                if (cartItem.getQuantity() == 0)
                    item.setQuantity(1);
                else
                    item.setQuantity(cartItem.getQuantity());
            }
            totalPrice += item.getPriceUnit().doubleValue() * item.getQuantity();
        }

        httpSession.setAttribute("totalItems", getTotalItems(request));
        httpSession.setAttribute("totalPrice", totalPrice);
        String[] data = { String.valueOf(getTotalItems(request)), totalPrice.toString() };
        return ResponseEntity.ok(new AjaxResponse(200, data));
    }

    @PostMapping(value = { "/cart/delete" })
    public ResponseEntity<AjaxResponse> deleteItemInCart(final ModelMap model, final HttpServletRequest request,
            @RequestBody CartItem cartItem) {
        HttpSession httpSession = request.getSession();

        Cart cart = (Cart) httpSession.getAttribute("cart");

        List<CartItem> cartItems = cart.getCartItems();
        Double totalPrice = 0d;

        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getProductId() == cartItem.getProductId()) {
                cartItems.remove(i);
                break;
            }
        }
        for (CartItem item : cartItems) {
            totalPrice += item.getPriceUnit().doubleValue() * item.getQuantity();
        }

        httpSession.setAttribute("totalItems", getTotalItems(request));
        httpSession.setAttribute("totalPrice", totalPrice);
        String[] data = { String.valueOf(getTotalItems(request)), totalPrice.toString() };
        return ResponseEntity.ok(new AjaxResponse(200, data));
    }

}
