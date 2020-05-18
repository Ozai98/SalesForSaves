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

import com.example.demo.database.HistoricRepository;
import com.example.demo.database.models.Historic;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MockHistoricRepository implements HistoricRepository{
    private HashMap<Integer, Historic> mockTable;
    private AtomicInteger idCounter ;
    
    public MockHistoricRepository(){
        mockTable = new HashMap();
        idCounter = new AtomicInteger();
    }
    
    @Override
    public List<Historic> getForUserAndState(Integer usrId, String state) throws Exception{
        LinkedList<Historic> exit = new LinkedList();
        for(Historic hsc: mockTable.values()) if(hsc.getUser().getId() == usrId && hsc.getState().compareTo(state) == 0) exit.add(hsc);
        return exit;
    }
    
    @Override
    public List<Historic> getForProviderAndState(Integer usrId, String state) throws Exception{
        LinkedList<Historic> exit = new LinkedList();
        for(Historic hsc: mockTable.values()) if(hsc.getUser().getId() == usrId && hsc.getState().compareTo(state) == 0) exit.add(hsc);
        return exit;
    }
    
    @Override
    public void create(Historic hsc) throws Exception {
        hsc.setId(idCounter.getAndIncrement());       
        mockTable.put(hsc.getId(), new Historic(hsc));
    }

    @Override
    public Historic getById(int ID) throws Exception {
        return mockTable.getOrDefault(ID, null);
    }

    @Override
    public void update(Historic obj) throws Exception {
        mockTable.put(obj.getId(), obj);
    }

    @Override
    public void refresh(Historic obj) throws Exception {
        
    }
}
