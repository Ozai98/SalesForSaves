
package savesforsales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import controllers.UsuarioController;
import services.Services;

/**
 *
 * @author German le yo
 */
@SpringBootApplication
public class Index {
    public static void main(String[] args){
       Services.startServices();
       SpringApplication.run(UsuarioController.class, args);
    }
}
