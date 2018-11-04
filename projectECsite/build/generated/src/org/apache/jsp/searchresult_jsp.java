package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import jums.ItemBeans;
import java.text.NumberFormat;

public final class searchresult_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        ");

        ArrayList<ItemBeans> ibList = new ArrayList<ItemBeans>();
        ibList = (ArrayList<ItemBeans>)request.getAttribute("result");
        LinkedHashMap<String,String> params = (LinkedHashMap<String,String>)request.getAttribute("params");
        int offset = Integer.parseInt( params.get("offset") );
        params.remove("offset");
        NumberFormat nfCur = NumberFormat.getCurrencyInstance();
        
      out.write("\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/searchresult.css\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"header-left\">\n");
      out.write("                <p>shopping.com</p>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"header-right\">\n");
      out.write("                <a href=\"login.jsp\">ログイン</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <h1>検索キーワード：");
      out.print( params.get("query") );
      out.write("</h1>\n");
      out.write("        <p>検索結果：</p>\n");
      out.write("        <div class=\"body\" align=\"left\">\n");
      out.write("        ");
 for(int i = 0 ; i < 4 ; i++ ){ 
      out.write("\n");
      out.write("            <div class=\"item1\">\n");
      out.write("                <img src='");
      out.print( ibList.get(i).getImage() );
      out.write("' class=\"item1_img\">\n");
      out.write("                <p word-wrap=\"break-word\"><font color=\"#0000ff\" >");
      out.print( ibList.get(0).getName() );
      out.write("</font></p>\n");
      out.write("                <p><font color=\"#778899\" size=\"2\">");
      out.print( ibList.get(0).getBrand() );
      out.write("</font></p>\n");
      out.write("                <p>");
      out.print( nfCur.format( Integer.parseInt( ibList.get(i).getPrice() ) ) );
      out.write("</p>\n");
      out.write("            </div>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("        <form atcion=\"searchresult\" method=\"GET\">\n");
      out.write("            ");
 if( offset!=0){ 
      out.write("\n");
      out.write("                ");
 for(Map.Entry<String,String> map : params.entrySet() ){ 
      out.write("\n");
      out.write("                  <input type=\"hidden\" name='");
      out.print( map.getKey() );
      out.write("' value='");
      out.print( map.getValue() );
      out.write("'>  \n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("                <input type='hidden' name='offset' value='");
      out.print( offset - 20);
      out.write("'>\n");
      out.write("                <input type=\"submit\" value=\"<<前のページ\">\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("       </form>   \n");
      out.write("        <form atcion=\"searchresult\" method=\"GET\">\n");
      out.write("                ");
 for(Map.Entry<String,String> map : params.entrySet() ){ 
      out.write("\n");
      out.write("                  <input type=\"hidden\" name='");
      out.print( map.getKey() );
      out.write("' value='");
      out.print( map.getValue() );
      out.write("'>  \n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("                <input type='hidden' name='offset' value='");
      out.print( 20 + offset);
      out.write("'>\n");
      out.write("                <input type=\"submit\" value=\"次のページ>>\">\n");
      out.write("       </form>   \n");
      out.write("        </div>");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
