    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savesforsales_test;

import com.example.demo.controllers.UserController;
import com.example.demo.database.models.User;
import com.example.demo.controllers.Response;
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
public class UserControllerTest {
 
    private UserController controller;
    
    private User defaultUser;
    @BeforeClass
    public static void setup() {
        Utils.defaultSetup();
    }

    @Before
    public void before() throws Exception{
        controller = new UserController();
        defaultUser = new User();
        defaultUser.setName("defaultUser Name");
        defaultUser.setMail("defaultUser@Test.Email");        
        defaultUser.setPassword("defaultUser password");
        defaultUser.setAvatar("defaultUser avatar");
        Response<User> res = controller.create(defaultUser.getName(), defaultUser.getMail(), defaultUser.getPassword(), null);
        if(!res.ok) Assert.fail("Fail default user creation");
        defaultUser.setId(res.classX.getId());
    }
    
    @Test
    public void UserCreationTest(){
        User tstUser = new User();
        tstUser.setName("User creation Test Name");
        tstUser.setMail("UserCeation@Test.Email");        
        tstUser.setPassword("User creation Test password");
        tstUser.setAvatar("User creation Test avatar");
        
        Response res = controller.create(tstUser.getName(), tstUser.getMail(), tstUser.getPassword(), null);
        
        Assert.assertEquals(res.msg, true, res.ok);
    }
    
    @Test
    public void LoginTest(){
        Response<User> res = controller.login(defaultUser.getMail(), defaultUser.getPassword());
        
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.classX.getName(), defaultUser.getName());
        Assert.assertEquals(res.classX.getAvatar(), defaultUser.getAvatar());
        Assert.assertEquals(res.classX.getId(), defaultUser.getId());    
        
        res = controller.login(defaultUser.getMail(), "Bad Password");
        Assert.assertFalse(res.msg, res.ok);
        
        res = controller.login("Bad Email", defaultUser.getPassword());
        Assert.assertFalse(res.msg, res.ok);
    }
    
    @Test
    public void GetByEmailTest(){
        defaultUser = controller.getById(defaultUser.getId()).classX;
        Response<User> res = controller.getByEmail(defaultUser.getMail());
        
        Assert.assertTrue(res.msg, res.ok);
        //Assert.assertEquals(res.classX.getName(), defaultUser.getName());
        //Assert.assertEquals(res.classX.getAvatar(), defaultUser.getAvatar());
        Assert.assertEquals(res.classX.getId(), defaultUser.getId());  
        
        res = controller.getByEmail("Bad Email");
        
        Assert.assertFalse(res.ok);
    }
    
    @Test
    public void GetByIdTest(){
        Response<User> res = controller.getById(defaultUser.getId());
        
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.classX.getName(), defaultUser.getName());
        Assert.assertEquals(res.classX.getAvatar(), defaultUser.getAvatar());
        Assert.assertEquals(res.classX.getId(), defaultUser.getId());  
        
        res = controller.getById(defaultUser.getId() - 100);
        
        Assert.assertFalse(res.msg, res.ok);
    }
    
    @Test
    public void UpdateUserTest(){
        
        String newName =  "defaultUser updated Name";
        
        Response<User> res = controller.updateUser(defaultUser.getId(), newName, null, null);
        Assert.assertTrue(res.msg, res.ok);
        User newUser = controller.getById(defaultUser.getId()).classX;
        Assert.assertEquals(newUser.getName(), newName);
        
        defaultUser.setPassword("defaultUser updated password");
        res = controller.updateUser(defaultUser.getId(), null, defaultUser.getPassword(), null);
        Assert.assertTrue(res.msg, res.ok);
        
        defaultUser.setAvatar("defaultUser updated avatar");
        res = controller.updateUser(defaultUser.getId(), null, null, null);
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.classX.getAvatar(), defaultUser.getAvatar());
        
        res = controller.updateUser(defaultUser.getId() - 100, null, null, null);
        Assert.assertFalse(res.msg, res.ok);
    }
}
