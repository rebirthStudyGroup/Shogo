/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author guest1Day
 */
public class BuyAmountUpdate extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletExceptionnn if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        ArrayList<ItemDataDTO> cartList = new ArrayList<ItemDataDTO>();
        ArrayList<Integer> deleteIDs = new ArrayList<Integer>();
        try {
            UserDataBeans loginUser = (UserDataBeans)session.getAttribute("loginUser");
            int userID = loginUser.getUserID();
            cartList = new ItemDataDAO().SearchByItemCode(loginUser.getUserID(),"");
            
            int[] deleteID = new int[cartList.size()];
            for(int i=1;i<=cartList.size();i++){
                String cartID = "cartID_"+String.valueOf(cartList.get(i-1).getCartID());
                int buyAmount = Integer.parseInt( request.getParameter(cartID) );
                if(buyAmount == 0){
                    deleteID[i-1] = cartList.get(i-1).getCartID();
                    deleteIDs.add(deleteID[i-1]);
                }else{
                    cartList.get(i-1).setCount(buyAmount);
                    new ItemDataDAO().Update(userID, cartList.get(i-1));
                }
            }
            //数量が0のものを削除
            new ItemDataDAO().Delete(deleteIDs);
            
            ArrayList<ItemDataDTO> newCartList = new ArrayList<ItemDataDTO>();
            newCartList = new ItemDataDAO().SearchByItemCode(userID,"");
            
            request.setAttribute("cartList", newCartList);
            request.getRequestDispatcher("/buyconfirm.jsp").forward(request,response);
        }catch(Exception e){
             request.setAttribute("error",e.getMessage() );
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
