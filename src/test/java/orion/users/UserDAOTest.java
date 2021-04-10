package orion.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import orion.users.data.UserDAO;
import orion.users.model.User;

/**
 * test.
 */
public class UserDAOTest {

    /** constructor. **/
    private UserDAO dao = new UserDAO();

    @Test
    @Tag("USER")
    public void md5() {
        assertEquals("5f4dcc3b5aa765d61d8327deb882cf99", dao.MD5("password"));
    }

    @Test
    @Tag("USER")
    public void generateHash(){
        assertNotNull(dao.generateHash());
    }
}
