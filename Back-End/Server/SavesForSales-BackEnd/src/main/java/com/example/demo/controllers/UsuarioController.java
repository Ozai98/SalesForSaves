
package com.example.demo.controllers;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.models.Usuario;
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
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController extends ClienteController<Usuario> {

	private Repository<Usuario> usuarioRepository;

	@PostConstruct
	public void init() {
		setUsuarioRepository(Repository.Usuario());
	}

	public void setUsuarioRepository(Repository<Usuario> repository) {
		this.usuarioRepository = repository;
	}

	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Response<Usuario> crear(String nombre, String correo, String password, String avatar) {
		return super.crear(nombre, correo, password, avatar, new Usuario());
	}

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Response<Usuario> login(String correo, String password) {
		return super.login(correo, password);
	}

	/**
	 * @PostMapping(value = "/get-by-email", consumes =
	 *                    MediaType.APPLICATION_FORM_URLENCODED_VALUE) public
	 *                    Response<Usuario> getByCorreo(String correo) { try{
	 *                    List<Usuario> users = usuarioRepository.search(correo);
	 *                    if(users.isEmpty()) return new Response<Usuario>(false,
	 *                    null, "User no found"); return new Response<Usuario>(true,
	 *                    Services.normalize(users.get(0)), "Ok"); }catch(Exception
	 *                    ex){ Services.handleError(ex); return new
	 *                    Response<Usuario>(false, null, ex); } }
	 */

	/**
	 * @GetMapping("/get-by-id/{id}") public Response<Usuario> getById(@PathVariable
	 * int id) { try{ Usuario user = usuarioRepository.getById(id); if(user == null)
	 * return new Response<Usuario>(false, null, "User no Found"); return new
	 * Response<Usuario>(true, Services.normalize(user), "Ok"); }catch(Exception
	 * ex){ Services.handleError(ex); return new Response<Usuario>(false, null, ex);
	 * } }
	 */

	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Response<Usuario> updateUser(Integer id, String nombre, String password, String avatar) {
		Usuario user;
		try {
			user = usuarioRepository.getById(id);
			return super.update(nombre, password, avatar, user);
		} catch (Exception ex) {
			Services.handleError(ex);
			return new Response<Usuario>(false, null, ex);
		}
		
	}
}

