package servlet;// Simple example servlet from slides
import javax.servlet.*; // servlet library
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*; // servlet library
import java.io.*;


@WebServlet(
        name = "/deval",
        urlPatterns = {"/deval"}
)
public class Week5 extends HttpServlet // Inheriting from HttpServlet makes this a servlet
{
    public void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html"); // Tells the web container what we're sending back
        PrintWriter out = response.getWriter(); // Make it appear as if we're "writing" to the browser window

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet example</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Visit my SWE page: <a href='https://mason.gmu.edu/~dparikh4/'>Deval Parikh</a>.</p>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }  // end doGet()
}  // end Hello