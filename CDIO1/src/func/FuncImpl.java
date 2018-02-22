package func;

import java.util.List;
import java.security.SecureRandom;

import data.IUserDAO;
import data.IUserDAO.DALException;
import data.UserDTO;

public class FuncImpl implements IFuncImpl{
	private static SecureRandom random = new SecureRandom();

	/** different dictionaries used */
	private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String NUMERIC = "0123456789";
	private static final String SPECIAL = ".-_+!?=";


	IUserDAO d;

	public FuncImpl(IUserDAO d)
	{
		this.d = d; 
	}

	@Override
	public UserDTO getUser(int userId) throws DALException {
		UserDTO user = d.getUser(userId);
		return user;
	}

	@Override
	public List<UserDTO> getUserList() {
		return null;
	}

	@Override
	public void createUser(String userName, String ini, String roles1, String cpr) {
		int	userId;
		String password = createPass(6, UPPER + LOWER + SPECIAL + NUMERIC);
	}

	@Override
	public void updateUser(int userId, String userName, String ini, String roles, String cpr) throws DALException {
		UserDTO user = getUser(userId);
		if(user != null)
		{

		}
	}

	@Override
	public void deleteUser(int userId) throws DALException {
		d.deleteUser(userId);
	}

	@Override
	public void exit() {

	}

	@Override

	public String createPass(int len, String dic) {
		/**
		 * Method will generate random string based on the parameters
		 * 
		 * @param len
		 *            the length of the random string
		 * @param dic
		 *            the dictionary used to generate the password
		 * @return the random password
		 */
		String result = "";
		for (int i = 0; i < len; i++) {
			int index = random.nextInt(dic.length());
			result += dic.charAt(index);
		}
		//				int len1 = 10;
		//				System.out.println("Alphanumeric, special, length " + len + " chars: ");
		//				String password = createPass(len1, ALPHA_CAPS + ALPHA + SPECIAL_CHARS);
		//				System.out.println(password);
		//				System.out.println();	    

		return result;
	}


}

//tostring

