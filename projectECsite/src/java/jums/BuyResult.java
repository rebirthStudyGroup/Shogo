/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author guest1Day
 */
public class BuyResult extends HttpServlet {

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
        HttpSession session = request.getSession();
        try  {
            UserDataBeans loginUser = (UserDataBeans)session.getAttribute("loginUser");
            int userID = loginUser.getUserID();
            ArrayList<ItemDataDTO> cartList = new ArrayList<ItemDataDTO>();
            cartList = new ItemDataDAO().SearchByItemCode(userID,"");
            
            ArrayList<Integer> cartIDs = new ArrayList<Integer>();
            for(ItemDataDTO buyingProduct : cartList){
                new BuyHistoryDataDAO().Insert(userID, buyingProduct);
                cartIDs.add(buyingProduct.getCartID());
            }
            
            //購入した製品をカートから削除する
            new ItemDataDAO().Delete(cartIDs);
                                                                                            
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            String str = sdf.format(c.getTime());
            
            request.setAttribute("now", str);
            request.setAttribute("buyingList",cartList);
            request.getRequestDispatcher("/buyresult.jsp").forward(request,response);
        }catch(SQLException e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request,response);           
        }catch(Exception e ){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request,response);
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
