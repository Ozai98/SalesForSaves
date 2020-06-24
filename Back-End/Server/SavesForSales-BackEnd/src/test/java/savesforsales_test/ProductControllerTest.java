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
import com.example.demo.database.models.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductControllerTest {
    private ProductController controller;
    private Product defaultProduct;

    @BeforeClass
    public static void setup() {
        Utils.defaultSetup();
    }

    @Before
    public void before() throws Exception {
        controller = new ProductController();
        defaultProduct = new Product();
        defaultProduct.setName("defaultProduct Name");
        defaultProduct.setPrice(100);
        // defaultProduct.setImage("defaultProduct Image");
        defaultProduct.setQuantity(5);
        defaultProduct.setSaved(23);

        ProviderController provController = new ProviderController();
        int id = provController.create("Tst prov", "TestMailProv@test.com", "0000", null, 0.0,0.0).classX.getId();

        Response<Product> res = controller.create(defaultProduct.getName(), defaultProduct.getPrice(), id,
                null, defaultProduct.getQuantity(), defaultProduct.getTimeLimit(), null, defaultProduct.getSaved());
        if (!res.ok)
            Assert.fail("Fail default product creation. Ex: " + res.msg);
        defaultProduct.setId(res.classX.getId());
        
    }

    @Test
    public void SearchProduct() {
        Response<Product[]> res = controller.searchProducts(defaultProduct.getName());

        Assert.assertTrue(res.msg, res.ok);
        boolean found = false;
        for (int i = 0; i < res.classX.length && !found; i++)
            found = res.classX[i].getId() == defaultProduct.getId();
        if (!found)
            Assert.fail();

    }

    @Test
    public void GetProduct() {
        Response<Product> res = controller.getProduct(defaultProduct.getId());

        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.classX.getId(), defaultProduct.getId());
    }

    @Test
    public void CreateProduct(){
        Response<Product> res = controller.create(defaultProduct.getName(), defaultProduct.getPrice(), ProductController.normalize(defaultProduct).getProvider().getId(), null, defaultProduct.getQuantity(), defaultProduct.getTimeLimit(), "Category", defaultProduct.getSaved());
                
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.classX.getName(), defaultProduct.getName());
    }
}
