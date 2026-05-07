package soft;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class AdminAuthTest {
private AdminAuthService adminAuth;
private Admin admin;
	 @BeforeAll
	    static void setUpBeforeClass() throws Exception {
	        System.out.println("=== Starting AdminAuth Tests ===");
	    }

	    @AfterAll
	    static void tearDownAfterClass() throws Exception {
	        System.out.println("=== All AdminAuth Tests Finished ==="); 
	    }


	@BeforeEach
	void setUp() throws Exception {
		admin = new Admin("ahmad","123123");
		adminAuth= new AdminAuthService(admin);
	}

	@AfterEach
	void tearDown() throws Exception {
        System.out.println("Test finished\n");

	}

	 @Test
	    void testLoginSuccess() {
	        assertTrue(adminAuth.login("ahmad", "123123"));
	        assertTrue(adminAuth.isLoggedIn());
	    }

	    @Test
	    void testLoginFail() {
	        assertFalse(adminAuth.login("ahmad", "wrong"));
	        assertFalse(adminAuth.isLoggedIn());
	    }

	    @Test
	    void testLogout() {
	        adminAuth.login("ahmad", "123123");
	        adminAuth.logout();
	        assertFalse(adminAuth.isLoggedIn());
	    }
	    @Test
	    void testLogoutWithoutLogin() {
	        adminAuth.logout();
	        assertFalse(adminAuth.isLoggedIn());
	    }
	    
	}
