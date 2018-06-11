package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFilter implements Filter{


    public static void addPatterns(){
        Pattern pattern = Pattern.compile("moban2150/login.html");
        patterns.add(pattern);
        pattern = Pattern.compile(".*in");
        patterns.add(pattern);
        pattern = Pattern.compile(".*out");
        patterns.add(pattern);
//        pattern = Pattern.compile("/.*/.*");
//        patterns.add(pattern);
        pattern = Pattern.compile(".*ico");
        patterns.add(pattern);
        pattern = Pattern.compile("moban2150/css/.*");
        patterns.add(pattern);
        pattern = Pattern.compile("moban2150/fonts/.*");
        patterns.add(pattern);
        pattern = Pattern.compile("moban2150/js/.*");
        patterns.add(pattern);
        pattern = Pattern.compile("moban2150/images/.*");
        patterns.add(pattern);
        pattern = Pattern.compile("moban2150/ssi-uploader/.*");
        patterns.add(pattern);
    }
    /**
     * 封装，不需要过滤的list列表
     */
    protected static List<Pattern> patterns = new ArrayList<Pattern>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException  {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (url.startsWith("/") && url.length() > 1) {
            url = url.substring(1);
        }
        if (isInclude(url)){
            chain.doFilter(httpRequest, httpResponse);
        } else {
            HttpSession session = httpRequest.getSession();
            if (session.getAttribute("loginUser") != null){
                // session存在
                chain.doFilter(httpRequest, httpResponse);
            } else {
                httpResponse.sendRedirect("/moban2150/login.html");
                chain.doFilter(httpRequest, httpResponse);
            }
        }

    }

    @Override
    public void destroy() {

    }

    /**
     * 是否需要过滤
     * @param url
     * @return
     */
    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}
