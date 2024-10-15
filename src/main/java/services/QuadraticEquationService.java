package services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import models.QuadraticEquationResponse;

@WebService
public interface QuadraticEquationService {
    @WebMethod
    public QuadraticEquationResponse solveEquation(double a,
                                                   double b,
                                                   double c);
}

