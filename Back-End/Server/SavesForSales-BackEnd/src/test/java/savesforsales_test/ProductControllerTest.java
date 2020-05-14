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
import com.example.demo.controllers.ProductController;
import com.example.demo.controllers.ProviderController;
import com.example.demo.database.ProductRepository;
import com.example.demo.database.RepositoryController;
import com.example.demo.database.models.Product;
import com.example.demo.services.Services;
import mock_repositories.MockProductRepository;
import mock_repositories.MockProviderRepository;
import mock_repositories.MockUserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ProductControllerTest{
    private ProductController controller;
    private Product defaultProduct;
    @BeforeClass
    public static void setup() {
        Utils.defaultSetup();
    }
    
    @Before
    public void before() throws Exception{
        controller = new ProductController();
        defaultProduct = new Product();
        defaultProduct.setName("defaultProduct Name");
        defaultProduct.setPrice(100);
        defaultProduct.setImage("defaultProduct Image");
        defaultProduct.setQuantity(5);
        
        ProviderController provController = new ProviderController();
        int id = provController.create("Tst prov", "TestMailProv@test.com", "0000", "").clase.getId();
        
        Response<Product> res = controller.create(defaultProduct.getName(), defaultProduct.getPrice(), id, defaultProduct.getImage(), defaultProduct.getQuantity());
        if(!res.ok) Assert.fail("Fail default product creation. Ex: " + res.msg);
        defaultProduct.setId(res.clase.getId());
    }
    
    @Test
    public void SearchProduct(){
        Response<Product[]> res = controller.searchProducts(defaultProduct.getName());
                
        Assert.assertTrue(res.msg, res.ok);
        boolean found = false; 
        for(int i = 0; i < res.clase.length && !found; i++) found = res.clase[i].getId() == defaultProduct.getId(); 
        if(!found) Assert.fail();
        
    }
    
    @Test
    public void GetProduct(){
        Response<Product> res = controller.getProduct(defaultProduct.getId());
                
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.clase.getId(), defaultProduct.getId());        
    }
    
    @Test
    public void CreateProduct(){
        Response<Product> res = controller.create(defaultProduct.getName(), defaultProduct.getPrice(), ProductController.normalize(defaultProduct).getProvider().getId(), defaultProduct.getImage(), defaultProduct.getQuantity());
                
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.clase.getName(), defaultProduct.getName());        
    }
}
