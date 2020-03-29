
package controllers;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import database.DatabaseController;
import database.models.Usuario;
import services.Services;

/**
 *
 * @author German le yo
 */
@RestController
@EnableAutoConfiguration
public class UsuarioController {

    public static final String ROUTE = "usuario";

    private Dao<Usuario, Integer> userDao;

    @PostConstruct
    public void init() {
        userDao = DatabaseController.getInstance().userDao();
    }

	@PostMapping(value = ROUTE + "/crear", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Response crear(String nombre, String correo, String password)
    {

        // Verifing email
        if(!Services.validateEmail(correo)) return new Response(false, null, "bad email");


        // Creating new user
        Usuario newUser = new Usuario();
        newUser.setNombre(nombre);
        newUser.setCorreo(correo);
        newUser.setPassword(Services.cryptPassword(password));
        try{
            // Saving new user
            userDao.create(newUser);
            return new Response(true, newUser, "user created");
        }catch(SQLException ex){
            // Error saving
            Services.handleError(ex);
            return new Response(false, null, ex);
        }
    }    
}

class Response{
    public final boolean ok;
    public final Usuario usuario;
    public final String msg;

    public Response(boolean ok, Usuario usuario, String msg){
        this.ok = ok;
        this.usuario = usuario;
        this.msg = msg;
    }

    public Response(boolean ok, Usuario usuario, Exception ex){
        this.ok = ok;
        this.usuario = usuario;
        this.msg = ex.getCause().getMessage();
    }
}