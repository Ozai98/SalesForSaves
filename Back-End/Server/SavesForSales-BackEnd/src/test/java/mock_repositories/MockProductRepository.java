/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mock_repositories;

import com.example.demo.database.ProductRepository;
import com.example.demo.database.models.Product;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author carme
 */
public class MockProductRepository implements ProductRepository{
    private HashMap<Integer, Product> mockTable;
    private AtomicInteger idCounter ;
    
    public MockProductRepository(){
        mockTable = new HashMap();
        idCounter = new AtomicInteger();
    }
    
    @Override
    public void create(Product pro) throws Exception {
        pro.setId(idCounter.getAndIncrement());       
        mockTable.put(pro.getId(), new Product(pro));
    }

    @Override
    public List<Product> searchValid(String param) throws Exception {
        LinkedList<Product> exit = new LinkedList();
        for(Product pro: mockTable.values()) if(pro.getName().compareTo(param) == 0) exit.add(pro);
        return exit;
    }

    @Override
    public Product getById(int ID) throws Exception {
        return mockTable.getOrDefault(ID, null);
    }

    @Override
    public void update(Product obj) throws Exception {
        mockTable.put(obj.getId(), obj);
    }

    @Override
    public void refresh(Product obj) throws Exception {
        
    }
}
