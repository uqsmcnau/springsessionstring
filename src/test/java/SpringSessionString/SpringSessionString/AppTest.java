package SpringSessionString.SpringSessionString;

import org.json.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AppTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void Test1() throws JSONException {
		SQLiteJDBCDriverConnection.clearDatabase();
		
		String body = this.restTemplate.getForObject("/state", String.class);
		JSONObject response = new JSONObject(body);
		assertThat(response.get("response")).isEqualTo("");
	}
	
	@Test
	public void Test2() throws JSONException, RestClientException, URISyntaxException {
		SQLiteJDBCDriverConnection.clearDatabase();
		
		JSONObject request = new JSONObject();
		request.put("character", 'a');
		request.put("amount", 3);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		String body = this.restTemplate.postForObject("/chars", entity, String.class);
		
		body = this.restTemplate.getForObject("/state", String.class);
		JSONObject response = new JSONObject(body);
		
		assertThat(response.get("response")).isEqualTo("aaa");
	}

	
	@Test
	public void Test3() throws JSONException, RestClientException, URISyntaxException {
		SQLiteJDBCDriverConnection.clearDatabase();
		
		JSONObject request = new JSONObject();
		request.put("character", 'a');
		request.put("amount", 3);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		String body = this.restTemplate.postForObject("/chars", entity, String.class);
		
		body = this.restTemplate.getForObject("/state", String.class);
		JSONObject response = new JSONObject(body);
		
		assertThat(response.get("response")).isEqualTo("aaa");
	}
	
	@Test
	public void Test4() throws JSONException, RestClientException, URISyntaxException {
		SQLiteJDBCDriverConnection.clearDatabase();
		
		JSONObject request = new JSONObject();
		request.put("character", 'a');
		request.put("amount", 3);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		String body = this.restTemplate.postForObject("/chars", entity, String.class);
		
		body = this.restTemplate.getForObject("/state", String.class);
		JSONObject response = new JSONObject(body);
		assertThat(response.get("response")).isEqualTo("aaa");
		
		this.restTemplate.delete("/chars/a");
		
		body = this.restTemplate.getForObject("/state", String.class);
		response = new JSONObject(body);
		assertThat(response.get("response")).isEqualTo("aa");
	}
	
	@Test
	public void Test5() throws JSONException, RestClientException, URISyntaxException {
		SQLiteJDBCDriverConnection.clearDatabase();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		JSONObject request = new JSONObject();
		request.put("character", 'a');
		request.put("amount", 3);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		this.restTemplate.postForObject("/chars", entity, String.class);
		
		request = new JSONObject();
		request.put("character", '1');
		request.put("amount", 1);
		entity = new HttpEntity<String>(request.toString(), headers);
		this.restTemplate.postForObject("/chars", entity, String.class);

		request = new JSONObject();
		request.put("character", '4');
		request.put("amount", 1);
		entity = new HttpEntity<String>(request.toString(), headers);
		this.restTemplate.postForObject("/chars", entity, String.class);

		request = new JSONObject();
		request.put("character", '5');
		request.put("amount", 1);
		entity = new HttpEntity<String>(request.toString(), headers);
		this.restTemplate.postForObject("/chars", entity, String.class);

		request = new JSONObject();
		request.put("character", 'a');
		request.put("amount", 5);
		entity = new HttpEntity<String>(request.toString(), headers);
		this.restTemplate.postForObject("/chars", entity, String.class);

		request = new JSONObject();
		request.put("character", '5');
		request.put("amount", 1);
		entity = new HttpEntity<String>(request.toString(), headers);
		this.restTemplate.postForObject("/chars", entity, String.class);

		request = new JSONObject();
		request.put("character", 'a');
		request.put("amount", 5);
		entity = new HttpEntity<String>(request.toString(), headers);
		this.restTemplate.postForObject("/chars", entity, String.class);
		
		String body = this.restTemplate.getForObject("/state", String.class);
		JSONObject response = new JSONObject(body);
		assertThat(response.get("response")).isEqualTo("aaa145aaaaa5aaaaa");
		
		body = this.restTemplate.getForObject("/sum", String.class);
		response = new JSONObject(body);
		assertThat(response.get("response")).isEqualTo("150");
	}
	
	@Test
	public void Test6() throws JSONException, RestClientException, URISyntaxException {
		SQLiteJDBCDriverConnection.clearDatabase();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		JSONObject request = new JSONObject();
		request.put("character", 'a');
		request.put("amount", 3);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		this.restTemplate.postForObject("/chars", entity, String.class);
		
		request = new JSONObject();
		request.put("character", '1');
		request.put("amount", 1);
		entity = new HttpEntity<String>(request.toString(), headers);
		this.restTemplate.postForObject("/chars", entity, String.class);

		request = new JSONObject();
		request.put("character", '4');
		request.put("amount", 1);
		entity = new HttpEntity<String>(request.toString(), headers);
		this.restTemplate.postForObject("/chars", entity, String.class);

		request = new JSONObject();
		request.put("character", '5');
		request.put("amount", 1);
		entity = new HttpEntity<String>(request.toString(), headers);
		this.restTemplate.postForObject("/chars", entity, String.class);

		request = new JSONObject();
		request.put("character", 'd');
		request.put("amount", 2);
		entity = new HttpEntity<String>(request.toString(), headers);
		this.restTemplate.postForObject("/chars", entity, String.class);

		request = new JSONObject();
		request.put("character", '5');
		request.put("amount", 1);
		entity = new HttpEntity<String>(request.toString(), headers);
		this.restTemplate.postForObject("/chars", entity, String.class);

		request = new JSONObject();
		request.put("character", 'c');
		request.put("amount", 1);
		entity = new HttpEntity<String>(request.toString(), headers);
		this.restTemplate.postForObject("/chars", entity, String.class);
		
		String body = this.restTemplate.getForObject("/state", String.class);
		JSONObject response = new JSONObject(body);
		assertThat(response.get("response")).isEqualTo("aaa145dd5c");
		
		body = this.restTemplate.getForObject("/chars", String.class);
		response = new JSONObject(body);
		assertThat(response.get("response")).isEqualTo("aaaddc");
	}
}