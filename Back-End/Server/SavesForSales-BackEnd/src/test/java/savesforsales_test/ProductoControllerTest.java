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
import com.example.demo.controllers.ProductoController;
import com.example.demo.database.ProductoRepository;
import com.example.demo.database.models.Producto;
import com.example.demo.services.Services;
import mock_repositories.MockProductoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


public class ProductoControllerTest{
    private ProductoController controller;
    private Producto defaultProduct;
    @BeforeClass
    public static void setup() {
        Services.startServices();
        
    }
    
    @Before
    public void before() throws Exception{
        MockProductoRepository repo = new MockProductoRepository();
        controller = new ProductoController();
        controller.setProductoRepository((ProductoRepository) repo);
        defaultProduct = new Producto();
        defaultProduct.setNombre("defaultProduct Name");
        defaultProduct.setPrecio(0);
        defaultProduct.setImagen("defaultProduct Image");
        defaultProduct.setCantidad(0);
        Response<Producto> res = controller.crear(defaultProduct.getNombre(), defaultProduct.getPrecio(), null, defaultProduct.getImagen(), defaultProduct.getCantidad());
        if(!res.ok) Assert.fail("Fail default product creation");
        defaultProduct.setId(res.clase.getId());
    }
    
    @Test
    public void SearchProduct(){
        Response<Producto[]> res = controller.searchProductos(defaultProduct.getNombre());
                
        Assert.assertTrue(res.msg, res.ok);
        boolean found = false; 
        for(int i = 0; i < res.clase.length && !found; i++) found = res.clase[i].id == defaultProduct.id; 
        if(!found) Assert.fail();
        
    }
    
    @Test
    public void GetProduct(){
        Response<Producto> res = controller.getProducto(defaultProduct.getId());
                
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.clase.getId(), defaultProduct.getId());        
    }
    
    @Test
    public void CreateProduct(){
        Response<Producto> res = controller.crear(defaultProduct.getNombre(), defaultProduct.getPrecio(), defaultProduct.getProveedor().getId(), defaultProduct.getImagen(), defaultProduct.getCantidad());
                
        Assert.assertTrue(res.msg, res.ok);
        Assert.assertEquals(res.clase.getNombre(), defaultProduct.getNombre());        
    }
}
