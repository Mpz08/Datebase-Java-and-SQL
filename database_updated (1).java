

class roomDimensions
{
    int width = 0;
    int depth = 0;
    int height = 0;
    public void setDimensions(int[] dimensions)
    {
        width = dimensions[0];
        depth = dimensions[1];
        height = dimensions[2];
    }
}

class item
{
    int itemID = 0;
    String name = "";
    int width = 0;
    int depth = 0;
    int height = 0;
    int roomID = 0;

    public void setItemInformation(int id, String itemName, int[] dimensions, int rmID)
    {
        itemID = id;
        name = itemName;
        width = dimensions[0];
        depth = dimensions[1];
        height = dimensions[2];
        roomID = rmID;
    }
}

class customer
{
    int customerID = 0;
    String firstName = "";
    String lastName = "";
    String phoneNumber = "";
    String email = "";

    public void setCustomerInformation(int id, String[] info)
    {
        customerID = id;
        firstName = info[0];
        lastName = info[1];
        phoneNumber = info[2];
        email = info[3];
    }
}

class employee
{
    int id = 0;
    String username = "";
    String password = "";
}

public class database 
{

    private SQL_Connection sql_connection;

    //////////////////////////////////////////////////////////////////////////////////////////////
    ////////  Private internal functions  ////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    // This function returns all of the customer information
    // First Name, Last Name, Phone Number, and Email
    private String[] getCustomerInfo(int customerID)
    {
        String[] customerInfo = sql_connection.getCustomerInfo(customerID);
       
        return customerInfo;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////


    //////////////////////////////////////////////////////////////////////////////////////////////
    ////////  Public external functions  /////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    // This function returns all of the customer information
    // Input: customer id (integer)
    // Output: First Name, Last Name, Phone Number, and Email (String Array 4 elements)
    public customer getCustomerInformation (int customerID)
    {
            customer customerInfromation = new customer();
            customerInfromation.setCustomerInformation(customerID, getCustomerInfo(customerID));
            return customerInfromation;   
    }

    // This function returns the room dimensions
    // Input: room size (integer)
    // Output: Class roomDimensions: width, depth, height (3 integers)
    public roomDimensions getRoomDimensions(int roomSize)
    {
        roomDimensions rmDimensions = new roomDimensions();
        rmDimensions.setDimensions(sql_connection.getEmptyRoomDimensions(roomSize));
        return rmDimensions;
    }
    
    // This function returns the items of a room
    // Input: room number (integer)
    // Output: Class item: id, name, width, depth, height, roomID, customerID (1 integer, 1 string, 5 integers)
    public item[] getRoomItems(int roomNumber)
    {
        item[] items = sql_connection.getRoomItems(roomNumber);
        return items;
    }

    // This function returns an employees username and password
    // Input: employee id (integer)
    // Output: Class employee: id, username, password (1 integer and 2 strings)
    public employee getEmployeeInfo(int employeeID)
    {
        employee anEmployee = new employee();
        String[] employeeInfo = {"", ""};
        employeeInfo = sql_connection.getEmployeeInformation(employeeID);
        anEmployee.id = employeeID;
        anEmployee.username = employeeInfo[0];
        anEmployee.password = employeeInfo[1];
        return anEmployee;
    }

    // This function adds a customer to the database, if succesful it will return the new customer id
    // Inputs: first_name, last_name, phonenumber, email (4 strings)
    // Outputs: customer id (integer)
    public int addCustomerToDatabase(String firstName, String lastName, String phoneNumber, String email)
    {
        int customerID = 0;
        customer newCustomer = new customer();
        newCustomer.firstName = firstName;
        newCustomer.lastName = lastName;
        newCustomer.phoneNumber = phoneNumber;
        newCustomer.email = email;
        customerID = sql_connection.addCustomerToDatabase(newCustomer);
        return customerID;
    }

    // This function adds a employee to the database, if succesful it will return the new employee id
    // Inputs: username, password (2 strings)
    // Outputs: employee id (integer)
    public int addEmployeeToDatabase(String username, String password)
    {
        int employeeID = 0;
        employee newEmployee = new employee();
        newEmployee.username = username;
        newEmployee.password = password;
        employeeID = sql_connection.addEmployeeToDatabase(newEmployee);
        return employeeID;
    }

    // This function adds an item to the database, if succesful it will return the new item id
    // Inputs: name, width, depth, and height (1 string and 3 integers)
    // Outputs: item id (integer)
    public int addItemToDatabase(String itemName, int itemWidth, int itemDepth, int itemHeight, int roomNumber)
    {
        int itemID = 0;
        item newItem = new item();
        newItem.name = itemName;
        newItem.width = itemWidth;
        newItem.depth = itemDepth;
        newItem.height = itemHeight;
        newItem.roomID = roomNumber;
        itemID = sql_connection.addItemToDatabase(newItem);
        return itemID;
    }
/**
     * Checks for available rooms that are not assigned to any customer.
     * @return a list of available room IDs.
     */
    public List<Integer> checkAvailableRooms() {
        List<Integer> availableRooms = new ArrayList<>();
        String query = "SELECT roomID FROM rooms WHERE assignedToCustomer = 0";

        try {
            ResultSet resultSet = sql_connection.executeQuery(query);
            while (resultSet.next()) {
                availableRooms.add(resultSet.getInt("roomID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableRooms;
    }
 /**
     * Assigns a customer to a room.
     * @param customerID the ID of the customer.
     * @param roomID the ID of the room to be assigned.
     * @return true if the assignment is successful, false otherwise.
     */
    public boolean assignCustomerToRoom(int customerID, int roomID) {
        String query = "UPDATE rooms SET assignedToCustomer = ?, customerID = ? WHERE roomID = ? AND assignedToCustomer = 0";
        try {
            PreparedStatement preparedStatement = sql_connection.prepareStatement(query);
            preparedStatement.setInt(1, 1); // setting assignedToCustomer to 1
            preparedStatement.setInt(2, customerID);
            preparedStatement.setInt(3, roomID);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    // Create the database
    database()
    {
        sql_connection = new SQL_Connection();
    }
}
