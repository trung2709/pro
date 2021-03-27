/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO;
import Entity.Item;
import Entity.Order;
import Entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {

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
            out.println("<title>Servlet AddToCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCart at " + request.getContextPath() + "</h1>");
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
        int pID = Integer.parseInt(request.getParameter("pID"));
        DAO dao = new DAO();
        Product p = dao.getProductById(pID);
        HttpSession ses = request.getSession();
        ses.setAttribute("discount", 0);
        if (ses.getAttribute("order") == null) {
            Order order = new Order();
            List<Item> listItems = new ArrayList<>();
            Item item = new Item();
            int itemID = 0;
            item.setId(itemID);
            item.setQuantity(1);
            item.setProduct(p);
            item.setPrice(p.getPrice());
            listItems.add(item);
            order.setListItems(listItems);
            ses.setAttribute("order", order);
        } else {
            Order order = (Order) ses.getAttribute("order");
            List<Item> listItems = order.getListItems();

            boolean isExisting = false;
            for (Item item : listItems) {
                if (item.getProduct().getId() == p.getId()) {
                    item.setQuantity(item.getQuantity() + 1);
                    isExisting = true;
                }
            }
            if (!isExisting) {
                int itemID = listItems.size();
                Item item = new Item();
                item.setId(itemID);
                item.setQuantity(1);
                item.setProduct(p);
                item.setPrice(p.getPrice());
                listItems.add(item);
            }
            order.setListItems(listItems);
            ses.setAttribute("order", order);
        }
        response.sendRedirect("Cart.jsp");
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
