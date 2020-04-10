/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savesforsales_test;

import com.example.demo.controllers.UsuarioController;
import com.example.demo.database.models.Usuario;
import com.example.demo.services.Services;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;


/**
 *
 * @author German le yo
 */
@RunWith(SpringRunner.class)
public class UsuarioControllerTest {
 
    private UsuarioController controller;
    
    @BeforeClass
    public static void setup() {
        Services.startServices();
    }
    
    @Test
    @Order(1)
    public void UserCreationTest(){
        Usuario tstUser = new Usuario();
        tstUser.setNombre("User creation Test Name");
        tstUser.setCorreo("UserCeation@Test.Email");        
        tstUser.setPassword("User creation Test password");
        
        UsuarioController controller = new UsuarioController();
        controller.init();
        //Assert.assertEquals(true, controller.crear(tstUser.getNombre(), tstUser.getCorreo(), tstUser.getPassword()).ok);
    }
}
