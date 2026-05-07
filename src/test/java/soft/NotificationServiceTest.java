package soft;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

class NotificationServiceTest {

	
	
	  
	@Test
	void test() {
		  Observer observer = mock(Observer.class);
		// notification service
        NotificationService service = new NotificationService();
        service.addObserver(observer);

        // client
        Client user = new Client("Nadeen", "test@mail.com", "123");

        // call method
        service.notifyUser(user, "Hello");

        // verify
        verify(observer).update(user, "Hello");
	}

}
