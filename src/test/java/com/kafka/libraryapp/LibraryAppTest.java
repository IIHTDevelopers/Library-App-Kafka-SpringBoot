package com.kafka.libraryapp;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static com.kafka.libraryapp.testutils.TestUtils.currentTest;
import static com.kafka.libraryapp.testutils.TestUtils.businessTestFile;
import static com.kafka.libraryapp.testutils.TestUtils.yakshaAssert;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assess.kafka.libraryapp.consumer.service.KafkaBookConsumerService;
import com.assess.kafka.libraryapp.model.Book;
import com.assess.kafka.libraryapp.producer.controller.BookController;
import com.assess.kafka.libraryapp.producer.service.KafkaBookProducerService;
import com.kafka.libraryapp.testutils.MasterData;
import static org.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;


@WebMvcTest(BookController.class)
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@EnableKafka
public class LibraryAppTest {
    @Autowired
	private MockMvc mockMvc;

@Mock
    private KafkaTemplate<String, Book> kafkaTemplate;


    @MockBean
	private KafkaBookProducerService bookProducerService;

@Autowired
    private KafkaBookConsumerService kafkaBookConsumerService;

    @Test
	public void test_BookControllerSendBook() throws Exception {
		final int count[] = new int[1];
        Book book =new Book();
        book.setId(1L);
        book.setAuthor("First");
        book.setTitle("New Book");
		
		when(this.bookProducerService.sendBook("addBook", book)).then(new Answer<Book>() {

			@Override
			public Book answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return book;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/books")
				.content(MasterData.asJsonString(book)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
    public void testSendBook() throws Exception{
		Book book =new Book();
        book.setId(1L);
        book.setAuthor("First");
        book.setTitle("New Book");
        String topic = "addBook";

		try{
        this.bookProducerService.sendBook(topic, book);

        verify(kafkaTemplate, times(1)).send(topic, book);
		yakshaAssert(currentTest(), true, businessTestFile);
		}catch(Exception ex){
			yakshaAssert(currentTest(), false, businessTestFile);
		}

    }	
	
	@Test
    public void testConsumeBook() {
        Book book =new Book();
        book.setId(1L);
        book.setAuthor("First");
        book.setTitle("New Book");
        kafkaTemplate.send("addBook", book);

        await().atMost(5, SECONDS).untilAsserted(() -> {
            // Implement a method in KafkaBookConsumerService to capture the last consumed message for assertion
            // For example, assuming you have a getLastConsumedBook() method:
            Book consumedBook = kafkaBookConsumerService.listenAddBook(book);
            yakshaAssert(currentTest(), consumedBook != null, businessTestFile);
            
        });
    }


}
