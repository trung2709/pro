/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO;
import Entity.Account;
import Entity.Item;
import Entity.Order;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CompleteOrder", urlPatterns = {"/CompleteOrder"})
public class CompleteOrder extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CompleteOrder</title>");
            out.println("</head>");
            out.println("<body>");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            int phone = Integer.parseInt(request.getParameter("phone"));
            HttpSession ses = request.getSession();
            double totalOrder = Double.parseDouble("" + ses.getAttribute("totalOrder"));
            Order order = (Order) ses.getAttribute("order");
            out.println("<h1>?????t H??ng Th??nh C??ng</h1><br/>");
            out.println("<h4>Th??ng Tin ????n H??ng</h4><br/>");
//            out.println("M?? ????n H??ng:  " + order.getOrderID());
            out.println("<br/>H??? T??n:  " + name);
            out.println("<br/>?????a Ch???:  " + address);
            out.println("<br/>S??? ??i???n Tho???i:  " + phone);
            out.println("<br/>S???n Ph???m:<br/>");
            for (Item o : order.getListItems()) {
                out.println(o.getProduct().getName() + " x " + o.getQuantity() + "<br/>");
            }
            out.println("T???ng ti???n: " + totalOrder + " $");
            out.println("<br/><br/>C???m ??n qu?? kh??ch ???? mua h??ng....");
            out.println("<br/><br/><a href='HomeControl'><button type='button'>Trang ch???</button></a>");

            ses.removeAttribute("order");
            DAO dao = new DAO();
            int uID = 0;
            int orderID = dao.getMaxOrderID();
            if (ses.getAttribute("acc") != null) {
                Account a = (Account) ses.getAttribute("acc");
                uID = a.getuID();
            } else {
                uID = -1;
                order.setuID(uID);
            }
            dao.loadOrderToDB(uID, phone);
            for (Item o : order.getListItems()) {
                dao.loadOrderDetailToDB(o.getProduct().getId(), o.getPrice(), o.getQuantity());
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
