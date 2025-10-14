package com.apm.app;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.apm.bll.BookBO;
import com.apm.bll.BussinessLayerFasade;
import com.apm.dal.BookDAO;
import com.apm.dal.DBConnectionDBC;
import com.apm.dal.DataAccessLayerFasade;
import com.apm.pl.Arabic_Prose_User_Interface;
//this.conn = DBConnectionDBC.getConnection();
//try {
//	this.stmt = conn.createStatement();
//} catch (SQLException e) {
//	e.printStackTrace();
//}
public class App {
	
	 public static void main(String[] args) {
		  Connection conn = DBConnectionDBC.getConnection();
		  BookDAO bookDAO = null; // initialize with null so we can access it outside try catch
		try {
			bookDAO = new BookDAO(conn,conn.createStatement() );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  DataAccessLayerFasade daf = new DataAccessLayerFasade(bookDAO,null,null);
		  BookBO bookBO = new BookBO (daf);
		  BussinessLayerFasade blf = new BussinessLayerFasade(bookBO,null,null);
	        try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch (Exception e) {
	            // Fallback to default look and feel
	        }
	        SwingUtilities.invokeLater(() -> new Arabic_Prose_User_Interface(blf));
	    }
}
