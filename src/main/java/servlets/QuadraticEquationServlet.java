package servlets;

import models.QuadraticEquationException;
import models.QuadraticEquationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import services.QuadraticEquationService;
import services.QuadraticEquationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;

public class QuadraticEquationServlet extends HttpServlet {
    private final QuadraticEquationService service = new QuadraticEquationServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger(QuadraticEquationServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(request.getInputStream());

            Element root = doc.getDocumentElement();
            double a = Double.parseDouble(root.getElementsByTagName("a").item(0).getTextContent());
            double b = Double.parseDouble(root.getElementsByTagName("b").item(0).getTextContent());
            double c = Double.parseDouble(root.getElementsByTagName("c").item(0).getTextContent());

            logger.debug("a : " + a + ", b : " + b + ", c : " + c);

            QuadraticEquationResponse result = service.solveEquation(a, b, c);

            response.setContentType("application/xml");
            response.getWriter().write("<response><formula>" + result.getFormula() + "</formula><D>" + result.getD() + "</D><x1>" + result.getX1() + "</x1>");
            if (result.getX2() != null) {
                response.getWriter().write("<x2>" + result.getX2() + "</x2>");
            }
            response.getWriter().write("</response>");

        } catch (QuadraticEquationException e) {
            response.setContentType("application/xml");
            response.getWriter().write("<response><error>" + e.getMessage() + "</error><formula>" + e.getFormula() + "</formula><D>" + e.getD() + "</D></response>");
        } catch (NullPointerException e){
            response.setContentType("application/xml");
            response.getWriter().write("<response><error>Not all values were received</error></response>");
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid XML request");
        }
    }
}