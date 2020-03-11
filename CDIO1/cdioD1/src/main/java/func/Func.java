package func;

import dto.UserDTO;

import java.util.List;

public class Func implements IFunc {
    @Override
    public UserDTO createUser(int userID, String userName, String cpr, String password, List<String> roles) {
        return null;
    }

    @Override
    public List<UserDTO> getUserList() {
        return null;
    }

    @Override
    public UserDTO getUser(int userID) {
        return null;
    }

    @Override
    public UserDTO deleteUser(int userID) {
        return null;
    }
}
