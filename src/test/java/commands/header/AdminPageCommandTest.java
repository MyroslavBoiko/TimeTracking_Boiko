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

@RunWith(MockitoJUnitRunner.class)
public class AdminPageCommandTest {

    String page = "/WEB-INF/views/admin/admin.jsp";
    AdminPageCommand adminPageCommand;
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    @Before
    public void setUp() throws Exception {
        adminPageCommand = new AdminPageCommand();
        doNothing().when(request).setAttribute("currentPage", page);
        when(request.getAttribute("currentPage")).thenReturn(page);
    }

    @Test
    public void shouldReturnAdminPageString() throws ServletException, IOException {
        String result = adminPageCommand.execute(request, response);
        verify(request, times(1)).setAttribute("currentPage",page);
        assertEquals(page, result);
    }

    @Test
    public void shouldSetAttributeIntoRequest() throws ServletException, IOException {
        adminPageCommand.execute(request, response);
        assertEquals(page, request.getAttribute("currentPage"));
    }

}