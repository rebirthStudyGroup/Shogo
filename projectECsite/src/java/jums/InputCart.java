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
public class InputCart extends HttpServlet {

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
        String itemCode="";
        HttpSession session = request.getSession();
        UserDataBeans udb = (UserDataBeans)session.getAttribute("loginUser");
        if(udb==null){
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else{
            int userID = udb.getUserID();
            //表示していた商品ページのアイテムリストを入手し、そのあと、リスト内で何番目に表示されてるかを入手する
            ArrayList<ItemDataBeans> ibList = new ArrayList<ItemDataBeans>();
            ibList = (ArrayList<ItemDataBeans>)session.getAttribute("result");
            int itemNum = Integer.parseInt( request.getParameter("itemNum") );
            //まだログインしてない場合
            if(udb==null){
                session.setAttribute("itemNum", itemNum);
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }else{
                try {
                    //カート内の商品一覧を取得
                    ArrayList<ItemDataDTO> cartList = new ArrayList<ItemDataDTO>();
                    cartList =new ItemDataDAO().SearchByItemCode(userID, "");
                    request.setAttribute("cartList",cartList);

                    itemCode=ibList.get(itemNum).getName();
                    ArrayList<ItemDataDTO> itemList = new ArrayList<ItemDataDTO>();
                    //【以下検索メソッドの戻り値を配列にした理由】
                    //別のクラスで複数の商品を検索するため
                    //アイテム名で商品検索場合は、1商品に対して1つの行しか返さないため、itemlistの要素数は0か1
                    itemList=new ItemDataDAO().SearchByItemCode(userID, itemCode);

                    //まだカートに入っていない商品を追加する場合
                    if(itemList.size()==0){
                        ItemDataDTO newItemInCart = new ItemDataDTO();
                        ibList.get(itemNum).UDDMapping(newItemInCart);
                        newItemInCart.setCount(1);//★★１つ追加じゃなくてたくさん追加できるようにする
                        new ItemDataDAO().Insert(userID,newItemInCart);
                        request.setAttribute("addedProduct", newItemInCart);
                    //既にカートに存在する商品を追加する場合
                    }else{
                        int numberInCart=0;
                        numberInCart=itemList.get(0).getCount();
                        numberInCart++;//★★１つ追加じゃなくてたくさん追加できるようにする
                        itemList.get(0).setCount(numberInCart);
                        new ItemDataDAO().Update(userID, itemList.get(0));
                        request.setAttribute("addedProduct", itemList.get(0));
                    }

                    //inputcart->addedProduct,cartList
                    request.getRequestDispatcher("/inputcart.jsp").forward(request,response);
                }catch(Exception e){
                    request.setAttribute("error",e.getMessage() );
                    request.getRequestDispatcher("/error.jsp").forward(request,response);
                }
            }
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
