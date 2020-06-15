package spring.chatapp.user.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring.chatapp.user.data.DynamoDbDAO;
import spring.chatapp.user.data.User;

@Component
public class Authentication {
	
	@Autowired
	DynamoDbDAO dynamoDbDAO;
	
	public User getUserbyUserName(String userName) {
		
		return dynamoDbDAO.loadRecord(userName);
	}
	
	
	public Result authenticate(String userName, String password) {
		
		User user=getUserbyUserName(userName);
		if(user==null) {
			
			return new Result(false, "No User Found",null);
		}
		
		if(password.equals(user.getPassword())) {
			
			return new Result(true, "User Succesfully logged in", user);
			
		}
		
		else {
			
			return new Result(false, "Wrong Username or password", null);
			
		}
		
	}
}