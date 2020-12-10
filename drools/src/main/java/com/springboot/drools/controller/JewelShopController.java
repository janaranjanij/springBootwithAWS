package com.springboot.drools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.drools.model.Product;
import com.springboot.drools.service.JewelShopService;

@RestController
public class JewelShopController {
	
	private final JewelShopService jewelShopService;

	@Autowired
	public JewelShopController(JewelShopService jewelShopService) {
		this.jewelShopService = jewelShopService;
	}

	@RequestMapping(value = "/getDiscount", method = RequestMethod.GET, produces = "application/json")
	public Product getQuestions(@RequestParam(required = true) String type) {

		Product product = new Product();
		product.setType(type);
		jewelShopService.getProductDiscount(product);
		return product;
	}

}
