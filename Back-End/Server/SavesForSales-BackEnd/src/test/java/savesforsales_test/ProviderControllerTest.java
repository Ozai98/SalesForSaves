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
import com.example.demo.controllers.ProviderController;
import com.example.demo.database.models.Provider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ProviderControllerTest {
    private ProviderController controller;
    private Provider defaultProvider;
    @BeforeClass
    public static void setup() {
        Utils.defaultSetup();
    }
    
    @Before
    public void before() throws Exception{
        controller = new ProviderController();
        Response<Provider> res = controller.create("defaultProvider Name", "defaultProvider@Test.Email", "defaultProvider password", null);
        if(!res.ok) Assert.fail("Fail default proveedor creation");
        defaultProvider.setId(res.classX.getId());
    }
    
    @Test
    public void Create(){
        Provider other = new Provider();
        
        other.setName("Name 1");
        other.setMail("Name1@mail.com");
        other.setPassword("0000");
        
        Response<Provider> res = controller.create(other.getName(), other.getMail(), other.getPassword(), null);
        
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.classX.getName(), defaultProvider.getName());
        Assert.assertEquals(res.classX.getAvatar(), defaultProvider.getAvatar());   
    }
    
    @Test
    public void LoginTest(){
        Response<Provider> res = controller.login(defaultProvider.getMail(), defaultProvider.getPassword());
        
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.classX.getName(), defaultProvider.getName());
        Assert.assertEquals(res.classX.getAvatar(), defaultProvider.getAvatar());
        //Assert.assertEquals(res.classX.getId(), defaultProvider.getId());    
        
        res = controller.login(defaultProvider.getMail(), "Bad Password");
        Assert.assertFalse(res.msg, res.ok);
        
        res = controller.login("Bad Email", defaultProvider.getPassword());
        Assert.assertFalse(res.msg, res.ok);
    }
    
    @Test
    public void GetByIdTest(){
        Response<Provider> res = controller.getById(defaultProvider.getId());
        
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.classX.getName(), defaultProvider.getName());
        Assert.assertEquals(res.classX.getAvatar(), defaultProvider.getAvatar());
        Assert.assertEquals(res.classX.getId(), defaultProvider.getId());  
        
        res = controller.getById(defaultProvider.getId() - 100);
        
        Assert.assertFalse(res.msg, res.ok);
    }
    
    @Test
    public void UpdateProviderTest(){
        
        defaultProvider.setName("defaultProvider updated Name");
        Response<Provider> res = controller.updateProvider(defaultProvider.getId(), defaultProvider.getName(), null, null, null);
        Assert.assertTrue("Msg : " + res.msg, res.ok);
        Assert.assertEquals(res.classX.getName(), defaultProvider.getName());
        
        defaultProvider.setPassword("defaultUser updated password");
        res = controller.updateProvider(defaultProvider.getId(), null, defaultProvider.getPassword(), null, null);
        Assert.assertTrue(res.msg, res.ok);
        
        defaultProvider.setAvatar("defaultProvider updated avatar");
        res = controller.updateProvider(defaultProvider.getId(), null, null, null, null);
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.classX.getAvatar(), defaultProvider.getAvatar());
        
        res = controller.updateProvider(defaultProvider.getId() - 100, null, null, null, null);
        Assert.assertFalse(res.msg, res.ok);
    }
}
