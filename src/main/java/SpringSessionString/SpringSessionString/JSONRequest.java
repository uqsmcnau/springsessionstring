package SpringSessionString.SpringSessionString;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JSONRequest {
    @JsonProperty("character")
    private char character;
    

	@JsonProperty("amount")
    private int amount;
		
    public char getCharacter() {
		return character;
	}
    
	public void setCharacter(char character) {
		this.character = character;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
