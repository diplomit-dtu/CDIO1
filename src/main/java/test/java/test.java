package test.java;

import org.junit.Test;
import static org.junit.Assert.*;
import java.dal.UserDAONonPersistent;

public class test {

    NonPersistentTest nonPersistentTest = new NonPersistentTest();
    SQLTest sqlTest = new SQLTest();

}

public class NonPersistentTest {

    public NonPersistentTest(){

    }

    @Test
    public void testCreateUser(){
        // When creating a user with an invalid ID, i get an exception.
        UserDAONonPersistent NonPersistent = new UserDAONonPersistent();
        String message = "";
        try{
            UserDTO user1 = new UserDTO(int -1, String "Name", String "Na", String "123456-1234", String "password", String "Admin");
            NonPersistent.createUser(user1);
        } catch (DALException E) {
            message = E.getMessage();
        }
        assertTrue(message.equals("BrugerID skal være mellem 11 og 99 (inklusivt)"))


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

public class SQLTest{

    public SQLTest(){

    }


    public void testCreateUser(){

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