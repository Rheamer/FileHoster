package Rheamer.Storage;

import Rheamer.Storage.models.utils.DtoMapperPhoto;
import Rheamer.Storage.models.Photo;
import Rheamer.Storage.models.PhotoDto;
import Rheamer.Storage.repositories.RequestRepository;
import Rheamer.Storage.services.KafkaPhotoUploadService;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

@EnableKafka
@SpringBootTest
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StorageApplicationTests {

	@Autowired
	EmbeddedKafkaBroker kafkaEmbedded;

	@Autowired
	KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

	@Autowired
	private KafkaTemplate<String, Photo> kafkaTemplate;

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
    KafkaPhotoUploadService kfProducer;
	@Autowired
	private KafkaConsumer consumer;

	@Autowired
	private MockMvc mockMvc;

	@BeforeAll
	public void setUp() throws Exception {
		for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry.getListenerContainers()) {
			ContainerTestUtils.waitForAssignment(messageListenerContainer,
					kafkaEmbedded.getPartitionsPerTopic());
		}
	}

	@Test
	public void uploadPicture() throws Exception {
		var request = MockMvcRequestBuilders.post("/uploadPhoto");
		JSONObject jo = new JSONObject();
		jo.put("name", "mydad");
		jo.put("data", "mydad".getBytes(StandardCharsets.UTF_8));
		ResultActions response = mockMvc.perform(request);
		response.andExpect(MockMvcResultMatchers.status().isAccepted());
	}

	@Test
	public void uploadPictureVerbose() throws Exception {
		var request = MockMvcRequestBuilders.post("/uploadPhoto");
		var photoDto = new PhotoDto("mydad", "mydad".getBytes(StandardCharsets.UTF_8));
		DtoMapperPhoto.validate(photoDto);
		var photo = DtoMapperPhoto.toPhoto(photoDto);
		kfProducer.sendPhoto(photo);

	}

	@TestConfiguration
	public static class TestConfig {
		@Value(value = "${spring.kafka.bootstrap-servers}")
		private String bootstrapAddress;
		@Autowired
		EmbeddedKafkaBroker kafkaBroker;
		@Bean
		public ProducerFactory<String, String> producerFactory() {
			var props = KafkaTestUtils.producerProps(kafkaBroker);
			return new DefaultKafkaProducerFactory<>(props);
		}

		@Bean
		public KafkaTemplate<String, String> kafkaTemplate() {
			KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory());
			return kafkaTemplate;
		}
	}
}
