/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savesforsales_test;

/**
 *
 * @author carme
 */

import com.example.demo.controllers.Response;
import com.example.demo.controllers.ProveedorController;
import com.example.demo.database.ProveedorRepository;
import com.example.demo.database.models.Proveedor;
import com.example.demo.services.Services;
import mock_repositories.MockProveedorRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


public class ProveedorControllerTest {
    private ProveedorController controller;
    private Proveedor defaultProveedor;
    @BeforeClass
    public static void setup() {
        Services.startServices();
    }
    
    @Before
    public void before() throws Exception{
        MockProveedorRepository repo = new MockProveedorRepository();
        controller = new ProveedorController();
        controller.setProveedorRepository((ProveedorRepository) repo);
        defaultProveedor = new Proveedor();
        defaultProveedor.setNombre("defaultProveedor Name");
        defaultProveedor.setCorreo("defaultProveedor@Test.Email");        
        defaultProveedor.setPassword("defaultProveedor password");
        defaultProveedor.setAvatar("defaultProveedor avatar");
        defaultProveedor.setUbicacion("defaultProveedor ubicacion");
        Response<Proveedor> res = controller.crear(defaultProveedor.getNombre(), defaultProveedor.getCorreo(), defaultProveedor.getPassword(), defaultProveedor.getAvatar());
        if(!res.ok) Assert.fail("Fail default proveedor creation");
        defaultProveedor.setId(res.clase.getId());
    }
    
    @Test
    public void Create(){
        Response<Proveedor> res = controller.crear(defaultProveedor.getNombre(), defaultProveedor.getCorreo(), defaultProveedor.getPassword(), defaultProveedor.getAvatar());
        
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.clase.getNombre(), defaultProveedor.getNombre());
        Assert.assertEquals(res.clase.getAvatar(), defaultProveedor.getAvatar());   
        
    }
    
    @Test
    public void LoginTest(){
        Response<Proveedor> res = controller.login(defaultProveedor.getCorreo(), defaultProveedor.getPassword());
        
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.clase.getNombre(), defaultProveedor.getNombre());
        Assert.assertEquals(res.clase.getAvatar(), defaultProveedor.getAvatar());
        Assert.assertEquals(res.clase.getId(), defaultProveedor.getId());    
        
        res = controller.login(defaultProveedor.getCorreo(), "Bad Password");
        Assert.assertFalse(res.msg, res.ok);
        
        res = controller.login("Bad Email", defaultProveedor.getPassword());
        Assert.assertFalse(res.msg, res.ok);
    }
    
    @Test
    public void GetByIdTest(){
        Response<Proveedor> res = controller.getById(defaultProveedor.getId());
        
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.clase.getNombre(), defaultProveedor.getNombre());
        Assert.assertEquals(res.clase.getAvatar(), defaultProveedor.getAvatar());
        Assert.assertEquals(res.clase.getId(), defaultProveedor.getId());  
        
        res = controller.getById(defaultProveedor.getId() - 100);
        
        Assert.assertFalse(res.msg, res.ok);
    }
    
    @Test
    public void UpdateUserTest(){
        
        defaultProveedor.setNombre("defaultProveedor updated Name");
        Response<Proveedor> res = controller.updateProveedor(defaultProveedor.getId(), defaultProveedor.getNombre(), null, null, null);
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.clase.getNombre(), defaultProveedor.getNombre());
        
        defaultProveedor.setPassword("defaultUser updated password");
        res = controller.updateProveedor(defaultProveedor.getId(), null, defaultProveedor.getPassword(), null, null);
        Assert.assertTrue(res.msg, res.ok);
        
        defaultProveedor.setAvatar("defaultProveedor updated avatar");
        res = controller.updateProveedor(defaultProveedor.getId(), null, null, defaultProveedor.getAvatar(), null);
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.clase.getAvatar(), defaultProveedor.getAvatar());
        
        res = controller.updateProveedor(defaultProveedor.getId() - 100, null, null, null, null);
        Assert.assertFalse(res.msg, res.ok);
    }
}
