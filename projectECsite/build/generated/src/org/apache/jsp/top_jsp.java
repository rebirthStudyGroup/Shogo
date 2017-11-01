package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import jums.UserDataBeans;

public final class top_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        ");

        //ログイン有無の確認
        Boolean loginCheck = false;
        String name ="";
        if( session.getAttribute("loginUser")!=null){
            loginCheck = true;
            UserDataBeans udb = (UserDataBeans)session.getAttribute("loginUser");
            name=udb.getName();
        }     
        
      out.write("\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/header.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/top.css\">\r\n");
      out.write("        <link href=\"https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\" rel=\"stylesheet\" integrity=\"sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN\" crossorigin=\"anonymous\">\r\n");
      out.write("        <title>TOP 画面</title>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body class=\"body\">\r\n");
      out.write("        <div class=\"header\">");
      out.write("\r\n");
      out.write("                <p class=\"header-left\"><i class=\"fa fa-shopping-cart\" aria-hidden=\"true\"></i>shopping.com</p>");
      out.write("\r\n");
      out.write("            <div class=\"header-right\">\r\n");
      out.write("            ");
 if( loginCheck == true){ 
      out.write("\r\n");
      out.write("                <a href=\"mypage\">アカウント名：");
      out.print( name);
      out.write("</a>\r\n");
      out.write("            ");
 }else{ 
      out.write("\r\n");
      out.write("                <a href=\"login.jsp\">ログイン</a>\r\n");
      out.write("            ");
 } 
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>");
      out.write("\r\n");
      out.write("        <div class=\"body_wrapper\">");
      out.write("\r\n");
      out.write("                <h1>Top画面</h1>\r\n");
      out.write("                <a href=\"search\" class=\"toSearch\">検索画面へ</a>\r\n");
      out.write("        </div>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
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
