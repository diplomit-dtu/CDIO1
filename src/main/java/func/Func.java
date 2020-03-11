package func;

import dal.IUserDAO;
import dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class Func implements IFunc {
    IUserDAO dao;

    public Func(IUserDAO dao){this.dao = dao;}

    @Override
    public UserDTO createUser(int userID, String userName, String cpr, List<String> roles) throws UserFormatException {
        List<UserFormatException.errortypes> errorlist = new ArrayList<>();
        UserDTO user = new UserDTO();
        // Check ID
        if(!(11<=userID && userID<=99)){
           errorlist.add(UserFormatException.errortypes.ID);
        }

        // Check username
        if(2<=userName.length() && userName.length()<=20){
            errorlist.add(UserFormatException.errortypes.username);
        }

        // Check CPR
        boolean isInteger=true;
        String hyphen = "";
        try{
            int test = Integer.parseInt(cpr.substring(0,6));
            test = Integer.parseInt(cpr.substring(8,12));
            hyphen = cpr.substring(6,7);
        }catch(NumberFormatException | StringIndexOutOfBoundsException e){
            isInteger=false;
            hyphen = "";
        }
        if(!(isInteger && hyphen == "-")){
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
            throw new UserFormatException("One or more user paramaters are not correctly formatted",errorlist);
        }
        user.setUserId(userID);
        user.setUserName(userName);
        user.setUserCpr(cpr);
        user.setRoles(roles);

        try {
            dao.createUser(user);
        }catch(IUserDAO.DALException e){
            //TODO: make better
            System.out.println("WRONGI!!!");
        }
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

}
