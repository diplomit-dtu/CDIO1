package functionality;

import dto.UserDTO;

import java.util.List;

public interface IFunctionality {
    
    boolean isUserIDPresent(int ID, int[] IDs);
    int[] getUserIDs(List<UserDTO> users);
    boolean verify(UserDTO user, String pass) throws Exception;
    
}
