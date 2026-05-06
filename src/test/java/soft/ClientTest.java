package soft;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientTest {

	private Client client;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
        System.out.println("=== Starting Client Tests ===");

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
        System.out.println("=== All Client Tests Finished ==="); 
	}

	@BeforeEach
	void setUp() throws Exception {
		client =new Client("leen","leen.05@gmail.com","0599454545");
				
	}

	@AfterEach
	void tearDown() throws Exception {
        System.out.println("Test finished\n");
	}

	@Test
	void clientCreationtest() {
		assertEquals("leen",client.getName());
		assertEquals("leen.05@gmail.com",client.getEmail());
		assertEquals("0599454545",client.getPhoneNumber());

		
	}

}
