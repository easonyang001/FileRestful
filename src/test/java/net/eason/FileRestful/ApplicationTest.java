package net.eason.FileRestful;

import java.io.File;

import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class ApplicationTest {

	@Test
	public void test(){
	    String url = "http://127.0.0.1:8080/file";
	    String filePath = "/Users/ly/Documents/Background/CSC_0309.JPG";  
	  
	    RestTemplate rest = new RestTemplate();  
	    
	    FileSystemResource resource = new FileSystemResource(new File(filePath));  
	    MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();  
	    param.add("file", resource);  
	    param.add("userId", "tester");
	  
	    String string = rest.postForObject(url, param, String.class);  
	    System.out.println(string);  
	}
}

