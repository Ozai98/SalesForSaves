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

import com.example.demo.database.ClientRepository;
import com.example.demo.database.models.Provider;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MockProviderRepository implements ClientRepository<Provider>{
    private HashMap<Integer, Provider> mockTable;
    private AtomicInteger idCounter ;
    
    public MockProviderRepository(){
        mockTable = new HashMap<>();
        idCounter = new AtomicInteger();
    }
    
    @Override
    public void create(Provider pro) throws Exception {
        pro.setId(idCounter.getAndIncrement());       
        mockTable.put(pro.getId(), new Provider(pro));
    }
    
    @Override
    public List<Provider> getByEmail(String Email) throws Exception{
        LinkedList<Provider> exit = new LinkedList<>();
        for(Provider pro: mockTable.values()) if(pro.getMail().compareTo(Email) == 0) exit.add(new Provider(pro));
        return exit;
    }

    @Override
    public Provider getById(int ID) throws Exception {
        return new Provider(mockTable.getOrDefault(ID, null));
    }
    
    @Override
    public void update(Provider pro) throws Exception {
        mockTable.put(pro.getId(), pro);
    }
    
    @Override
    public void refresh(Provider pro) throws Exception {
        
    }
}
