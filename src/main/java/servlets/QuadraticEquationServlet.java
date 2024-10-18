package servlets;

import Exceptions.QuadraticEquationException;
import models.EquationParameters;
import models.QuadraticEquationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.QuadraticEquationService;
import services.QuadraticEquationServiceImpl;
import services.ResponseWriter;
import services.XmlProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuadraticEquationServlet extends HttpServlet {

    private QuadraticEquationService service;

    private static final String RESPONSE_TEMPLATE = "<response><formula>%s</formula><D>%.2f</D><x1>%.2f</x1>%s</response>";

    @Override
    public void init() throws ServletException {
        super.init();
        service = new QuadraticEquationServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            EquationParameters params = XmlProcessor.processXml(request.getInputStream());
            QuadraticEquationResponse result = service.solveEquation(params.getA(), params.getB(), params.getC());

            String x2Part = (result.getX2() != null) ? String.format("<x2>%.2f</x2>", result.getX2()) : "";
            String responseContent = String.format(RESPONSE_TEMPLATE, result.getFormula(), result.getDiscriminant(), result.getX1(), x2Part);
            ResponseWriter.writeResponse(response, responseContent);

        } catch (QuadraticEquationException e) {
            ResponseWriter.writeExceptionResponse(response, e);
            logError(e);
        } catch (IllegalArgumentException e) {
            ResponseWriter.writeErrorResponse(response, e.getMessage());
            logError(e);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid XML request");
            logError(e);
        }
    }

    private void logError(Exception e) {
        Logger logger = LoggerFactory.getLogger(QuadraticEquationServlet.class);
        logger.error("Error processing request", e);
    }
}