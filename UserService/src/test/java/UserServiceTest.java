import org.freefly.dodaily.userservice.UserServiceApplication;
import org.freefly.dodaily.userservice.common.UserResult;
import org.freefly.dodaily.userservice.controller.CookieController;
import org.freefly.dodaily.userservice.controller.UserController;
import org.freefly.dodaily.userservice.entity.User;
import org.freefly.dodaily.userservice.tool.JWTTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpCookie;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletResponse;

@SpringBootTest(classes = UserServiceApplication.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    /**
     * Test for UserService include Cookie
     * <p>
     * For UserService:
     * 1.The methods in Controller
     * 2.Here is others if exist:
     * <p>
     * <p>
     * For Cookie:
     * 1.The method validate
     * 2.Other methods about cookie will be tested together in UserService
     */

    @Autowired
    private UserController userController;

    @Autowired
    private CookieController cookieController;

    @Autowired
    private HttpServletResponse response;

    @Test
    public void fullProcess() {
        User user = new User(1, "freefly", "root1234", "freefly");
        user.setRole(1);

        // Full process and also test for repeated username if run twice
        userController.Register(user);
        userController.login(user, response);
        userController.logout(user.getId(), response);
    }

    @Test
    public void loginOrRegisterWhenUserUnsuit() {

        // for register
        int i1 = userController.Register(null);
        System.out.println(i1);

        User user = new User(1, "freefly", "root1234", "freefly");
        user.setRole(1);

        // test if user is null
        UserResult i2 = userController.login(null, response);
        System.out.println(i2);

        // test if name or password is null
        user.setName(null);
        UserResult i3 = userController.login(user, response);
        System.out.println(i3);
        user.setNickname("freefly");
        user.setPassword(null);
        UserResult i4 = userController.login(user, response);
        System.out.println(i4);
    }

    @Test
    public void update() {
        // make a virtual user to create
        User user = new User("demo", "demo", "demo");
        userController.Register(user);

        user.setName("demo2");
        int i1 = userController.updateUser(user);
        System.out.println(i1);
    }

    @Test
    public void delete() {
        int i1 = userController.deleteUser(2);
        System.out.println(i1);
    }

    @Test
    public void cookieAll() {
        String token = JWTTool.generateToken(2, "demo");
        int i1 = cookieController.validate(new HttpCookie("DODAILY_USER", token));
        System.out.println(i1); // 0
    }
}
