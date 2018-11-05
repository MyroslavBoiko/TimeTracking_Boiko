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
public class IndexPageCommandTest {

    String page = "/index.jsp";
    IndexPageCommand indexPageCommand;
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    @Before
    public void setUp() throws Exception {
        indexPageCommand = new IndexPageCommand();
        doNothing().when(request).setAttribute("currentPage", page);
        when(request.getAttribute("currentPage")).thenReturn(page);
    }

    @Test
    public void shouldReturnAdminPageString() throws ServletException, IOException {
        String result = indexPageCommand.execute(request, response);
        verify(request, times(1)).setAttribute("currentPage",page);
        assertEquals(page, result);
    }

    @Test
    public void shouldSetAttributeIntoRequest() throws ServletException, IOException {
        indexPageCommand.execute(request, response);
        assertEquals(page, request.getAttribute("currentPage"));
    }
}