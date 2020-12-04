package orion.users;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import orion.users.service.PublicService;

import orion.users.data.UserDAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * test.
 */
public class RegularTest {

    /** Logger. **/
    private static Logger logger = Logger.getLogger("RegularTest");

    UserDAO dao = new UserDAO();

    @Test
    @Order(1)
    @Tag("USER")
    @DisplayName("md5 test")
    public String md5() {
        logger.info("test 1");
        String md5 = "servicepassword";
        dao.MD5(md5);
        return md5;
    }

    @Test
    @Order(2)
    @Tag("USER")
    @DisplayName("hash test")
    public String hash() {
        logger.info("test 2");
        String hhash = "hash";
        hhash = dao.generateHash();
        return hhash;
    }

 
}
