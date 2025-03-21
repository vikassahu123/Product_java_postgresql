package org.example;

import java.util.ArrayList;
import java.util.List;
public class ProductService {

    List<Product> products=new ArrayList<>();

    ProductDB db=new ProductDB();

    public void addProduct(Product p)
    {
//        products.add(p);
        db.save(p);

    }

    public List<Product> getAllProducts()
    {
        // return products;
      return db.getAll();
    }

    public Product getProduct(String name)
    {
//      for(Product p:products) {
//          if (p.getName().equals(name)) {
//              return p;
//
//          }
//      }
//      return null;

        return db.getProductByName(name);

    }

    public Product getProductByPlace(String place)
    {
        for(Product p:products) {
            if (p.getPlace().equals(place)) {
                return p;
            }
        }
        return null;
    }

    public List<Product> getProductByText(String text) {

        String str=text.toLowerCase();
        List<Product> prod = new ArrayList<>();
        for (Product p : products) {
            String name=p.getName().toLowerCase();
            String place=p.getPlace().toLowerCase();
            String type=p.getType().toLowerCase();
            if (name.contains(str) || place.contains(str) || type.equals(str)) {
                prod.add(p);
            }
        }

        return prod;
    }
}
