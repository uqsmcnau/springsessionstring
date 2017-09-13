package SpringSessionString.SpringSessionString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class Controller {
    
	// Create a id for the user based off their request headers
	private String getUserId() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();

        // Identify the user by their remote address and browser
        // Then convert to a hexcode to allow use as an id
        String user_id = Integer.toHexString((request.getRemoteAddr() + request.getHeader("User-Agent")).hashCode());
        
        return user_id;
	}
	
	// Return the state string for the current user
	private String getSessionString() {
		String user_id = getUserId();

		return SQLiteJDBCDriverConnection.getUserSessionString(user_id);
	}
	
	// Set the state string for the current user
	private void setSessionString(String new_session_string) {
		String user_id = getUserId();

		SQLiteJDBCDriverConnection.setUserSessionString(user_id, new_session_string);
	}
	
	// Return the full state string for the user
    @RequestMapping("/state")
    public JSONResponse state() {
    	System.out.println("UserID: " + getUserId() + ", get: state");
    	
        return new JSONResponse(getUserId(), getSessionString());
    }
    
    // Return the sum of each number in the current state string
    @RequestMapping("/sum")
    public JSONResponse sum() {
    	String session_string = getSessionString();
    	
    	Pattern MY_PATTERN = Pattern.compile("\\d+");
		Matcher m = MY_PATTERN.matcher(session_string);
		int total = 0;
		while (m.find()) {
		    total += Integer.parseInt(m.group(0));
		}
		
    	System.out.println("UserID: " + getUserId() + ", get: sum");
		
        return new JSONResponse(getUserId(), String.valueOf(total));
    }

    // Return the state string without any numbers
    @RequestMapping(value = "/chars", method = RequestMethod.GET)
    public JSONResponse get_chars() {
    	String session_string = getSessionString();
    	session_string = session_string.replaceAll("\\d", "");
    	
    	System.out.println("UserID: " + getUserId() + ", get: chars");
    	
        return new JSONResponse(getUserId(), session_string);
    }
    
    // Add the given character 1-9 times to the current state string
    @RequestMapping(value = "/chars", method = RequestMethod.POST)
    public JSONResponse post_chars(@RequestBody JSONRequest json) {  
    	char character = json.getCharacter();
    	int amount = json.getAmount();
    	
    	if (!Character.isLetterOrDigit(character) || amount <= 0 || amount >= 10) {
    		ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    		return null;
    	}
    	
    	String current_string = getSessionString();
    	for (int i = 0; i < amount; i++) {
    		current_string += character;
    	}
    	
    	if (current_string.length() > 200) {
    		ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    		return null;
    	}
    	
    	setSessionString(current_string);
    	
    	System.out.println("UserID: " + getUserId() + ", added: " + character + ", amount: " + amount);
    	
        return new JSONResponse(getUserId(), current_string);
    }

    // Remove the last instance of the character from the state string
    @RequestMapping(value = "/chars/{character}", method = RequestMethod.DELETE)
    public JSONResponse get_id_chars(@PathVariable(value="character") String character) {
    	if (character.length() != 1) {
    		ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    		return null;
    	}
    	String session_string = getSessionString();
    	
    	if (session_string.contains(character)) {
    		session_string = session_string.substring(0,session_string.lastIndexOf(character)) 
    				+ session_string.substring(session_string.lastIndexOf(character) + 1);
    		setSessionString(session_string);
    	}

		System.out.println("UserID: " + getUserId() + ", removed: " + character);
		
        return new JSONResponse(getUserId(), session_string);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Controller.class, args);
    }
}