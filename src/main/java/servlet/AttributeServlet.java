package servlet;// From "Professional Java Server Programming", Patzer et al.,

// Import Servlet Libraries

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

// Import Java Libraries
import java.io.*;
import java.util.Enumeration;

@WebServlet(name = "attributeServlet", urlPatterns = {"/attribute"})
public class AttributeServlet extends HttpServlet {

    String lifeCycleURL = "/session";

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get session object
        HttpSession session = request.getSession();

        String name = request.getParameter("attrib_name");
        String hobby = request.getParameter("attrib_hobby");
        String value = request.getParameter("attrib_value");
        String remove = request.getParameter("attrib_remove");
        String[] vals = {hobby, value};
        if (remove != null && remove.equals("on")) {
            session.removeAttribute(name);
        } else {
            if ((name != null && name.length() > 0) && (vals != null && vals.length > 0)) {
                session.setAttribute(name, vals);
            }

        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        // no-cache lets the page reload by clicking on the reload link
        out.println("<meta http-equiv=\"Pragma\" content=\"no-cache\">");
        out.println("<head>");
        out.println(" <title>Session lifecycle</title>");
        out.println("</head>");
        out.println("");

        out.println("<body>");
        out.println("<h1><center>Session attributes | By: Deval Parikh</center></h1>");

        out.println("Enter name, hobby, and value of an attribute");


        String url = response.encodeURL("attribute");
        out.println("<form action=\"" + url + "\" method=\"GET\">");
        out.println(" Name: ");
        out.println(" <input type=\"text\" size=\"10\" name=\"attrib_name\">");

        out.println(" Hobby: ");
        out.println(" <input type=\"text\" size=\"10\" name=\"attrib_hobby\">");

        out.println(" Value: ");
        out.println(" <input type=\"text\" size=\"10\" name=\"attrib_value\">");

        out.println(" <br><input type=\"checkbox\" name=\"attrib_remove\">Remove");
        out.println(" <input type=\"submit\" name=\"update\" value=\"Update\">");
        out.println("</form>");
        out.println("<hr>");

        out.println("Attributes in this session:");
        Enumeration e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            String att_name = (String) e.nextElement();
            String[] att_vals = (String[]) session.getAttribute(att_name);

            out.print("<br><b>Name:</b> ");
            out.println(att_name);
            out.print("<br><b>Hobby:</b> ");
            out.println(att_vals[0]);
            out.print("<br><b>Value:</b> ");
            out.println(att_vals[1]);
        } //end while

        out.print("<br><br><a href=\"" + lifeCycleURL + "?action=invalidate\">");
        out.println("Invalidate the session</a>");
       out.print("<br><br><a href=\"" + "https://github.com/devalparikh/Deval-SWE432-Week5" + "\">");
       out.println("Source Code</a>");

        out.println("</body>");
        out.println("</html>");
        out.close();
    } // End doGet
} //End  SessionLifeCycle
