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
import com.example.demo.database.models.Client;
import com.example.demo.database.models.Historic;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MockHistoricRepository {
    private HashMap<Integer, Client> mockTable;
    private AtomicInteger idCounter ;
    
    public MockClientRepository(){
        mockTable = new HashMap();
        idCounter = new AtomicInteger();
    }
}
