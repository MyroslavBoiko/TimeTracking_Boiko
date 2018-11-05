package commands.header;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class LoginPageCommandTest {

    String page = "/WEB-INF/views/login.jsp";
    LoginPageCommand loginPageCommand;
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    @Before
    public void setUp() throws Exception {
        loginPageCommand = new LoginPageCommand();
    }

    @Test
    public void shouldReturnAdminPageString() throws ServletException, IOException {
        String result = loginPageCommand.execute(request, response);
        assertEquals(page, result);
    }

}