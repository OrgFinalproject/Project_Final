package soft;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdminTest {

    private Admin admin; // هنا عرفت المتغير

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("=== Starting Admin Tests ===");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("=== All Admin Tests Finished ==="); 
    }

    @BeforeEach
    void setUp() throws Exception {
        admin = new Admin("admin1", "1234");
        System.out.println("New Admin object created");
    }

    @AfterEach
    void tearDown() throws Exception {
        System.out.println("Test finished\n");
    }

    @Test
    void admincreationTest() {
        assertEquals("admin1", admin.getUsername());
        assertEquals("1234", admin.getPassword());
    }

    @Test
    void testRegisterNewCompany() {
        Company company = new Company("Car Company","Cars");

        admin.registerNewCompany(company);

        List<Company> companies = admin.getRegisteredCompanies();

        assertEquals(1, companies.size());
        assertTrue(companies.contains(company));
    }

    @Test
    void testEmptyListInitially() {
        assertTrue(admin.getRegisteredCompanies().isEmpty());
    }
}