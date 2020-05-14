/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mock_repositories;

import com.example.demo.database.ClientRepository;
import com.example.demo.database.models.User;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author German le yo
 */
public class MockUserRepository implements ClientRepository<User>{

    private HashMap<Integer, User> mockTable;
    private AtomicInteger idCounter ;
    
    public MockUserRepository(){
        mockTable = new HashMap();
        idCounter = new AtomicInteger();
    }
    
    @Override
    public void create(User usr) throws Exception {
        usr.setId(idCounter.getAndIncrement());       
        mockTable.put(usr.getId(), new User(usr));
    }

    @Override
    public List<User> getByEmail(String email) throws Exception {
        LinkedList<User> exit = new LinkedList();
        for(User usr: mockTable.values()) if(usr.getMail().compareTo(email) == 0) exit.add(usr);
        return exit;
    }

    @Override
    public User getById(int ID) throws Exception {
        return mockTable.getOrDefault(ID, null);
    }

    @Override
    public void update(User usr) throws Exception {
        mockTable.put(usr.getId(), usr);
    }

    @Override
    public void refresh(User obj) throws Exception {

    }
    
}
