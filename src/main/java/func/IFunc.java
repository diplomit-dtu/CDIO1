package func;

import dal.IUserDAO;
import dto.UserDTO;
import java.util.List;

public interface IFunc{
    // This method will generate the initials and password based on the given data. Returns a deep-copy of the resulting user.
    //TODO: Remove password here, as it is generated, make proper password generation
    UserDTO createUser(int userID, String userName, String cpr, List<String> roles) throws UserFormatException;
    // Returns a deep-copy of the list of users, as to not being able to modify it.
    List<UserDTO> getUserList() throws IUserDAO.DALException;
    // Returns a deep-copy of the user with the specific id.
    UserDTO getUser(int userID) throws IUserDAO.DALException;
    // Deletes the user with specified id. Returns a deep-copy of the user.
    UserDTO deleteUser(int userID) throws IUserDAO.DALException;

    public static class UserFormatException extends Exception{
        public List<errortypes> errorlist;
        UserFormatException(String message, List<errortypes> errorlist){
            super(message);
            this.errorlist = errorlist;
        }
        public enum errortypes{
            ID,
            username,
            CPR,
            roles,
            password
        }
    }
}
