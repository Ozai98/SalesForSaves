/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mock_repositories;

import com.example.demo.database.UsuarioRepository;
import com.example.demo.database.models.Usuario;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author German le yo
 */
public class MockUsuarioRepository implements UsuarioRepository{

    private HashMap<Integer, Usuario> mockTable;
    private AtomicInteger idCounter ;
    
    public MockUsuarioRepository(){
        mockTable = new HashMap();
        idCounter = new AtomicInteger();
    }
    
    @Override
    public void create(Usuario usr) throws Exception {
        usr.setId(idCounter.getAndIncrement());
        mockTable.put(usr.getId(), usr);
    }

    @Override
    public List<Usuario> getByEmail(String email) throws Exception {
        LinkedList<Usuario> exit = new LinkedList();
        for(Usuario usr: mockTable.values()) if(usr.getCorreo().compareTo(email) == 0) exit.add(usr);
        return exit;
    }

    @Override
    public Usuario getById(int ID) throws Exception {
        return mockTable.getOrDefault(ID, null);
    }

    @Override
    public void update(Usuario usr) throws Exception {
        mockTable.put(usr.getId(), usr);
    }
    
}
