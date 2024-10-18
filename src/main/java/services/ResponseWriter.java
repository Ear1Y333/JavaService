package services;

import Exceptions.QuadraticEquationException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseWriter {

    public static void writeResponse(HttpServletResponse response, String content) throws IOException {
        response.setContentType("application/xml");
        response.getWriter().write(content);
    }

    public static void writeErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
        response.setContentType("application/xml");
        response.getWriter().write("<response><error>" + errorMessage + "</error></response>");
    }

    public static void writeExceptionResponse(HttpServletResponse response, QuadraticEquationException e) throws IOException {
        response.setContentType("application/xml");
        response.getWriter().write("<response><error>" + e.getMessage() + "</error><formula>" + e.getFormula() + "</formula><D>" + e.getDiscriminant() + "</D></response>");
    }
}