/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.database;

import com.example.demo.database.models.Provider;
import com.example.demo.database.models.Ubication;
import com.example.demo.database.models.User;


/**
 *
 * @author German le yo
 */
public final class RepositoryController {
    
    private static ClientRepository<User> User;
    private static ClientRepository<Provider> Provider;
    private static ProductRepository Product;
    private static HistoricRepository Historic;
    private static CommentsRepository Comments;
    private static Repository<Ubication> Ubication;
    private static MessagesRepository Messages;
    
    public static void setDaoClases(){
        setUser(new UserRepositoryDao());
        setProvider(new ProviderRepositoryDao());
        setProduct(new ProductRepositoryDao());
        setHistoric(new HistoricRepositoryDao());
        setUbication(new UbicationRepositoryDao());
        setComments(new CommentRepositoryDao());
        setMessages(new MessagesRepositoryDao());
    }
    
    public static ClientRepository<User> User(){
        return User;
    }
    public static ClientRepository<Provider> Provider(){
        return Provider;
    }
    public static ProductRepository Product(){
        return Product;
    }
    public static HistoricRepository Historic(){
        return Historic;
    }
    public static CommentsRepository Comment() {
        return Comments;
    }
    public static Repository<Ubication> Ubication(){
        return Ubication;
    }
    public static MessagesRepository Messages(){
        return Messages;
    }

    public static void setMessages(MessagesRepository messagesRepository) {
        Messages = messagesRepository;
    }
    public static void setUbication(Repository<Ubication> repository){
        Ubication = repository;
    }
    public static void setComments(CommentsRepository cRepository) {
        Comments = cRepository;
    }

    public static void setUser(ClientRepository<User> aUser) {
        User = aUser;
    }
    
    public static void setProvider(ClientRepository<Provider> aProvider) {
        Provider = aProvider;
    }
    
    public static void setProduct(ProductRepository aProduct) {
        Product = aProduct;
    }
    
    public static void setHistoric(HistoricRepository aHistoric) {
        Historic = aHistoric;
    }
    public static RateRepositoryDao Rate(){
        return new RateRepositoryDao();
    }
}
