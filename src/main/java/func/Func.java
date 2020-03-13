package func;

import dal.IUserDAO;
import dto.UserDTO;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Func implements IFunc {
    IUserDAO dao;

    public Func(IUserDAO dao){this.dao = dao;}

    @Override
    public UserDTO createUser(int userID, String userName, String cpr, List<String> roles) throws UserFormatException, DatabaseException{
        List<UserFormatException.errortypes> errorlist = new ArrayList<>();
        UserDTO user = new UserDTO();
        // Check ID
        if(!(11<=userID && userID<=99)){
           errorlist.add(UserFormatException.errortypes.ID);
        }

        // Check username
        if(!(2<=userName.length() && userName.length()<=20)){
            errorlist.add(UserFormatException.errortypes.username);
        }

        // Check CPR
        boolean isInteger=true;
        String hyphen = "";
        try{
            int test = Integer.parseInt(cpr.substring(0,6));
            test = Integer.parseInt(cpr.substring(7,11));
            hyphen = cpr.substring(6,7);
        }catch(NumberFormatException | StringIndexOutOfBoundsException e){
            isInteger=false;
            hyphen = "";
        }
        if(!isInteger || !hyphen.equals("-")){
            errorlist.add(UserFormatException.errortypes.CPR);
        }
        // Check roles
        List<String> cpyRoles = new ArrayList<>(roles);
        cpyRoles.remove("Administrator");
        cpyRoles.remove("Formand");
        cpyRoles.remove("Farmaceut");
        cpyRoles.remove("Operatør");
        if(cpyRoles.size()!=0){
            errorlist.add(UserFormatException.errortypes.roles);
        }
        //TODO: Check password

        if(errorlist.size() > 0) {
            throw new UserFormatException("One or more user parameters are not correctly formatted",errorlist);
        }

        user.setUserId(userID);
        user.setUserName(userName);
        user.setUserCpr(cpr);
        user.setRoles(roles);
        try{
            dao.createUser(user);
        }catch(IUserDAO.DALException e){
            //TODO: make better, furthermore, is the message correct Silas? Sincerely christoffer
            throw new DatabaseException("Either there is no database, or the user you are trying to create already exists");
        }
        return user;
    }

    @Override
    public List<UserDTO> getUserList() throws DatabaseException{
        try {
            return new ArrayList<>(dao.getUserList());
        }catch (IUserDAO.DALException e){
            throw new DatabaseException("Unable to get user list from database.");
        }
    }

    @Override
    public UserDTO getUser(int userID) throws DatabaseException {
        try {
            return dao.getUser(userID);
        }catch (IUserDAO.DALException e){
            throw new DatabaseException("Unable to get user with ID: " + userID + " from database.");
        }
    }

    @Override
    public UserDTO deleteUser(int userID) throws DatabaseException {
        UserDTO user = getUser(userID);
        try {
            dao.deleteUser(userID);
            return user;
        }catch(IUserDAO.DALException e){
            throw new DatabaseException("Unable to delete user with ID: " + userID + " from database.");
        }
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
