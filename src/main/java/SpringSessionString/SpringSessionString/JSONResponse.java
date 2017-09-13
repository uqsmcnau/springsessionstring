package SpringSessionString.SpringSessionString;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JSONResponse {
    @JsonProperty("userID")
    private String userID;
    

	@JsonProperty("response")
    private String response;

	public JSONResponse(String u, String r){
		userID = u;
		response = r;
	}

	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getResponse() {
		return response;
	}


	public void setResponse(String response) {
		this.response = response;
	}
}
