package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;

import javax.jws.soap.SOAPBinding;
import java.sql.*;

public class EventController {


    /* ======================================== FX Fields etc. ======================================== */
    public TextField register_field_name;
    public TextField register_field_surname;
    public TextField register_field_login;
    public TextField register_field_password;
    public TextField register_field_password_r;
    public TextField register_field_email;
    public Button register_button;
    public TextArea register_text_area;
    public Button user_logout_button;
    public TextField login_text_field;
    public TextField login_alert_field;
    public PasswordField password_text_field;
    public CheckBox password_checkbox;
    public Button login_button;
    public Button administrator_logout_button;
    public TextField password_text_field_u;
    @FXML
    private TableView<User> tbl_users;
    @FXML
    private TableColumn<User, Long> col_id;
    @FXML
    private TableColumn<User, String> col_name;
    @FXML
    private TableColumn<User, String> col_surname;
    @FXML
    private TableColumn<User, String> col_login;
    @FXML
    private TableColumn<User, String> col_password;
    @FXML
    private TableColumn<User, String> col_email;
    @FXML
    private TableColumn<User, String> col_date;

    @FXML
    private MenuItem mi_exit;

    /* ======================================== FX Event Methods ======================================== */
    public void register_check_user(ActionEvent event) {
    }

    public void login_check_user(ActionEvent event) {

        String login = login_text_field.getText();
        String password = password_text_field.getText();

        User logginingInUser = searchForExistingUser(login, password);

        if (loginAttempts < 3) {
            if (logginingInUser != null) {

                if (logginingInUser.getPermissions() == User.Permissions.USER){
                    login_alert_field.setPromptText("Successfully logged in. You can now access your data in \"User View\" tab.");
                    loginAttempts = 0;
                }
                else if (logginingInUser.getPermissions() == User.Permissions.ADMINISTRATOR){
                    login_alert_field.setPromptText("Successfully logged in. You can now access your data in \"Administrator View\" tab.");
                    loginAttempts = 0;
                }

            } else {
                login_alert_field.setPromptText("Incorrect user or password! Try again.");
                loginAttempts++;
            }
        }
    }



    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }


    public void showPassword(ActionEvent event) {

        password_text_field_u.managedProperty().bind(password_checkbox.selectedProperty());
        password_text_field_u.visibleProperty().bind(password_checkbox.selectedProperty());
        password_text_field.managedProperty().bind(password_checkbox.selectedProperty().not());
        password_text_field.visibleProperty().bind(password_checkbox.selectedProperty().not());
        password_text_field_u.textProperty().bindBidirectional(password_text_field.textProperty());

    }

    /* ======================================== Other Fields ======================================== */

    private int loginAttempts = 0;

    private enum whoIsLogin {
        USER, ADMINISTRATOR, NONE
    }

    boolean loginUserExists = false;
    boolean registerUserExists = false;

    /* ======================================== Other Methods ======================================== */

    private Connection MySQLConnection() {
        Connection MySQLConnection = null;

        try {
            MySQLConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/psw" +
                            "?useUnicode=true" +
                            "&useJDBCCompliantTimezoneShift=true" +
                            "&useLegacyDatetimeCode=false" +
                            "&serverTimezone=UTC",
                    "root", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return MySQLConnection;
    }

    private User searchForExistingUser(String login, String password) {

        User existingUser = null;

        PreparedStatement findingStm = null;
        try {
            findingStm = MySQLConnection().prepareStatement("SELECT * FROM user WHERE login LIKE ? AND password LIKE ?");
            findingStm.setString(1, login);
            findingStm.setString(2, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        existingUser = doFindingQuery(findingStm);

        return existingUser;
    }


    private User doFindingQuery(PreparedStatement findStm) {
        User tmp_user = null;
        ResultSet userResultFind;

        try {
            userResultFind = findStm.executeQuery();
            if (userResultFind.next()) {
                tmp_user = new User(userResultFind.getLong("id"),
                        userResultFind.getString("name"),
                        userResultFind.getString("surname"),
                        userResultFind.getString("login"),
                        userResultFind.getString("password"),
                        userResultFind.getString("email"));
                String permission = userResultFind.getString("permissions");

                if (permission.matches("administrator")){
                    tmp_user.setPermissions(User.Permissions.ADMINISTRATOR);
                }

            } else {
                throw new RuntimeException("Creating user from result error.");
            }
            MySQLConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp_user;
    }




    /*


    @FXML
    public void findEmpolyeeById(ActionEvent event) {
        String employee_id = employee_id_field.getText();
        Integer empl_id = Integer.parseInt(employee_id);

        Employee tmpEmpl = findOne(empl_id);
        employeeId.add(tmpEmpl);
        addDataToFindEmployee(employeeId);

    }

    @FXML
    public void findEmpolyeeByName(ActionEvent event) {

        String empl_name = employee_name_field.getText();
        Employee tmpEmpl = findByName(empl_name);

        employeeName.add(tmpEmpl);
        addDataToFindEmployee(employeeName);

    }

    @FXML
    public void resetTable(ActionEvent event) {
        SortType sorted = null;

        if (sortByNull.isSelected()) {
            sorted = SortType.sortNull;
        } else if (sortById.isSelected()) {
            sorted = SortType.sortById;
        } else if (sortByName.isSelected()) {
            sorted = SortType.sortByName;
        } else if (sortByEmail.isSelected()) {
            sorted = SortType.sortByEmail;
        } else if (sortBySalary.isSelected()) {
            sorted = SortType.sortBySalary;
        }

        if (sorted == SortType.sortNull && sorted != null) {
            employees.clear();
            findAll();
        } else {
            employeeSort.clear();
            sortTable(sorted);
        }

    }

    @FXML
    public void deleteEmployee(ActionEvent event) {
        String employee_id = employee_delete_field.getText();
        Integer empl_id = Integer.parseInt(employee_id);
        Employee existingEmployeeToDelete = findExistingEmployeeToDelete(empl_id);

        if (existingEmployeeToDelete != null)
            delete(existingEmployeeToDelete);
        else
            employee_info_field_delete.setText("Nie ma pracownika o podanym ID.");

    }

    @FXML
    public void findExistingEmployee(ActionEvent event) {
        String emplIdString = employee_add_field_id.getText();

        long employeeToAddId = Long.parseLong(emplIdString);
        String employeeToAddName = employee_add_field_name.getText();
        String employeeToAddEmail = employee_add_field_email.getText();
        String employeeToAddSalary = employee_add_field_salary.getText();

        save(findExistingEmployeeToAddOrUpdate(employeeToAddId, employeeToAddName, employeeToAddEmail, employeeToAddSalary), updateEmployee);

    }

    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    private ObservableList<Employee> employeeId = FXCollections.observableArrayList();
    private ObservableList<Employee> employeeName = FXCollections.observableArrayList();
    private ObservableList<Employee> employeeSort = FXCollections.observableArrayList();

    boolean updateEmployee = Boolean.parseBoolean(null);


    public enum SortType {
        sortById, sortByName, sortByEmail, sortBySalary, sortNull
    }

    private void addDataToEmployee(ObservableList<Employee> employeesList) {
        addDataToTable(employeesList, col_id, col_name, col_email, col_salary, tbl_employee);

    }

    private void addDataToFindEmployee(ObservableList<Employee> employeesList) {
        addDataToTable(employeesList, col_id_find, col_name_find, col_email_find, col_salary_find, tbl_find_employee);

    }

    private void addDataToTable(ObservableList<Employee> employeesList, TableColumn<Employee, Long> col_id_find, TableColumn<Employee, String> col_name_find, TableColumn<Employee, String> col_email_find, TableColumn<Employee, String> col_salary_find, TableView<Employee> tbl_find_employee) {
        col_id_find.setCellValueFactory(new PropertyValueFactory<Employee, Long>("id"));
        col_name_find.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        col_email_find.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        col_salary_find.setCellValueFactory(new PropertyValueFactory<Employee, String>("salary"));
        tbl_find_employee.setItems(employeesList);
    }


    public void initialize() {
        findAll();

    }


    public Employee findOne(Integer id_emp) {

        Employee find_empl = null;
        employeeId.clear();

        try {
            PreparedStatement prpStm = MySQLConnection().prepareStatement("select * from employee where id = ?");
            prpStm.setLong(1, id_emp);

            find_empl = doFindingQuery(prpStm);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return find_empl;
    }

    public Employee findByName(String name) {
        Employee find_empl = null;
        employeeName.clear();

        try {
            PreparedStatement prpStm = MySQLConnection().prepareStatement("select * from employee where name LIKE ?");
            prpStm.setString(1, name);

            find_empl = doFindingQuery(prpStm);

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return find_empl;
    }

    public void findAll() {
        try {
            Statement myStatement = MySQLConnection().createStatement();
            ResultSet myResultSet = myStatement.executeQuery("select * from employee");

            while (myResultSet.next()) {
                Employee e = new Employee((long) myResultSet.getInt("id"),
                        myResultSet.getString("name"),
                        myResultSet.getString("email"),
                        myResultSet.getString("salary"));
                employees.add(e);
                addDataToEmployee(employees);

            }
            MySQLConnection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Employee findExistingEmployeeToDelete(Integer employeeID) {

        Employee tmpEmployee;

        PreparedStatement prpStm = null;
        try {
            prpStm = MySQLConnection().prepareStatement("select * from employee where id = ?");
            prpStm.setLong(1, employeeID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tmpEmployee = doFindingQuery(prpStm);

        if (tmpEmployee != null)
            return tmpEmployee;
        else
            return null;

    }

    public Employee findExistingEmployeeToAddOrUpdate(long id, String name, String email, String salary) {

        Employee employeeToFind = new Employee(id, name, email, salary);

        isThereEmployeeToUpdate(employeeToFind);

        return employeeToFind;
    }

    private void isThereEmployeeToUpdate(Employee employeeToFind) {
        for (Employee emp : employees) {

            if (emp.getId() == employeeToFind.getId()) {
                updateEmployee = true;
                break;
            } else
                updateEmployee = false;
        }
    }

    public void delete(Employee employee) {

        try {

            String Query = "DELETE FROM employee WHERE id = " + employee.getId();

            Statement Stm = MySQLConnection().createStatement();
            Stm.executeUpdate(Query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        employees.clear();
        findAll();

    }

    public void save(Employee employee, boolean updateEmployee) {

        try {
            if (updateEmployee) {

                String Query = "UPDATE employee " +
                        "SET " +
                        "name = '" + employee.getName() + "', " +
                        "email = '" + employee.getEmail() + "', " +
                        "salary = " + employee.getSalary() + " " +
                        "WHERE id = " + employee.getId();
                System.out.println(Query);

                Statement Stm = MySQLConnection().createStatement();
                Stm.executeUpdate(Query);
                employees.clear();
                findAll();

            } else {

                String Query = "INSERT INTO employee " +
                        "(id, " +
                        "name, " +
                        "email, " +
                        "salary) " +
                        "VALUES " +
                        "(" + employee.getId() + ", " +
                        "'" + employee.getName() + "', " +
                        "'" + employee.getEmail() + "', " +
                        employee.getSalary() + ");";

                Statement Stm = MySQLConnection().createStatement();
                Stm.executeUpdate(Query);

                employees.clear();
                findAll();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void sortTable(SortType sorted) {

        Statement myStatement;
        ResultSet myResultSet;

        try {
            switch (sorted) {
                case sortById:
                    myStatement = MySQLConnection().createStatement();
                    myResultSet = myStatement.executeQuery("select * from employee order by id DESC");
                    employeeSortList(myResultSet);
                    MySQLConnection().close();
                    break;
                case sortByName:
                    myStatement = MySQLConnection().createStatement();
                    myResultSet = myStatement.executeQuery("select * from employee order by name DESC");
                    employeeSortList(myResultSet);
                    MySQLConnection().close();
                    break;
                case sortByEmail:
                    myStatement = MySQLConnection().createStatement();
                    myResultSet = myStatement.executeQuery("select * from employee order by email DESC");
                    employeeSortList(myResultSet);
                    MySQLConnection().close();
                    break;
                case sortBySalary:
                    myStatement = MySQLConnection().createStatement();
                    myResultSet = myStatement.executeQuery("select * from employee order by salary DESC");
                    employeeSortList(myResultSet);
                    MySQLConnection().close();
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void employeeSortList(ResultSet myResultSet) throws SQLException {
        while (myResultSet.next()) {
            Employee e = new Employee((long) myResultSet.getInt("id"),
                    myResultSet.getString("name"),
                    myResultSet.getString("email"),
                    myResultSet.getString("salary"));
            employeeSort.add(e);
            addDataToEmployee(employeeSort);
        }
    }

     */
}
