/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mock_repositories;

/**
 *
 * @author carme
 */

import com.example.demo.database.ProveedorRepository;
import com.example.demo.database.models.Proveedor;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MockProveedorRepository implements ProveedorRepository{
    private HashMap<Integer, Proveedor> mockTable;
    private AtomicInteger idCounter ;
    
    public MockProveedorRepository(){
        mockTable = new HashMap();
        idCounter = new AtomicInteger();
    }
    
    @Override
    public void create(Proveedor pro) throws Exception {
        pro.setId(idCounter.getAndIncrement());       
        mockTable.put(pro.getId(), new Proveedor(pro));
    }
    
    @Override
    public List<Proveedor> getByEmail(String Email) throws Exception{
        LinkedList<Proveedor> exit = new LinkedList();
        for(Proveedor pro: mockTable.values()) if(pro.getCorreo().compareTo(Email) == 0) exit.add(pro);
        return exit;
    }

    @Override
    public Proveedor getById(int ID) throws Exception {
        return mockTable.getOrDefault(ID, null);
    }
    
    @Override
    public void update(Proveedor pro) throws Exception {
        mockTable.put(pro.getId(), pro);
    }
    
    @Override
    public void refresh(Proveedor pro) throws Exception {
        
    }
}
