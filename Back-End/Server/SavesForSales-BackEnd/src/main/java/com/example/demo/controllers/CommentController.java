package com.example.demo.controllers;

import java.util.List;

import com.example.demo.database.ClientRepository;
import com.example.demo.database.CommentsRepository;
import com.example.demo.database.RepositoryController;
import com.example.demo.database.models.Comments;
import com.example.demo.database.models.Provider;
import com.example.demo.database.models.User;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/Comment")
public class CommentController {

	private ClientRepository<User> userRepository;
	private ClientRepository<Provider> providerRepository;
	private CommentsRepository commentRepository;

	public CommentController() {
		userRepository = RepositoryController.User();
		providerRepository = RepositoryController.Provider();
		commentRepository = RepositoryController.Comment();
	}

	private Comments normalize(Comments comment) {
		try {
			userRepository.refresh(comment.getUser());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comment.setProvider(ProviderController.normalize(comment.getProvider()));
		comment.setUser(UserController.normalize(comment.getUser()));
		return comment;
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Response<Comments> create(int idUser, int idProvider, String comment) {
		Comments comments = new Comments();
		try {
			comments.setUser(userRepository.getById(idUser));
			comments.setProvider(providerRepository.getById(idProvider));
			comments.setComment(comment);
			commentRepository.create(comments);
			return new Response<Comments>(true, normalize(comments), "creado commentario");

		} catch (Exception e) {
			return new Response<>(false, null, "asd");
		}
	}

	@GetMapping("/search/{id}")
	public Response<Comments[]> searchByProvider(@PathVariable Integer id) {
		List<Comments> comments;
		try {
			comments = commentRepository.searchByProvider(id);
			Comments[] respoComments = new Comments[comments.size()];
			int i = 0;
			for(Comments comments2: comments) respoComments[i++] = normalize(comments2);
		
			return new Response<>(true, respoComments, "comentarios");
		} catch (Exception e) {
			return new Response<>(false, null, e);
		}
		
	}
}