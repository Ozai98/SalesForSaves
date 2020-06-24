package com.example.demo.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.models.Provider;
import com.example.demo.database.models.Ubication;
import com.example.demo.database.Repository;
import com.example.demo.database.RepositoryController;

import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.services.Services;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author German le yo
 */
@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/provider")
public class ProviderController extends ClientController<Provider> {

	private Repository<Provider> providerRepository;
	private Repository<Ubication> ubicationRepository;

	public ProviderController() {
		super(RepositoryController.Provider());
		providerRepository = RepositoryController.Provider();
		ubicationRepository = RepositoryController.Ubication();
	}

	@PostMapping(value = "/create")
	public Response<Provider> create(String name, String mail, String password, MultipartFile avatar, double lat, double longitud) {
		try {
			Ubication ubication = new Ubication();
			ubication.setLat(lat);
			ubication.setLongitud(longitud);
			ubicationRepository.create(ubication);
			Provider provider = new Provider();
			provider.setUbication(ubication);
		} catch (Exception e) {
			//TODO: handle exception
		}
		return super.create(name, mail, password, avatar, new Provider());
	}

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Response<Provider> login(String mail, String password) {
		return super.login(mail, password);
	}

	@GetMapping("/get-by-id/{id}")
	public Response<Provider> getById(@PathVariable Integer id) {
		return super.getById(id);
	}

	@PostMapping(value = "/update")
	public Response<Provider> updateProvider(Integer id, String name, String password, MultipartFile avatar, double lat, double longitud) {
		if(id == null) return new Response<>(false, null, "Missing ID");
		try {
			Provider provider = providerRepository.getById(id);
			Ubication ubication = new Ubication();
			ubication.setLat(lat);
			ubication.setLongitud(longitud);
			ubicationRepository.create(ubication);
			provider.setUbication(ubication);
			return super.update(name, password, avatar, provider);
		} catch (Exception ex) {
			Services.handleError(ex);
			return new Response<>(false, null, ex);
		}
	}

}
