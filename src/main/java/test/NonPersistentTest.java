package test;

import dal.IUserDAO;
import dal.UserDAONonPersistent;
import dto.UserDTO;
import org.junit.jupiter.api.Test;

public class NonPersistentTest {

    public NonPersistentTest(){

    }

    @Test
    public void testCreateUser(){
        // When creating a user with an invalid ID, i get an exception.
        UserDAONonPersistent NonPersistent = new UserDAONonPersistent();
        String message = "";
        try{
            UserDTO user1 = new UserDTO(-1,  "Name",  "Na",  "123456-1234",  "password",  "Admin");
            NonPersistent.createUser(user1);
        } catch (IUserDAO.DALException E) {
            message = E.getMessage();
        }
        assert(message.equals("BrugerID skal være mellem 11 og 99 (inklusivt)"));


    }

    public void testGetUser(){

    }

    public void testGetUserList(){

    }

    public void testUpdateUser(){

    }

    public void testDeleteUser(){

    }
}
