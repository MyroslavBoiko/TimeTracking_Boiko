package tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author Mirosha
 */
public class UserTag extends TagSupport {

    private static final Logger LOGGER = Logger.getLogger(UserTag.class);

    private String firstname;
    private String lastname;

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public int doStartTag() {
        LOGGER.debug("UserTag execution");
        try {
            JspWriter out = pageContext.getOut();
            out.write(firstname + " " + lastname);
        } catch (IOException e) {
            LOGGER.error("IO Exception in custom tag.");
        }
        return SKIP_BODY;
    }
}
