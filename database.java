import java.sql.*;


public class database {

    private String[] getCustomerInfo(int customerID)
        {
            String connectionUrl = "jdbc:mysql://localhost:3306/publicstorage2";
            String[] CustomerName = new String[4];
            try (Connection connection = DriverManager.getConnection(connectionUrl, "root", "MySQL");) {
            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("select first_name, last_name, phone_number, email from customer where customer_id="+customerID+";");
           
            //for (int i = 0; i < 4; i++)
            int i = 0;
            while(rs.next()){
                while (i < 4)
                {
                    CustomerName[i] = rs.getString(i+1);
                    System.out.println(rs.getString(i+1));
                    i++;
                }
                
            }
            
            System.out.println("Hello!");
            
        }
            catch (SQLException e) {
            e.printStackTrace();
        }

            return CustomerName;
        }

        public String[] getCustomerInformation (int customerID)
        {

            String[] customerInfromation = new String[4];
            customerInfromation=getCustomerInfo(customerID);
            return customerInfromation;
        
        }

    database()
    {
  
    }
}
