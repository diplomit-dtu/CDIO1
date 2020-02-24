package Data;

import dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class ArraylistnonPersistens {
    List<UserDTO> users;
    public ArraylistnonPersistens(){
        users = new ArrayList<>();

    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
