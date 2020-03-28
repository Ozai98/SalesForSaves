
package controllers;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
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

    private Dao<Usuario, Integer> userDao;

    @PostConstruct
    public void init() {
        userDao = DatabaseController.getInstance().userDao();
    }

	@GetMapping("/crear")
    public Response crear(@RequestParam(value = "nombre") String nombre, 
                          @RequestParam(value = "email") String email,
                          @RequestParam(value = "password") String password) 
    {
        // Verifing email
        if(!Services.validateEmail(email)) return new Response(false, null, "bad email");

        // Creating new user
        Usuario nuevo = new Usuario();
        nuevo.setNombre(nombre);
        nuevo.setCorreo(email);
        nuevo.setPassword(Services.cryptPassword(password));
        try{
            // Saving new user
            userDao.create(nuevo);
            return new Response(true, nuevo, "user created");
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