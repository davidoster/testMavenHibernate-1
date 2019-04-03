/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.controller;

import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springmvc.models.Product;
import springmvc.service.ProductService;

/**
 *
 * @author jnap
 */
@Controller
@RequestMapping("/")
public class MyController {

	@Autowired
	ProductService service;
	
	@Autowired
	MessageSource messageSource;

	/*
	 * List all existing Products.
	 */
        
        @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listProducts(ModelMap model) {

		List<Product> products = service.findAllProducts();
		model.addAttribute("products", products);
		return "allproducts";
	}
        
        /*
	 * Add a new Produc.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newProduct(ModelMap model) {
		Product product = new Product();
		model.addAttribute("product", product);
		model.addAttribute("edit", false);
		return "registration";
	}
        
        /*
	 * Handling POST request for validating the user input and saving Student in database.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveProduct(@Valid Product product, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}
		
//		if(!service.isProductCodeUnique(product.getId(), product.getName())){
//			FieldError codeError =new FieldError("Product","name",messageSource.getMessage("non.unique.name", new String[]{product.getName()}, Locale.getDefault()));
//		    result.addError(codeError);
//			return "registration";
//		}
		
		service.saveProduct(product);

		model.addAttribute("success", "Product " + product.getName() + " registered successfully");
		return "success";
	}
        
        /*
	 * Provide the existing Student for updating.
	 */
	@RequestMapping(value = { "/edit-{id}-product" }, method = RequestMethod.GET)
	public String editProduct(@PathVariable long id, ModelMap model) {
		Product product = service.findById(id);
		model.addAttribute("product", product);
		model.addAttribute("edit", true);
		return "registration";
	}
        
        /*
	 * Handling POST request for validating the user input and updating Student in database.
	 */
	@RequestMapping(value = { "/edit-{id}-product" }, method = RequestMethod.POST)
	public String saveOrUpdate(@Valid Product product, BindingResult result,
			ModelMap model, @PathVariable long id) {

		if (result.hasErrors()) {
			return "registration";
		}

//		if(!service.isProductCodeUnique(product.getId(), product.getName())){
//			FieldError idError =new FieldError("Product","id",messageSource.getMessage("non.unique.id", new String[]{product.getName()}, Locale.getDefault()));
//		    result.addError(idError);
//			return "registration";
//		}

		service.saveOrUpdate(product);

		model.addAttribute("success", "Product " + product.getName()	+ " updated successfully");
		return "success";
	}

	
	/*
	 * Delete a Product by it's id value.
	 */
	@RequestMapping(value = { "/delete-{id}-product" }, method = RequestMethod.GET)
	public String deleteProduct(@PathVariable long id) {
		service.deleteProductById(id);
		return "redirect:/list";
	}
}
