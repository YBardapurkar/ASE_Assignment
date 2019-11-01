package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import registration.model.User;
import registration.model.UserError;

@RunWith(JUnitParamsRunner.class)
public class UserTest {
	
	User user;
	UserError userError;
	
	@Before
	public void setUp() throws Exception {
		user = new User();
		userError = new UserError();
	}
	
	@Test
	@FileParameters("src/test/UserRegisterTestCases.csv")
	public void testRegister(int testCaseNumber, String action, String username, String password, 
			String firstname,String lastname, String role, String utaid, String phone, String email, 
			String street, String city, String state, String zipcode,
			String usernameMessage, String passwordMessage, String firstnameMessage, String lastnameMessage, 
			String roleMessage, String utaidMessage, String phoneMessage, String emailMessage, 
			String streetMessage, String cityMessage, String stateMessage, String zipcodeMessage,
			String message, String errorMessage, String description) {
		
		user.setuser(username, password, firstname, lastname, role, utaid, phone, email, street, city, state, zipcode);
		userError = user.validateUser(action);
		
		assertEquals(username, user.getUsername());
		assertEquals(password, user.getPassword());
		assertEquals(role, user.getRole());
		assertEquals(utaid, user.getUtaId());
		assertEquals(phone, user.getPhone());
		assertEquals(email, user.getEmail());
		assertEquals(street, user.getStreet());
		assertEquals(city, user.getCity());
		assertEquals(state, user.getState());
		assertEquals(zipcode, user.getZipcode());
		
		assertEquals(usernameMessage, userError.getUsernameError());
		assertEquals(passwordMessage, userError.getPasswordError());
		assertEquals(firstnameMessage, userError.getFirstnameError());
		assertEquals(lastnameMessage, userError.getLastnameError());
		assertEquals(roleMessage, userError.getRoleError());
		assertEquals(utaidMessage, userError.getUtaIdError());
		assertEquals(phoneMessage, userError.getPhoneError());
		assertEquals(emailMessage, userError.getEmailError());
		assertEquals(streetMessage, userError.getStreetError());
		assertEquals(cityMessage, userError.getCityError());
		assertEquals(stateMessage, userError.getStateError());
		assertEquals(zipcodeMessage, userError.getZipcodeError());
		
		assertEquals(errorMessage, userError.getErrorMsg());
//		assertEquals(message, user.getUsername());
	}
	
	@Test
	@FileParameters("src/test/UserLoginTestCases.csv")
	public void testLogin(int testCaseNumber, String action, String username, String password, 
			String usernameMessage, String passwordMessage, String message, 
			String errorMessage, String description) {
		
		user.setLoginUser(username, password);
		userError = user.validateUser(action);
		
		assertEquals(username, user.getUsername());
		assertEquals(password, user.getPassword());
		assertEquals(usernameMessage, userError.getUsernameError());
		assertEquals(passwordMessage, userError.getPasswordError());
		assertEquals(errorMessage, userError.getErrorMsg());
//		assertEquals(message, user.getUsername());
	}
	
	@Test
	@FileParameters("src/test/UserChangeRoleTestCases.csv")
	public void testChangeRole(int testCaseNumber, String action, String username, String role, 
			String usernameMessage, String roleMessage, String message, 
			String errorMessage, String description) {
		
		user.setUsername(username);
		user.setRole(role);
		userError = user.validateUser(action);
		
		assertEquals(username, user.getUsername());
		assertEquals(role, user.getRole());
		
		assertEquals(usernameMessage, userError.getUsernameError());
		assertEquals(roleMessage, userError.getRoleError());
		assertEquals(errorMessage, userError.getErrorMsg());
//		assertEquals(message, user.getUsername());
	}
	
	@Test
	@FileParameters("src/test/UserUpdateProfileTestCases.csv")
	public void testUpdateProfile(int testCaseNumber, String action, String username, String password, 
			String firstname, String lastname, String phone, String email,  String street, String city, 
			String state, String zipcode,
			String usernameMessage, String passwordMessage, String firstnameMessage, String lastnameMessage, 
			String phoneMessage, String emailMessage, String streetMessage, String cityMessage, 
			String stateMessage, String zipcodeMessage,
			String message, String errorMessage, String description) {
		
		user.setuser(username, password, firstname, lastname, null, null, phone, email, street, city, state, zipcode);
		userError = user.validateUser(action);
		
		assertEquals(username, user.getUsername());
		assertEquals(password, user.getPassword());
		assertEquals(phone, user.getPhone());
		assertEquals(email, user.getEmail());
		assertEquals(street, user.getStreet());
		assertEquals(city, user.getCity());
		assertEquals(state, user.getState());
		assertEquals(zipcode, user.getZipcode());
		
		assertEquals(usernameMessage, userError.getUsernameError());
		assertEquals(passwordMessage, userError.getPasswordError());
		assertEquals(firstnameMessage, userError.getFirstnameError());
		assertEquals(lastnameMessage, userError.getLastnameError());
		assertEquals(phoneMessage, userError.getPhoneError());
		assertEquals(emailMessage, userError.getEmailError());
		assertEquals(streetMessage, userError.getStreetError());
		assertEquals(cityMessage, userError.getCityError());
		assertEquals(stateMessage, userError.getStateError());
		assertEquals(zipcodeMessage, userError.getZipcodeError());
		
		assertEquals(errorMessage, userError.getErrorMsg());
//		assertEquals(message, user.getUsername());
	}
	
	@Test
	@FileParameters("src/test/UserEditUserTestCases.csv")
	public void testEditUser(int testCaseNumber, String action, String username, String password, 
			String firstname, String lastname, String phone, String email,  String street, String city, 
			String state, String zipcode,
			String usernameMessage, String passwordMessage, String firstnameMessage, String lastnameMessage, 
			String phoneMessage, String emailMessage, String streetMessage, String cityMessage, 
			String stateMessage, String zipcodeMessage,
			String message, String errorMessage, String description) {
		
		user.setuser(username, password, firstname, lastname, null, null, phone, email, street, city, state, zipcode);
		userError = user.validateUser(action);
		
		assertEquals(username, user.getUsername());
		assertEquals(password, user.getPassword());
		assertEquals(phone, user.getPhone());
		assertEquals(email, user.getEmail());
		assertEquals(street, user.getStreet());
		assertEquals(city, user.getCity());
		assertEquals(state, user.getState());
		assertEquals(zipcode, user.getZipcode());
		assertEquals(errorMessage, userError.getErrorMsg());
		assertEquals(usernameMessage, userError.getUsernameError());
		assertEquals(passwordMessage, userError.getPasswordError());
		assertEquals(firstnameMessage, userError.getFirstnameError());
		assertEquals(lastnameMessage, userError.getLastnameError());
		assertEquals(phoneMessage, userError.getPhoneError());
		assertEquals(emailMessage, userError.getEmailError());
		assertEquals(streetMessage, userError.getStreetError());
		assertEquals(cityMessage, userError.getCityError());
		assertEquals(stateMessage, userError.getStateError());
		assertEquals(zipcodeMessage, userError.getZipcodeError());
		
		assertEquals(errorMessage, userError.getErrorMsg());
//		assertEquals(message, user.getUsername());
	}
}
