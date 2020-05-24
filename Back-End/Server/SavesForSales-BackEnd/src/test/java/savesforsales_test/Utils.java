/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savesforsales_test;

import com.example.demo.database.RepositoryController;
import com.example.demo.services.Services;
import mock_repositories.MockHistoricRepository;
import mock_repositories.MockProductRepository;
import mock_repositories.MockProviderRepository;
import mock_repositories.MockUserRepository;

/**
 *
 * @author German le yo
 */
public class Utils {
    public static void defaultSetup(){
        Services.startServices();
        RepositoryController.setUser(new MockUserRepository());
        RepositoryController.setProduct(new MockProductRepository());
        RepositoryController.setProvider(new MockProviderRepository());
        RepositoryController.setHistoric(new MockHistoricRepository());
    }
}
