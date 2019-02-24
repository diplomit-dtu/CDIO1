package dal;

import dto.UserDTO;

import java.sql.*;
import java.util.*;

public class UserDAOImpls132962 implements IUserDAO {
    private String databaseURL = "jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s132962";
    private String userName = "s132962";
    private String databasePassword = "aigJQGPdFIGulY8iALPsu";
    private String[] roleTitles = {"admin", "pharmacist", "foreman", "operator"};

    public UserDTO getUser(int id) throws DALException {
        UserDTO tempUser = null;
        List<String> roles = new ArrayList<>();
        String query = "SELECT * FROM user WHERE user_id = " + id + ";";

        try (Connection connection = DriverManager.getConnection(databaseURL, userName, databasePassword)) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                for (int i = 0; i < roleTitles.length; i++) {
                    if (result.getString(i + 6).equals("true")) {
                        roles.add(roleTitles[i]);
                    }
                }
                tempUser = makeUserDTO(result.getInt(1), result.getString(2), result.getString(4), roles);
            }
        } catch (SQLException s) {
            throw new DALException("Could not get user " + id + ".", s);
        }
        return tempUser;
    }

    public List<UserDTO> getUserList() throws DALException {
        UserDTO tempUser;
        List<UserDTO> tempUsers = new ArrayList<>();
        String query = "SELECT * FROM user";
        try (Connection connection = DriverManager.getConnection(databaseURL, userName, databasePassword)) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                List<String> roles = new ArrayList<>();

                for (int i = 0; i < roleTitles.length; i++) {
                    if (result.getString(i + 6).equals("true")) {
                        roles.add(roleTitles[i]);
                    }
                }
                tempUser = makeUserDTO(result.getInt(1), result.getString(2), result.getString(4), roles);
                tempUsers.add(tempUser);
            }
        } catch (SQLException s) {
            throw new DALException("Could not get list of users.", s);
        }
        return tempUsers;
    }

    public void createUser(UserDTO user) throws DALException {
        if (user.getUserId() < 11 || user.getUserId() > 99) {
            throw new DALException("Can't make user. Valid ID's are between 11 and 99");
        }

        String[] roleBooleans = new String[4];
        for (int i = 0; i < roleTitles.length; i++) {
            if (user.getRoles().contains(roleTitles[i])) {
                roleBooleans[i] = "true";
            } else {
                roleBooleans[i] = "false";
            }
        }

        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String password = passwordGenerator.getPassword();
        password = password.replace("'", "''");

        String update = "INSERT INTO user(user_id, user_name, user_password, initials, cpr, admin, pharmacist, " +
                "foreman, operator) VALUES(" + user.getUserId() + ", '" + user.getUserName() + "', '" + password +
                "', '" + user.getIni() + "', 'no_cpr', '" + roleBooleans[0] + "', '" + roleBooleans[1] + "', '" +
                roleBooleans[2] + "', '" + roleBooleans[3] + "');";

        try (Connection connection = DriverManager.getConnection(databaseURL, userName, databasePassword)) {

            Statement statement = connection.createStatement();
            statement.executeUpdate(update);

        } catch (SQLException s) {
            throw new DALException("Could not create user " + user.getUserId() + ".", s);
        }

    }

    public void updateUser(UserDTO user) throws IUserDAO.DALException {
        if (user.getUserId() < 11 || user.getUserId() > 99) {
            throw new DALException("Can't make user. Valid ID's are between 11 and 99");
        }

        String[] roleBooleans = new String[4];
        for (int i = 0; i < roleTitles.length; i++) {
            if (user.getRoles().contains(roleTitles[i])) {
                roleBooleans[i] = "true";
            } else {
                roleBooleans[i] = "false";
            }
        }

        String update = "UPDATE user SET user_name = '" + user.getUserName() + "', initials = '" + user.getIni() +
                "', cpr = 'no_cpr', admin = '" + roleBooleans[0] + "', pharmacist = '" + roleBooleans[1] +
                "', foreman = '" + roleBooleans[2] + "', operator = '" + roleBooleans[3] +
                "' WHERE user_id = " + user.getUserId() + ";";

        try (Connection connection = DriverManager.getConnection(databaseURL, userName, databasePassword)) {

            Statement statement = connection.createStatement();
            statement.executeUpdate(update);

        } catch (SQLException s) {
            throw new DALException("Could not update user " + user.getUserId() + ".", s);
        }
    }

    public void deleteUser(int id) throws IUserDAO.DALException {
        if (id < 11 || id > 99) {
            throw new DALException("Can't make user. Valid ID's are between 11 and 99");
        }

        String update = "DELETE FROM user WHERE user_id = " + id + ";";

        try (Connection connection = DriverManager.getConnection(databaseURL, userName, databasePassword)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(update);
        } catch (SQLException s) {
            throw new DALException("Could not create user " + id + ".", s);
        }
    }

    private UserDTO makeUserDTO(int id, String name, String initials, List<String> roles) {
        // TODO: add CPR
        UserDTO tempUser = new UserDTO();
        tempUser.setUserId(id);
        tempUser.setUserName(name);
        tempUser.setIni(initials);
        tempUser.setRoles(roles);
        return tempUser;
    }

    private class PasswordGenerator {
        // This class generates a random databasePassword with capitals, lower case letters,
        // digits and the symbols . - _ ! ? ' , =
        private Random random = new Random();

        // Array of all valid symbols
        private Character[] validSymbols = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '.', '-', '_', '+', '!', '?', '\'', ',', '='};

        private String generate() {

            ArrayList<Character> passwordCharacters = new ArrayList<>();
            StringBuilder stringBuilder = new StringBuilder();

            // Set random length: 6 to 50.
            int passwordLength = (random.nextInt(45) + 6); // Bound not inclusive

            // Add at least one of the following:
            passwordCharacters.add(validSymbols[random.nextInt(26)]); // capitals
            passwordCharacters.add(validSymbols[random.nextInt(26) + 26]); // lower case
            passwordCharacters.add(validSymbols[random.nextInt(10) + 52]); // digits
            passwordCharacters.add(validSymbols[random.nextInt(9) + 62]); // special symbols

            // Make rest of the databasePassword:
            for (int i = 4; i < passwordLength; i++) {
                passwordCharacters.add(validSymbols[random.nextInt(71)]);
            }

            // Randomize order of characters
            Collections.shuffle(passwordCharacters);

            // Build a string
            for (int i = 0; i < passwordLength; i++) {
                stringBuilder.append(passwordCharacters.get(i));
            }

            return stringBuilder.toString();
        }

        String getPassword() {
            return generate();
        }
    }
}





