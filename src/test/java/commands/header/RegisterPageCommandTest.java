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
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class RegisterPageCommandTest {

    String page = "/WEB-INF/views/register.jsp";
    RegisterPageCommand registerPageCommand;
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    @Before
    public void setUp() throws Exception {
        registerPageCommand = new RegisterPageCommand();
    }

    @Test
    public void shouldReturnAdminPageString() throws ServletException, IOException {
        String result = registerPageCommand.execute(request, response);
        assertEquals(page, result);
    }
}