package func;

import dal.IUserDAO;
import dto.UserDTO;

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
            System.out.println(test);
            test = Integer.parseInt(cpr.substring(7,11));
            System.out.println(test);
            hyphen = cpr.substring(6,7);
            System.out.println(hyphen);
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
    public List<UserDTO> getUserList() throws IUserDAO.DALException {
        return new ArrayList<>(dao.getUserList());
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

}
