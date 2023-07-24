package JDBC1;

public interface ProductDAO {
	
	int insertProduct(Product p); 
	int updateProduct(Product p); 
	int deleteProduct(String id); 
	String[] getID(); 
	Product fillFields(String item);
}
