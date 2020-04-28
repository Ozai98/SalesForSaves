/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savesforsales_test;

import com.example.demo.controllers.Response;
import com.example.demo.controllers.UsuarioController;
import com.example.demo.database.models.Usuario;
import com.example.demo.services.Services;
import mock_repositories.MockUsuarioRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


/**
 *
 * @author German le yo
 */
@RunWith(SpringRunner.class)
public class UsuarioControllerTest {
 
    private UsuarioController controller;
    private Usuario defaultUser;
    @BeforeClass
    public static void setup() {
        Services.startServices();
        
    }

    @Before
    public void before() throws Exception{
        MockUsuarioRepository repo = new MockUsuarioRepository();
        controller = new UsuarioController();
        controller.setUsuarioRepository(repo);
        defaultUser = new Usuario();
        defaultUser.setNombre("defaultUser Name");
        defaultUser.setCorreo("defaultUser@Test.Email");        
        defaultUser.setPassword("defaultUser password");
        defaultUser.setAvatar("defaultUser avatar");
        Response<Usuario> res = controller.crear(defaultUser.getNombre(), defaultUser.getCorreo(), defaultUser.getPassword(), defaultUser.getAvatar());
        if(!res.ok) Assert.fail("Fail default user creation");
        defaultUser.setId(res.clase.getId());
    }
    
    @Test
    public void UserCreationTest(){
        Usuario tstUser = new Usuario();
        tstUser.setNombre("User creation Test Name");
        tstUser.setCorreo("UserCeation@Test.Email");        
        tstUser.setPassword("User creation Test password");
        tstUser.setAvatar("User creation Test avatar");
        
        Response res = controller.crear(tstUser.getNombre(), tstUser.getCorreo(), tstUser.getPassword(), tstUser.getAvatar());
        
        Assert.assertEquals(res.msg, true, res.ok);
    }
    
    @Test
    public void LoginTest(){
        Response<Usuario> res = controller.login(defaultUser.getCorreo(), defaultUser.getPassword());
        
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.clase.getNombre(), defaultUser.getNombre());
        Assert.assertEquals(res.clase.getAvatar(), defaultUser.getAvatar());
        Assert.assertEquals(res.clase.getId(), defaultUser.getId());    
        
        res = controller.login(defaultUser.getCorreo(), "Bad Password");
        Assert.assertFalse(res.msg, res.ok);
        
        res = controller.login("Bad Email", defaultUser.getPassword());
        Assert.assertFalse(res.msg, res.ok);
    }
    
    @Test
    public void GetByEmailTest(){
        Response<Usuario> res = controller.getByCorreo(defaultUser.getCorreo());
        
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.clase.getNombre(), defaultUser.getNombre());
        Assert.assertEquals(res.clase.getAvatar(), defaultUser.getAvatar());
        Assert.assertEquals(res.clase.getId(), defaultUser.getId());  
        
        res = controller.getByCorreo("Bad Email");
        
        Assert.assertFalse(res.ok);
    }
    
    @Test
    public void GetByIdTest(){
        Response<Usuario> res = controller.getById(defaultUser.getId());
        
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.clase.getNombre(), defaultUser.getNombre());
        Assert.assertEquals(res.clase.getAvatar(), defaultUser.getAvatar());
        Assert.assertEquals(res.clase.getId(), defaultUser.getId());  
        
        res = controller.getById(defaultUser.getId() - 100);
        
        Assert.assertFalse(res.msg, res.ok);
    }
    
    @Test
    public void UpdateUserTest(){
        
        defaultUser.setNombre("defaultUser updated Name");
        Response<Usuario> res = controller.updateUser(defaultUser.getId(), defaultUser.getNombre(), null, null);
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.clase.getNombre(), defaultUser.getNombre());
        
        defaultUser.setPassword("defaultUser updated password");
        res = controller.updateUser(defaultUser.getId(), null, defaultUser.getPassword(), null);
        Assert.assertTrue(res.msg, res.ok);
        
        defaultUser.setAvatar("defaultUser updated avatar");
        res = controller.updateUser(defaultUser.getId(), null, null, defaultUser.getAvatar());
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.clase.getAvatar(), defaultUser.getAvatar());
        
        res = controller.updateUser(defaultUser.getId() - 100, null, null, null);
        Assert.assertFalse(res.msg, res.ok);
    }
}
