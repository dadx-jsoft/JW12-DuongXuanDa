package com.devpro.shopdoda.cotroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shopdoda.dto.ProductSearch;
import com.devpro.shopdoda.entities.Product;
import com.devpro.shopdoda.entities.ProductsImages;
import com.devpro.shopdoda.repositories.CategoriesRepo;
import com.devpro.shopdoda.repositories.ProductRepo;
import com.devpro.shopdoda.repositories.Products_ImagesRepo;
import com.devpro.shopdoda.services.ProductImagesService;
import com.devpro.shopdoda.services.ProductService;

@Controller
public class ProductController extends BaseController {
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoriesRepo categoriesRepo;

	@Autowired
	private ProductImagesService productImagesService;

	@RequestMapping(value = { "/products" }, method = RequestMethod.GET)
	public String products(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "front-end/products";
	}

	@RequestMapping(value = { "/product-detail/{seoPath}" }, method = RequestMethod.GET)
	public String product_detail(final ModelMap model, @PathVariable("seoPath") String seoPath,
			final HttpServletRequest request, final HttpServletResponse response) throws Exception {

		ProductSearch productSearch = new ProductSearch();
		productSearch.setSeo(seoPath);

		Product productDetail = productService.search(productSearch).get(0);

		model.addAttribute("pro_detail", productDetail);

		// find images
		List<ProductsImages> listImages = productImagesService.findByProduct(productDetail);
		if (listImages != null)
			model.addAttribute("listImages", listImages);
		
		return "front-end/product_detail";
	}

	@RequestMapping(value = { "/category/{categoriesSeo}" }, method = RequestMethod.GET)
	public String categories(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable("categoriesSeo") String categoriesSeo) throws Exception {

		ProductSearch productSearch = new ProductSearch();
		productSearch.buildPaging(request);

		productSearch.setCategorySeo(categoriesSeo); //
		List<Product> products = productService.search(productSearch);

		model.addAttribute("products", products);
		model.addAttribute("productSearch", productSearch);

		return "front-end/index";
	}

	@RequestMapping(value = { "/product/search-all" }, method = RequestMethod.GET)
	public String searchAll(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		ProductSearch productSearch = new ProductSearch();
		productSearch.buildPaging(request);

		productSearch.setSearchText(request.getParameter("searchText")); // name input
		List<Product> products = productService.search(productSearch);

		model.addAttribute("products", products);
		model.addAttribute("productSearch", productSearch);

		return "front-end/index";
	}

}
