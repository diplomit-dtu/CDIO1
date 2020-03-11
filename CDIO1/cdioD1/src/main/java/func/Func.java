package func;

import dto.UserDTO;

import java.util.List;

public class Func implements IFunc {
    @Override
    public UserDTO createUser(int userID, String userName, String cpr, String password, List<String> roles) {
        UserDTO user = new UserDTO();
        user.setUserId(userID);
        user.setUserName(userName);
        user.setUserCpr(cpr);
        user.setPassword(password);
        user.setRoles(roles);
        return user;
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
