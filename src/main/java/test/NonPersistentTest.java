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
            UserDTO user1 = new UserDTO(-1, "Name", "Na", "123456-1234", "password", "Admin");
            NonPersistent.createUser(user1);
            message = "User Created.";
        } catch (DALException E) {
            message = E.getMessage();
        }
        assertEquals(message, "BrugerID skal være mellem 11 og 99 (inklusivt)");

        // When creating a user with a valid ID, i get no exception.

        try{
            UserDTO user1 = new UserDTO(20, "Name", "Na", "123456-1234", "password", "Admin");
            NonPersistent.createUser(user1);
            message = "User Created.";
        } catch (DALException E) {
            message = E.getMessage();
        }
        assertEquals(message, "User Created.");

        // When creating a user with the same ID as another one, i get an exception.

        try{
            UserDTO user1 = new UserDTO(20, "Name", "Na", "123456-1234", "password", "Admin");
            NonPersistent.createUser(user1);
            message = "User Created.";
        } catch (DALException E) {
            message = E.getMessage();
        }
        assertEquals(message, "Der findes allerede en bruger med ID 20");

    }

    @Test
    public void testGetUser(){
        //setup
        UserDAONonPersistent NonPersistent = new UserDAONonPersistent();
        try {
            UserDTO user1 = new UserDTO(20, "Name", "Na", "123456-1234", "password", "Admin");
            NonPersistent.createUser(user1);
        } catch (DALException E){

        }

        UserDTO user;
        String message;
        //When trying to get a user that exists, i get the user.

        try {
            user = NonPersistent.getUser(20);
        } catch (DALException E){
            message = E.getMessage();
        }
        assertFalse(user == null);

        //When trying to get a user that doesn't exist, i get an exception.
        try {
            user = NonPersistent.getUser(-1);
        } catch (DALException E){
            message = E.getMessage();
        }
        assertEquals(message, "Der findes ingen bruger med ID -1")

    }

    @Test
    public void testGetUserList(){
        //setup (When creating an object of UserDAONonPersistent, 3 dummy users are initialized automatically)
        UserDAONonPersistent NonPersistent = new UserDAONonPersistent();
        try {
            UserDTO user1 = new UserDTO(20, "Name", "Na", "123456-1234", "password", "Admin");
            NonPersistent.createUser(user1);
        } catch (DALException E){

        }

        // When getting the user list, i get a list with one user, that has ID = 20.

        List<UserDTO> users = NonPersistent.getUserList();

        assertTrue(users.size() == 4);
        assertTrue(users.get(3).getUserId() == 20);

    }

    @Test
    public void testUpdateUser(){
        //setup
        UserDAONonPersistent NonPersistent = new UserDAONonPersistent();
        try {
            UserDTO user1 = new UserDTO(20, "Name", "Na", "123456-1234", "password", "Admin");
            NonPersistent.createUser(user1);
        } catch (DALException E){

        }

        String message;

        // When trying to update a user that doesn't exist, i get an exception

        try {
            UserDTO user2 = new UserDTO(-1, "Name", "Na", "123456-1234", "password", "Admin");
            NonPersistent.updateUser(user2);
        } catch (DALException E){
            message = E.getMessage();
        }
        assertEquals(message, "Brugeren du prøvede at opdatere fandtes ikke");

        // When trying to update a user that exists, it gets updated
        message = "";
        try {
            UserDTO user2 = new UserDTO(20, "UpdatedUser", "Na", "123456-1234", "password", "Admin");
            NonPersistent.updateUser(user2);
        } catch (DALException E){
            message = E.getMessage();
        }
        assertEquals(message, "");
        assertEquals(NonPersistent.getUser(20).getUserName(), "UpdatedUser");

    }

    @Test
    public void testDeleteUser(){
        //setup
        UserDAONonPersistent NonPersistent = new UserDAONonPersistent();
        try {
            UserDTO user1 = new UserDTO(20, "Name", "Na", "123456-1234", "password", "Admin");
            NonPersistent.createUser(user1);
        } catch (DALException E){

        }

        String message;

        //When deleting a user, it is removed from the user-list.
        try {
            NonPersistent.deleteUser(20);
        } catch (DALException E){
            message = E.getMessage();
        }

        assertTrue(message == null);
        assertTrue(NonPersistent.getUserList().size() == 3);

        //When trying to delete a user that doesn't exist, i get an exception.

        try {
            NonPersistent.deleteUser(-1);
        } catch (DALException E){
            message = E.getMessage();
        }

        assertEquals("Der fandtes ingen bruger med ID -1");

    }
}
