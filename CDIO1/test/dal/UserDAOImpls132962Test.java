package dal;

import dto.UserDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserDAOImpls132962Test {


    @Test
    public void getUser() {
        UserDAOImpls132962 testDAO = new UserDAOImpls132962();
        UserDTO testUser = null;
        try{
            testUser = testDAO.getUser(11);
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println(testUser.getUserId() + ", " +
                testUser.getUserName() + ", " +
                testUser.getIni() + ", " +
                testUser.getRoles() + ".");

        assertEquals("Bing Crosby", testUser.getUserName());
    }


    @Test
    public void getUserList() {
        UserDAOImpls132962 testDAO = new UserDAOImpls132962();
        List<UserDTO> testUsers = new ArrayList<>();
        try{
            testUsers = testDAO.getUserList();
        } catch (Exception e){
            System.out.println(e);
        }
        for (UserDTO user : testUsers){
            System.out.println(user.getUserId() + ", " +
                    user.getUserName() + ", " +
                    user.getIni() + ", " +
                    user.getRoles() + ".");
        }
        assertEquals("[foreman, operator]", testUsers.get(1).getRoles().toString());
    }

    @Test
    public void createUser() {
        UserDAOImpls132962 testDAO = new UserDAOImpls132962();
        UserDTO testUser = new UserDTO();
        testUser.setUserId(14);
        testUser.setUserName("Kim Larsen");
        testUser.setIni("KiLa");
        List<String> roles = new ArrayList<>();
        roles.add("operator");
        testUser.setRoles(roles);

        try{
            testDAO.createUser(testUser);
            assertEquals("Kim Larsen", testDAO.getUser(14).getUserName());

            System.out.println(testDAO.getUser(14).getUserName());
        } catch (Exception e){
            System.out.println(e);
        }

    }

    @Test
    public void updateUser() {
        UserDAOImpls132962 testDAO = new UserDAOImpls132962();
        UserDTO testUser = new UserDTO();
        testUser.setUserId(14);
        testUser.setUserName("Kim Larsen");
        testUser.setIni("KiLa");
        List<String> roles = new ArrayList<>();
        roles.add("administrator");
        testUser.setRoles(roles);
        try{
            testDAO.updateUser(testUser);
        } catch (Exception e){
            System.out.println(e);
        }
        assertEquals(roles, testUser.getRoles());
    }

    @Test
    public void deleteUser() {
        UserDAOImpls132962 testDAO = new UserDAOImpls132962();
        try{
            testDAO.deleteUser(14);
            assert("Kim Larsen".compareTo(testDAO.getUser(13).getUserName()) != 0);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}