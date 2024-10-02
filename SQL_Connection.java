import java.sql.*;

public class SQL_Connection 
{
    String connectionUrl = "jdbc:mysql://localhost:3306/publicstorage";
    Connection connection;
    Statement stmt;

    private Connection connectToDatabase()
    {
        try
        {
            Connection connection = DriverManager.getConnection(connectionUrl, "root", "MySQL");
            stmt = connection.createStatement();
        }

        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return connection;
    }

    // Return customer information
    String[] getCustomerInfo(int customerID)
    {
        String[] customerInfo = new String[4];
        ResultSet rs;
        try
        {
            rs = stmt.executeQuery("select first_name, last_name, phone_number, email from customer where customer_id="+customerID+";");

            int i = 0;
            while(rs.next())
            {
                while (i < 4)
                {
                    customerInfo[i] = rs.getString(i+1);
                    System.out.println(rs.getString(i+1));
                    i++;
                }
                
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        
        return customerInfo;
    }
    
    // Return a room's size from a room number
    // Input: Room Number (Integer)
    // Output: Room Size (Integer); 1=small 2=medium 3=large
    int getRoomSize(int roomNumber)
    {
        int roomSize = 0;
        ResultSet rs;

        try 
        {
            rs = stmt.executeQuery("select room_size from room where idroom="+roomNumber+";");
            while(rs.next())
            {
                roomSize = rs.getInt("room_size");
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return roomSize;
    }

    // Return an employee's username and password
    // Input: Employee ID (Integer)
    // Output: Username and Password (2 strings)
    String[] getEmployeeInformation(int employeeID)
    {
        String username = "";
        String password = "";
        ResultSet rs;

        try 
        {
            rs = stmt.executeQuery("select username, password from employee where idemployee="+employeeID+";");
            while(rs.next())
            {
                username = rs.getString("username");
                password = rs.getString("password");
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        String[] employeeInfo = {username, password};
        return employeeInfo;
    }

    // Return empty room dimensions
    // Input: Room Size (Integer)
    // Output: Room Dimensions (3 Integers): Width, Depth, and Height
    int[] getEmptyRoomDimensions(int roomSize)
    {
        // array with 3 elements: W,D,H
        int[] roomDimensions = {0,0,0};
        ResultSet rs;

        try 
        {
            rs = stmt.executeQuery("select room_width, room_depth, room_height from room_size where idroom_sizes="+roomSize+";");
            while(rs.next())
            {
                roomDimensions[0] = rs.getInt("room_width");
                roomDimensions[1] = rs.getInt("room_depth");
                roomDimensions[2] = rs.getInt("room_height");
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

        return roomDimensions;
    }

    // Return all items from a room
    item[] getRoomItems(int roomNumber)
    {
        // first check to see how many items are currently in a room
        int numberOfItems = 0;
        item[] items;
        ResultSet rs;

        try 
        {
            rs = stmt.executeQuery("select count(iditem) from item where roomID="+roomNumber+";");
            rs.next();
            numberOfItems = rs.getInt(1);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

        if (numberOfItems > 0) 
        {
            items = new item[numberOfItems];
        
            try 
            {
                rs = stmt.executeQuery("select * from item where roomID="+roomNumber+";");
                int i = 0;
                while(rs.next())
                {
                    items[i] = new item();
                    items[i].itemID = rs.getInt("iditem");
                    items[i].name = rs.getString("name");
                    items[i].width = rs.getInt("width");
                    items[i].depth = rs.getInt("depth");
                    items[i].height = rs.getInt("height");
                    items[i].roomID = rs.getInt("roomID");
                    i = i+1;
                }
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
        else
        {
            items = new item[1];
        }
        return items;
    }

    // Add customer to database
    int addCustomerToDatabase (customer newCustomer)
    {
        int newCustomerID = 0;
        ResultSet rs;
        String sqlStatement = "insert into customer (first_name, last_name, phone_number, email) ";
        sqlStatement = sqlStatement + "values ('"+newCustomer.firstName+"', '"+newCustomer.lastName+"', '"+newCustomer.phoneNumber+"', '"+newCustomer.email+"')";
        try 
        {
            stmt.executeUpdate(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            while (rs.next())
            {
              newCustomerID = rs.getInt(1);  
            }

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return newCustomerID;
    }

    // Add employee to database
    int addEmployeeToDatabase (employee newEmployee)
    {
        int newEmployeeID = 0;
        ResultSet rs;
        String sqlStatement = "insert into employee (username, password) ";
        sqlStatement = sqlStatement + "values ('"+newEmployee.username+"', '"+newEmployee.password+"')";
        try 
        {
            stmt.executeUpdate(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            while (rs.next())
            {
                newEmployeeID = rs.getInt(1);  
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return newEmployeeID;
    }

    // Add item to database
    int addItemToDatabase (item newItem)
    {
        int newItemID = 0;
        ResultSet rs;
        String sqlStatement = "insert into item (name, width, depth, height, roomID) ";
        sqlStatement = sqlStatement + "values ('"+newItem.name+"', '"+newItem.width+"', '"+newItem.depth+"', '"+newItem.height+"', '"+newItem.width+"')";
        try 
        {
            stmt.executeUpdate(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            while (rs.next())
            {
                newItemID = rs.getInt(1);  
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return newItemID;
    }


    // Connect to Database
    SQL_Connection()
    {
       connection = connectToDatabase();
    }

    
    
}
