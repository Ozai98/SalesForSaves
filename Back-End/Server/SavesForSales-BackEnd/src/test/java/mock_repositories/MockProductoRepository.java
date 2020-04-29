/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mock_repositories;

import com.example.demo.database.ProductoRepository;
import com.example.demo.database.models.Producto;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author carme
 */
public class MockProductoRepository implements ProductoRepository{
    private HashMap<Integer, Producto> mockTable;
    private AtomicInteger idCounter ;
    
    public MockProductoRepository(){
        mockTable = new HashMap();
        idCounter = new AtomicInteger();
    }
    
    @Override
    public void create(Producto pro) throws Exception {
        pro.setId(idCounter.getAndIncrement());       
        mockTable.put(pro.getId(), new Producto(pro));
    }

    @Override
    public List<Producto> search(String param) throws Exception {
        LinkedList<Producto> exit = new LinkedList();
        for(Producto pro: mockTable.values()) if(pro.getNombre().compareTo(param) == 0) exit.add(pro);
        return exit;
    }

    @Override
    public Producto getById(int ID) throws Exception {
        return mockTable.getOrDefault(ID, null);
    }
}
