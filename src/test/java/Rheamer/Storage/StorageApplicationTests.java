package Rheamer.Storage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class StorageApplicationTests {

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void uploadPicture() throws Exception {
		var request = MockMvcRequestBuilders.post("/uploadPhoto");
		request.content("123123123");
		ResultActions response = mockMvc.perform(request);
		response.andExpect(MockMvcResultMatchers.status().isAccepted());
	}

}
