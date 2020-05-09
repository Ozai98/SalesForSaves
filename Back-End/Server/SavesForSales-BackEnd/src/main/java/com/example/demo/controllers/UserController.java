
package com.example.demo.controllers;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.models.User;
import com.example.demo.database.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.Services;
import org.springframework.web.bind.annotation.CrossOrigin;
/**
 *
 * @author German le yo
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
@CrossOrigin
public class UserController extends ClientController<User> {

	private Repository<User> userRepository;

	@PostConstruct
	public void init() {
		setUsuarioRepository(Repository.Usuario());
	}

	public void setUsuarioRepository(Repository<User> repository) {
		this.userRepository = repository;
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Response<User> create(String name, String mail, String password, String avatar) {
		return super.create(name, mail, password, avatar, new User());
	}

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Response<User> login(String mail, String password) {
		return super.login(mail, password);
	}

	/**
	 * @PostMapping(value = "/get-by-email", consumes =
	 *                    MediaType.APPLICATION_FORM_URLENCODED_VALUE) public
	 *                    Response<Usuario> getByCorreo(String mail) { try{
	 *                    List<Usuario> users = userRepository.search(mail);
	 *                    if(users.isEmpty()) return new Response<Usuario>(false,
	 *                    null, "User no found"); return new Response<Usuario>(true,
	 *                    Services.normalize(users.get(0)), "Ok"); }catch(Exception
	 *                    ex){ Services.handleError(ex); return new
	 *                    Response<Usuario>(false, null, ex); } }
	 */

	/**
	 * @GetMapping("/get-by-id/{id}") public Response<Usuario> getById(@PathVariable
	 * int id) { try{ Usuario user = userRepository.getById(id); if(user == null)
	 * return new Response<Usuario>(false, null, "User no Found"); return new
	 * Response<Usuario>(true, Services.normalize(user), "Ok"); }catch(Exception
	 * ex){ Services.handleError(ex); return new Response<Usuario>(false, null, ex);
	 * } }
	 */

	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Response<User> updateUser(Integer id, String name, String password, String avatar) {
		User user;
		try {
			user = userRepository.getById(id);
			return super.update(name, password, avatar, user);
		} catch (Exception ex) {
			Services.handleError(ex);
			return new Response<User>(false, null, ex);
		}
		
	}
}

