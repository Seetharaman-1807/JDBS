package jdbc;

import java.sql.*;
import java.util.Scanner;

public class JDBC_C {

    public static void main(String[] args) throws Exception {

        String url = "jdbc:mysql://localhost:3306/VEHICLE_DB";
        String username = "root";
        String password = "mysql@123";

        Connection con = DriverManager.getConnection(url, username, password);
        Scanner sc = new Scanner(System.in);
       
        int choice;
        do {

            System.out.println("1.Insert Vehicles");
            System.out.println("2.Get Vehicles");
            System.out.println("3.Get Vehicle By ID");
            System.out.println("4.Get Vehicle By Make");
            System.out.println("5.Update Vehicle");
            System.out.println("6.Delete Vehicle");
            System.out.println("0.Exit");
            System.out.println("Enter the choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Year: ");
                    int year = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Model: ");
                    String model = sc.nextLine();
                    System.out.print("Enter Make: ");
                    String make = sc.nextLine();
                    String insert = "INSERT INTO vehicle VALUES(?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(insert);
                    ps.setInt(1, id);
                    ps.setInt(2, year);
                    ps.setString(3, model);
                    ps.setString(4, make);
                    int rows = ps.executeUpdate();
                    System.out.println(rows + " Record Inserted");
                    break;

                case 2:
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM vehicle");
                    while (rs.next()) {
                        System.out.println(rs.getInt("id") + " "+ rs.getInt("year1") + " "+ rs.getString("model") + " "+ rs.getString("make"));
                    }
                    break;
                
                case 3:
                    System.out.print("Enter Vehicle ID: ");
                    int searchId = sc.nextInt();
                    String select = "SELECT * FROM vehicle WHERE id=?";
                    PreparedStatement ps3 = con.prepareStatement(select);
                    ps3.setInt(1, searchId);
                    ResultSet rs3 = ps3.executeQuery();
                    if (rs3.next()) {
                    	System.out.println(rs3.getInt("id") + " "+ rs3.getInt("year1") + " "+ rs3.getString("model") + " "+ rs3.getString("make"));
                    } else {
                        System.out.println("Vehicle Not Found");
                    }
                    break;
                    
                case 4:
                    sc.nextLine();
                    System.out.print("Enter Vehicle Make: ");
                    String searchMake = sc.nextLine();
                    String selectMake = "SELECT * FROM vehicle WHERE make=?";
                    PreparedStatement ps4 = con.prepareStatement(selectMake);
                    ps4.setString(1, searchMake);
                    ResultSet rs4 = ps4.executeQuery();
                    boolean found = false;
                    while (rs4.next()) {
                        found = true;
                        System.out.println("ID: " + rs4.getInt("id"));
                        System.out.println("Year: " + rs4.getInt("year1"));
                        System.out.println("Model: " + rs4.getString("model"));
                        System.out.println("Make: " + rs4.getString("make"));
                        System.out.println();
                    }
                    if (!found) {
                        System.out.println("Vehicle Not Found");
                    }
                    break;
                	
                case 5:
                    System.out.print("Enter Vehicle ID to Update: ");
                    int updateId = sc.nextInt();
                    System.out.print("Enter New Year: ");
                    int updateYear = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Model: ");
                    String updateModel = sc.nextLine();
                    System.out.print("Enter New Make: ");
                    String updateMake = sc.nextLine();
                    String update = "UPDATE vehicle SET year1=?, model=?, make=? WHERE id=?";
                    PreparedStatement ps5 = con.prepareStatement(update);
                    ps5.setInt(1, updateYear);
                    ps5.setString(2, updateModel);
                    ps5.setString(3, updateMake);
                    ps5.setInt(4, updateId);
                    int updateRows = ps5.executeUpdate();
                    if (updateRows > 0)
                        System.out.println("Vehicle Updated Successfully");
                    else
                        System.out.println("Vehicle Not Found");

                    break;               	
                
                case 6:
                    System.out.print("Enter Vehicle ID to Delete: ");
                    int deleteId = sc.nextInt();
                    String delete = "DELETE FROM vehicle WHERE id=?";
                    PreparedStatement ps6 = con.prepareStatement(delete);
                    ps6.setInt(1, deleteId);
                    int deleteRows = ps6.executeUpdate();
                    if (deleteRows > 0)
                        System.out.println("Vehicle Deleted Successfully");
                    else
                        System.out.println("Vehicle Not Found");
                    break;
                case 0:
                    System.out.println("EXIT GET OUT DONT COME BACK");
                    break;
            }
        } 
        while (choice != 0);
    }
}