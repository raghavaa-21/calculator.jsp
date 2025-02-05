package hexacore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CalculatorServlet")
public class CalculatorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            double num1 = Double.parseDouble(request.getParameter("num1"));
            double num2 = Double.parseDouble(request.getParameter("num2"));
            String operation = request.getParameter("operation");
            String symbol = "";
            double result = 0;
            boolean error = false;
            String errorMessage = null;

            switch (operation) {
                case "add":
                    result = num1 + num2;
                    symbol = "+";
                    break;
                case "subtract":
                    result = num1 - num2;
                    symbol = "-";
                    break;
                case "multiply":
                    result = num1 * num2;
                    symbol = "×";
                    break;
                case "divide":
                    if (num2 == 0) {
                        error = true;
                        errorMessage = "Cannot divide by zero";
                    } else {
                        result = num1 / num2;
                        symbol = "÷";
                    }
                    break;
                default:
                    error = true;
                    errorMessage = "Invalid operation!";
            }

            // Get session
            HttpSession session = request.getSession();
            List<String> history = (List<String>) session.getAttribute("history");
            
            if (history == null) {
                history = new ArrayList<>();
            }

            if (!error) {
                String calculation = num1 + " " + symbol + " " + num2 + " = " + String.format("%.2f", result);
                history.add(calculation);
                session.setAttribute("history", history);
                response.sendRedirect("index.jsp?result=" + String.format("%.2f", result));
            } else {
                response.sendRedirect("index.jsp?error=" + errorMessage);
            }

        } catch (NumberFormatException e) {
            response.sendRedirect("index.jsp?error=Invalid input! Please enter numbers.");
        }
    }
}

