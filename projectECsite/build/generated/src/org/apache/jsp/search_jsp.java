package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import jums.Common;
import java.util.LinkedHashMap;
import java.util.Map;

public final class search_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        ");

        LinkedHashMap<String,String> category = new LinkedHashMap<String,String>();
        category = (LinkedHashMap<String,String>)request.getAttribute("category");
        LinkedHashMap<String,String> sort = new LinkedHashMap<String,String>();
        sort = (LinkedHashMap<String,String>)request.getAttribute("sort");
        
        
      out.write("\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>商品を検索する</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>ようこそ日本最大のショッピングサイトへ</h1>\n");
      out.write("            <form action=\"Search\" method=\"get\">\n");
      out.write("                <select name=\"category_id\" style=\"float:left\">\n");
      out.write("                    ");
 for(Map.Entry<String,String> hs: category.entrySet() ){ 
      out.write("\n");
      out.write("                        <option value='");
      out.print( hs.getKey() );
      out.write('\'');
      out.write('>');
      out.print( hs.getValue() );
      out.write("</option>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                </select>\n");
      out.write("                <select name=\"sort\" style=\"float:left\">\n");
      out.write("                    ");
 for(Map.Entry<String,String> hs: sort.entrySet() ){ 
      out.write("\n");
      out.write("                        <option value='");
      out.print( hs.getKey() );
      out.write('\'');
      out.write('>');
      out.print( hs.getValue() );
      out.write("</option>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                </select>\n");
      out.write("                <input type=\"text\" name=\"query\" style=\"float:left\">\n");
      out.write("                <input type=\"submit\" value=\"Yahooショッピングで検索\">\n");
      out.write("            </form>\n");
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
