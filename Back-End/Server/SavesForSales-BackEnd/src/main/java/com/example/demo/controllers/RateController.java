package com.example.demo.controllers;

import com.example.demo.database.ClientRepository;
import com.example.demo.database.RateRepositoy;
import java.util.List;

import javax.annotation.PostConstruct;

import com.example.demo.database.Repository;
import com.example.demo.database.RepositoryController;
import com.example.demo.database.models.Provider;
import com.example.demo.database.models.Rate;
import com.example.demo.database.models.User;
import com.example.demo.services.Services;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/rate")
public class RateController {
	private RateRepositoy rateRepository;
	private ClientRepository<User> userRepository;
	private ClientRepository<Provider> providerRepository;

	@PostConstruct
	public void init() {
		setRateRepository(RepositoryController.Rate());
		setUserRepository(RepositoryController.User());
		setProviderRepository(RepositoryController.Provider());
	}

	private void setRateRepository(RateRepositoy rateRepository) {
		this.rateRepository = rateRepository;
	}

	private void setUserRepository(ClientRepository<User> userRepository) {
		this.userRepository = userRepository;
	}

	private void setProviderRepository(ClientRepository<Provider> providerRepository) {
		this.providerRepository = providerRepository;
	}

	@PostMapping(value = "/{rating}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Response<Rate> rate(int idProvider, int idUser, @PathVariable int rating) {
		try {
			User user = userRepository.getById(idUser);
			Provider provider = providerRepository.getById(idProvider);
			Rate rate = new Rate();
			rate.setUser(user);
			rate.setProvider(provider);
			rate.setRate(rating);
			rateRepository.create(rate);
			return new Response<Rate>(true, rate, "rated created in db");
		} catch (Exception e) {
			Services.handleError(e);
			return new Response<Rate>(false, null, "user not found");
		}
	}

	@PostMapping(value = "/rating/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Response<int[]> totalRate(int idProvider) {
		try {
			Provider provider = providerRepository.getById(idProvider);
			providerRepository.refresh(provider);
			ProviderController.normalize(provider);
			List<Rate> list = rateRepository.getByProvider(idProvider);
			int cuantity = list.size();
			int rating = 0;
			for(Rate rate : list){
				rating += rate.getRate();
			}
			int values[] = new int[2];
			if(cuantity!=0)values[1] = rating/cuantity;
			values[1] = cuantity;
			return new Response<int[]>(true, values , "total rating");
		} catch (Exception e) {
			Services.handleError(e);
			return new Response<int[]>(true, null, "provider not found");
		}
	}
}