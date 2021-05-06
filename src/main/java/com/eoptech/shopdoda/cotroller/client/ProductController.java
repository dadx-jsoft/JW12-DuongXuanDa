package com.eoptech.shopdoda.cotroller.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eoptech.shopdoda.dto.search.ProductSearch;
import com.eoptech.shopdoda.entities.Product;
import com.eoptech.shopdoda.entities.ProductsImages;
import com.eoptech.shopdoda.services.ProductImagesService;
import com.eoptech.shopdoda.services.ProductService;
import com.eoptech.shopdoda.services.ReviewService;

@Controller
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductImagesService productImagesService;

	@Autowired
	private ReviewService reviewService;

	@RequestMapping(value = { "/products" }, method = RequestMethod.GET)
	public String products(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		model.addAttribute("menu", buildMenu());

		ProductSearch productSearch = new ProductSearch();
		productSearch.buildPaging(request);

		List<Product> products = productService.search(productSearch);

		model.addAttribute("products", products);
		model.addAttribute("productSearch", productSearch);
		
		return "front-end/products";
	}
	
	@RequestMapping(value = { "/product-detail/{seoPath}" }, method = RequestMethod.GET)
	public String productDetail(final ModelMap model, @PathVariable("seoPath") String seoPath,
			final HttpServletRequest request, final HttpServletResponse response) throws Exception {

		ProductSearch productSearch = new ProductSearch();
		productSearch.setSeo(seoPath);

		Product productDetail = productService.search(productSearch).get(0);

		model.addAttribute("pro_detail", productDetail);

		// find images
		List<ProductsImages> listImages = productImagesService.findByProduct(productDetail);
		if (listImages != null)
			model.addAttribute("listImages", listImages);

		// related products
		productSearch = new ProductSearch();
		productSearch.setCategorySeo(productDetail.getCategories().getSeo());
		List<Product> relatedProducts = productService.search(productSearch);
		relatedProducts.remove(productDetail); // bỏ product hiện tại khỏi ds liên quan đến nó
		model.addAttribute("relatedProducts", relatedProducts);
		model.addAttribute("comments", reviewService.findByProduct(productDetail));
		return "front-end/product_detail";
	}

	@RequestMapping(value = { "/category/{categoriesSeo}" }, method = RequestMethod.GET)
	public String showProductByCategory(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable("categoriesSeo") String categoriesSeo) throws Exception {

		model.addAttribute("menu", buildMenu());
		
		ProductSearch productSearch = new ProductSearch();
		productSearch.buildPaging(request);

		productSearch.setCategorySeo(categoriesSeo); //
		List<Product> products = productService.search(productSearch);

		model.addAttribute("products", products);
		model.addAttribute("productSearch", productSearch);

		return "front-end/products";
	}

	@RequestMapping(value = { "/product/search-all" }, method = RequestMethod.GET)
	public String searchAll(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("menu", buildMenu());
		
		ProductSearch productSearch = new ProductSearch();
		productSearch.buildPaging(request);

		productSearch.setSearchText(request.getParameter("searchText")); // name input
		List<Product> products = productService.search(productSearch);

		model.addAttribute("products", products);
		model.addAttribute("productSearch", productSearch);

		return "front-end/products";
	}

	
}
