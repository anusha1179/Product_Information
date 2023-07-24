package JDBC1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDAOImpl implements ProductDAO{
		
	private static Connection con;
	private static Product product;
	
	public ProductDAOImpl(String user,String password)
	{
		con=ConnectionProvider.connect(user,password);
	}
	public static Connection getConnection()
	{
		return con;
	}

	@Override
	public int insertProduct(Product p) {
			String insertquery="INSERT INTO PRODUCTS(ID,DESCRIPTION,PRICE,QUANTITY) VALUES(?,?,?,?)";
			int result=-1;
			try {
				PreparedStatement pstmt=con.prepareStatement(insertquery);
				pstmt.setString(1,p.getID());
				pstmt.setString(2,p.getDescription());
				pstmt.setDouble(3,p.getPrice());
				pstmt.setInt(4,p.getQuantity());
				
				result=pstmt.executeUpdate();
				pstmt.close();
			} 
			catch (Exception e) {
				System.out.println(e);
			}		
			
			return result;
	}

	@Override
	public int updateProduct(Product p) {
		String updatequery="UPDATE PRODUCTS SET DESCRIPTION=?,PRICE=?,QUANTITY=? WHERE ID=?";
		int result=-1;
		try {
			PreparedStatement pstmt=con.prepareStatement(updatequery);
			pstmt.setString(1,p.getDescription());
			pstmt.setDouble(2,p.getPrice());
			pstmt.setInt(3,p.getQuantity());
			pstmt.setString(4,p.getID());

			result=pstmt.executeUpdate();
			pstmt.close();
		} 
		catch (Exception e) {
			System.out.println(e);
		}		
		
		return result;
	
	}

	@Override
	public int deleteProduct(String id) {	
		String deletequery="DELETE FROM PRODUCTS WHERE ID=?";
		int result=-1;
		try {
			PreparedStatement pstmt=con.prepareStatement(deletequery);
			pstmt.setString(1, id);
				
			result=pstmt.executeUpdate();
			pstmt.close();
		} 
		catch (Exception e) {
			System.out.println(e);
		}		
		
		return result;
	}

	@Override
	public String[] getID() {
		String readquery="SELECT ID FROM PRODUCTS";
		String[] products = null ;
		try {
			PreparedStatement pstmt=con.prepareStatement(readquery);	
			ResultSet result=pstmt.executeQuery();
			products=new String[result.getFetchSize()];

			List<String> plist=new ArrayList<>();
			while(result.next())
			{
				plist.add(result.getString(1));
				
			}
			products=plist.toArray(new String[0]);
			Arrays.sort(products);
			pstmt.close();
		} 
	
		catch (Exception e) {
			e.printStackTrace();
		}
		return products;		
		
		
	}
	public  Product fillFields(String item) 
	{
		String getquery="SELECT * FROM PRODUCTS WHERE ID=?";
		
		try {
			PreparedStatement pstmt=con.prepareStatement(getquery);	
			pstmt.setString(1,item);
			ResultSet result=pstmt.executeQuery();
			
			while(result.next())
			{
				String id=result.getString(1);
				String des=result.getString(2);
				double price=result.getDouble(3);
				int quant=result.getInt(4);
				product=new Product(id,des,price,quant);			

			}
			
		} 
	
		catch (Exception e) {
			e.printStackTrace();
		}
		return product;
		
	}

}
