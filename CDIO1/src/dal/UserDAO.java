package dal;

import dto.UserDTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UserDAO implements IUserDAO {
    @Override
    public UserDTO getUser(int userId) throws DALException{
        return null;
    }
    @Override
    public List<UserDTO> getUserList() throws DALException{
        return null;
    }
    @Override
    public void createUser(UserDTO user) throws DALException{
        //Opretter en fil med User'id'et som navn
        try{
            String path = "CDIO1/src/Services/users/";
            File file = new File(path + user.getUserId()+".txt");
            if (file.createNewFile()){
                System.out.println("File created" + file.getName());
            }
        }
        catch(IOException e){ //Todo skal muligvis ændres til en DALException når vi får lavet den - Lasse
            e.printStackTrace();
        }
        try {
            String path = "CDIO1/src/Services/users/";
            FileWriter file = new FileWriter(path + user.getUserId() + ".txt");
            file.write("geder");
            file.close();
        } catch(IOException e){
            System.out.println("dit fuck-up er at det er fucked");
            e.printStackTrace();
        }

    }
    @Override
    public void updateUser(UserDTO user) throws DALException{

    }
    @Override
    public void deleteUser(int userId) throws DALException{

    }
}
