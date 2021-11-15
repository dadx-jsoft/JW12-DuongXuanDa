package com.eoptech.shopdoda.cotroller.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.eoptech.shopdoda.dto.search.ProductSearch;
import com.eoptech.shopdoda.entities.Product;
import com.eoptech.shopdoda.entities.Shop;
import com.eoptech.shopdoda.repositories.ShopRepo;
import com.eoptech.shopdoda.services.ProductService;

@Controller
public class HomeController extends BaseController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ShopRepo shopRepo;

    /**
     * @param model    - Dùng để đẩy dữ liệu hoặc hứng dữ liệu từ tầng VIEW.
     * @param request  - Các thông tin người dùng yêu cầu.
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping(value = { "/", "/home", "/trangchu" })
    public String index(final ModelMap model, final HttpServletRequest request) {

//		Categories parent = categoriesRepo.getOne(16);
//		System.out.println("parents: "+parent.getName());
//		for (Categories child : parent.getChilds()) {
//			System.out.println("child: " + child.getName());
//		}
//		model.addAttribute("categories", categoriesRepo.findAll());
        model.addAttribute("menu", buildMenu());

        ProductSearch productSearch = new ProductSearch();
        productSearch.buildPaging(request);

        List<Product> products = productService.search(productSearch);

        // Thông tin cửa hàng
        Shop shopInfo = (Shop) shopRepo.findAll().get(0);

        model.addAttribute("products", products);
        model.addAttribute("productSearch", productSearch);
        model.addAttribute("bestSellingProducts", productService.getBestSellingProducts());
        model.addAttribute("shopInfo", shopInfo);
        
        return "front-end/index";
    }

}
