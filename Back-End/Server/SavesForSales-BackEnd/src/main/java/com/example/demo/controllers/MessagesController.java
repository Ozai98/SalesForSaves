package com.example.demo.controllers;

import java.util.Date;
import java.util.List;

import com.example.demo.database.ClientRepository;
import com.example.demo.database.MessagesRepository;
import com.example.demo.database.RepositoryController;
import com.example.demo.database.models.Messages;
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
@RequestMapping("/messages")
public class MessagesController {
	private ClientRepository<User> userRepository;
	private ClientRepository<Provider> providerRepository;
	private MessagesRepository messagesRepository;

	public static Messages normalizeMessages(Messages message) throws Exception {
		RepositoryController.User().refresh(message.getUser());
		RepositoryController.Provider().refresh(message.getProvider());
		ClientController.normalize(message.getUser());
		ClientController.normalize(message.getProvider());
		return message;
	}

	public MessagesController() {
		userRepository = RepositoryController.User();
		providerRepository = RepositoryController.Provider();
		messagesRepository = RepositoryController.Messages();
	}

	@GetMapping(value = "/getAll/{id}/{idReciber}")
	public Response<Messages[]> getMessages(
		@PathVariable Integer id,
		@PathVariable Integer idReciber
	){
		try {
			List<Messages> listMessages = messagesRepository.searchByInvolved(id, idReciber);
			Messages[] getMessages = new Messages[listMessages.size()];
			int i = 0;
			for (Messages message : listMessages) {
				getMessages[i++] = normalizeMessages(message);
			}
			return new Response<>(true, getMessages, "lista de mensajes");
		} catch (Exception e) {
			return new Response<>(false, null, e);
		}
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Response<Messages> createMessages(
		Integer idUser,
		Integer idProvider,
		String content,
		Boolean isProviderBoolean
	){
		try {
			Messages message = new Messages();
			message.setUser(userRepository.getById(idUser));
			message.setProvider(providerRepository.getById(idProvider));
			message.setContent(content);
			message.setTimeSend(new Date());
			message.setSenderProvider(isProviderBoolean);
			messagesRepository.create(message);
			return new Response<Messages>(true, message, "el mensaje se creo");
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(false, null, "no se logro enviar el mensaje");
		}
	}
}