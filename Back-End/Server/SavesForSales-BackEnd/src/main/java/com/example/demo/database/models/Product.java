package com.example.demo.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

@DatabaseTable(tableName="Product")
public class Product{
	
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private double price;
	@DatabaseField
	private String name;
	@DatabaseField(foreign = true, columnName = "provider")
	private Provider provider;
        @DatabaseField
        private String image;
        @DatabaseField
        private double quantity;
        @DatabaseField
        private Date publicationDate;
        @DatabaseField
        private Date timeLimit;
        @DatabaseField
        private String category;
        
        public Product(){}
        
        public Product(Product other){
            id = other.id;
            price = other.price;
            name = other.name;
            provider = new Provider(other.provider);
            image = other.image;
            quantity = other.quantity;
            publicationDate = other.publicationDate;
            timeLimit = other.timeLimit;
            category = other.category;
        }
        
	public int getId(){return id;}
	public double getPrice(){return price;}
	public String getName(){return name;}
	public Provider getProvider(){return provider;}
        public String getImage(){return image;}
        public double getQuantity(){return quantity;}
        public Date getPublicationDate(){return publicationDate;}
        public Date getTimeLimit(){return timeLimit;};
        public String getCategory(){return category;};

        public void setId(int id){this.id = id;}
	public void setPrice(double p){price=p;}
	public void setName(String n){name=n;}
	public void setProvider(Provider p){provider=p;}
        public void setImage(String i){image=i;}
        public void setQuantity(double c){quantity = c;}
        public void setPublicationDate(Date d){publicationDate = d;}
        public void setTimeLimit(Date d){timeLimit = d;}
        public void setCategory(String c){category = c;}
}
