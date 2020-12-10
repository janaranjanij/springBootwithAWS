package com.springboot.drools.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import com.springboot.drools.model.Product;

@Service
public class JewelShopService {

	private final KieContainer kieContainer;

	@Autowired
	public JewelShopService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}

	public Product getProductDiscount(Product product) {
		//get the stateful session
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.insert(product);
		kieSession.fireAllRules();
		kieSession.dispose();
		return product;
	}

}
