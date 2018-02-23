package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import data.IUserDAO;

public class UserDBDAO implements IUserDAO{

	@Override
	public UserDTO getUser(int userId) throws DALException {		
		ResultSet rs = Connector.doQuery("SELECT * FROM personer WHERE userID = " + userId);
	    try {
	    	if (!rs.first()) throw new DALException("Personen med ID: " + userId + " findes ikke");
	    	return new UserDTO (rs.getInt("userID"), rs.getString("userName"), rs.getString("ini"), rs.getString("roles"), rs.getString("cpr"), rs.getString("passwd"));
	    }
	    catch (SQLException e) {throw new DALException(e.getMessage(), e); }
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		// TODO Auto-generated method stub
		List<UserDTO> list = new ArrayList<UserDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM personer");
		try
		{
			while (rs.next()) 
			{
				list.add(new UserDTO(rs.getInt("userID"), rs.getString("userName"), rs.getString("ini"), rs.getString("roles"), rs.getString("cpr"), rs.getString("passwd")));
			}
		}
		catch (SQLException e) { throw new DALException(e.getMessage(), e); }
		return list;
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		// TODO Auto-generated method stub
		conn.doUpdate(	//NEEDS TO BE SETUP------------------------------------------------------------------------
		"INSERT INTO personer(userID, userName, ini, roles, cpr, passwd) VALUES " + 
		"(" + user.getUserId() + ", '" + user.getUserName() + "', '" + user.getIni() + 
		"', '" + user.getRoles() + "', '" + user.getCpr() + "', '" + user.getPassword() + "');"
		);
		
		
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		// TODO Auto-generated method stub
		conn.doUpdate(	//NEEDS TO BE SETUP------------------------------------------------------------------------
				"UPDATE personer SET " + 
				 "userID = '" + user.getUserId() + "', userName = '" + user.getUserName() + "', ini = '" + user.getIni() + "', " + 
				"roles = '" + user.getRoles() + "', cpr ='" + user.getCpr() + "', passwd = '" + user.getPassword() + "'" + 
				"WHERE userID = " + user.getUserId() + ";"
				);
	}

	@Override
	public void deleteUser(int userId) throws DALException {
		// TODO Auto-generated method stub
		conn.doUpdate(	//NEEDS TO BE SETUP------------------------------------------------------------------------
				"DELETE FROM personer WHERE userID = " + userId + ";"
				);
	}
}