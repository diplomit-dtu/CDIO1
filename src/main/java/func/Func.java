package func;

import dal.IUserDAO;
import dto.UserDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Func implements IFunc {
    IUserDAO dao;

    public Func(IUserDAO dao){this.dao = dao;}

    @Override
    public UserDTO createUser(int userID, String userName, String cpr, String password, List<String> roles) throws IUserDAO.DALException {
        UserDTO user = new UserDTO();
        user.setUserId(userID);
        user.setUserName(userName);
        user.setUserCpr(cpr);
        user.setPassword(password);
        user.setRoles(roles);
        dao.createUser(user);
        return user;
    }

    @Override
    public List<UserDTO> getUserList() throws IUserDAO.DALException {
        return dao.getUserList();
    }

    @Override
    public UserDTO getUser(int userID) throws IUserDAO.DALException {
        return dao.getUser(userID);
    }

    @Override
    public UserDTO deleteUser(int userID) throws IUserDAO.DALException {
        UserDTO user = getUser(userID);
        dao.deleteUser(userID);
        return user;
    }

    public  String newPassword(){
        int min = 6;
        int max = 50;
        int len =(int) (Math.random()*(max - min)+ min);
        ArrayList<Character> pass = new ArrayList<Character>();

        char[] special = {'.', '-', '_', '+', '!', '?', '='};
        int x=0;
        for(int i = 0; i < len; i++){
            if(x>2)
                x=0;
            if(x == 0)
                pass.add((char) (Math.random()*(90 - 65)+65));
            else if(x == 1)
                pass.add((char) (Math.random()*(122 - 97) + 97));
            else if(x == 2)
                pass.add(special[(int)(Math.random()*(special.length))]);
            x++;
        }
        Collections.shuffle(pass);
        StringBuilder res = new StringBuilder();
        for(int i =0; i<len; i++ ){
            res.append(pass.get(i));
        }
        return res.toString();
    }


    public boolean validPassword(String pass){
        boolean small = false;
        boolean big = false;
        boolean number = false;
        boolean special = false;

        if(6>pass.length() || pass.length() > 50){
            return false;
        }
        for(int i = 0; i < pass.length(); i++){

            char bogstav = pass.charAt(i);

            if(bogstav >= 65 && bogstav <= 90)
                big = true;
            else if(bogstav >= 97 && bogstav <= 122)
                small = true;
            else if( bogstav >= 48 && bogstav <= 57)
                number = true;
            else if(bogstav == '.' || bogstav == '-' || bogstav == '_' || bogstav == '+'|| bogstav == '!' || bogstav == '?'|| bogstav == '=' )
                special = true;
        }
        int count = 0;
        if(small)
            count++;
        if(big)
            count++;
        if(number)
            count ++;
        if(special)
            count++;

        return count >= 3;
    }
}
