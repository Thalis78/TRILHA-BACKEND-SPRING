import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class TESTE extends HttpServlet{
    public void service (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        PrintWriter out = res.getWriter();
        String nome = req.getParameter("nome");
        System.out.println(nome);
    }
}
