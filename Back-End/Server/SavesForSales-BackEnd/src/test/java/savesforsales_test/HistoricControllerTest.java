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

import com.example.demo.controllers.HistoricController;
import com.example.demo.database.models.Historic;
import com.example.demo.database.models.User;
import com.example.demo.database.models.Product;
import com.example.demo.database.models.Provider;
import com.example.demo.controllers.Response;
import java.util.Calendar;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HistoricControllerTest {
    private HistoricController controller;
    private Historic defaultHistoric;
    @BeforeClass
    public static void setup() {
        Utils.defaultSetup();
    }
       @Before
    public void before() throws Exception{
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        Date dt2 = c.getTime();
        
        controller = new HistoricController();
        defaultHistoric = new Historic();
        defaultHistoric.setBuyDate(dt);
        defaultHistoric.setReserveMoment(dt2);
        User defaultUser = new User();
        defaultUser.setName("defaultUser Name");
        defaultUser.setMail("defaultUser@Test.Email");        
        defaultUser.setPassword("defaultUser password");
        defaultUser.setAvatar("defaultUser avatar");
        defaultHistoric.setUser(defaultUser);
        Provider defaultProvider = new Provider();
        defaultProvider.setName("defaultProvider Name");
        defaultProvider.setMail("defaultProvider@Test.Email");        
        defaultProvider.setPassword("defaultProvider password");
        defaultProvider.setAvatar("defaultProvider avatar");
        defaultProvider.setUbication("defaultProvider ubicacion");
        Product defaultProduct = new Product();
        defaultProduct = new Product();
        defaultProduct.setName("defaultProduct Name");
        defaultProduct.setPrice(100);
        defaultProduct.setImage("defaultProduct Image");
        defaultProduct.setProvider(defaultProvider);
        defaultProduct.setQuantity(5);
        defaultProduct.setPublicationDate(dt);
        defaultHistoric.setProduct(defaultProduct);
        defaultHistoric.setQuantity(1);
        defaultHistoric.setState("RESERVED");
        defaultHistoric.setReserveMoment(dt2);
        Response<Historic> res = controller.reserve(defaultHistoric.getUser().getId(), defaultHistoric.getProduct().getId(), defaultHistoric.getQuantity(), defaultHistoric.getTimeReserve());
        if(!res.ok) Assert.fail("Fail default Historic creation. Ex: " + res.msg);
        defaultHistoric.setId(res.classX.getId());
    }
    
    @Test
    public void buyedProduct(Integer idHistoric){
        Response <Historic> res = controller.buyedProduct(idHistoric);
         Assert.assertTrue(res.msg, res.ok);
    }
    
    @Test
    public void getUserReserved(Integer idUser){
        Response <Historic[]> res = controller.getUserReserved(idUser);
         Assert.assertTrue(res.msg, res.ok);
    }
    
    @Test
    public void getUserHistoric(Integer idUser){
        Response <Historic[]> res = controller.getUserHistoric(idUser);
         Assert.assertTrue(res.msg, res.ok);
    }
    
    @Test
    public void getProviderReserved(Integer idProv){
        Response <Historic[]> res = controller.getUserReserved(idProv);
         Assert.assertTrue(res.msg, res.ok);
    }
    
    @Test
    public void getProviderHistoric(Integer idProv){
        Response <Historic[]> res = controller.getUserHistoric(idProv);
         Assert.assertTrue(res.msg, res.ok);
    }
}
