package com.ekart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ekart.model.Cart;
import com.ekart.model.Product;

public class ProductDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public ProductDao(Connection con) {
		this.con = con;
	}
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<Product>();
		try {
			query = "select * from products";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getInt("price"));
				row.setImage(rs.getString("image"));
				
				products.add(row);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return products;
	}
	public Product addProduct(String category,String name,int price,String image) {
		Product product = null;
		try {
			query = "insert into products(category,name,price,image) values(?,?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setString(1, category);
			pst.setString(2, name);
			pst.setInt(3, price);
			pst.setString(4, image);
			int insert = pst.executeUpdate();
			if(insert > 0) {
				product = new Product();
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return product;
	}
	
	public List<Cart> getCartProducts (ArrayList<Cart> cartList){
		List<Cart> products = new ArrayList<Cart>();
		try {
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					query = "select * from products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1,item.getId());
					rs = pst.executeQuery();
					while(rs.next()) {
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getInt("price")*item.getQuentity());
						row.setQuentity(item.getQuentity());
						products.add(row);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return products;
	}
	public double getTotalCartPrice(ArrayList<Cart> cartlist) {
		double sum =0;
		try {
			if(cartlist.size()>0) {
				for(Cart item:cartlist) {
					query = "select price from products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while(rs.next()) {
						sum+=rs.getInt("price")*item.getQuentity();
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return sum;
	}

	 public Product getSingleProduct(int id) {
		 Product row = null;
	        try {
	            query = "select * from products where id=? ";

	            pst = this.con.prepareStatement(query);
	            pst.setInt(1, id);
	            ResultSet rs = pst.executeQuery();

	            while (rs.next()) {
	            	row = new Product();
	                row.setId(rs.getInt("id"));
	                row.setName(rs.getString("name"));
	                row.setCategory(rs.getString("category"));
	                row.setPrice(rs.getInt("price"));
	                row.setImage(rs.getString("image"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }

	        return row;
	    }
}
