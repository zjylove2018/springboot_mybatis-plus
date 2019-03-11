package cn.zjy.dayong.demo.utils;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/11/5
 * Time:10:24
 * 过滤器
 */
/*@WebFilter
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("拦截器进入========拦截器进入========");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截中========拦截中========");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        HttpSession session = request.getSession();

        //得到用户的请求
        String request_uri = request.getRequestURI();
        //得到web应用程序的上下文路径
        String ctxPath = request.getContextPath();
        // 去除上下文路径，得到剩余部分的路径
        String uri = request_uri.substring(ctxPath.length());
        //判断用户访问的是否是登录页面
        if(uri.equals("/freemark/login") ||
                uri.equals("/freemark/toLogin") ||
                uri.equals("/freemark/toRegister") ||
                uri.equals("/freemark/register")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }else {
            //如果访问的不是登录和注册页面则判断用户是否登录
            if(null != session.getAttribute("userSission") && "" != session.getAttribute("userSission")){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }else {
                //如果用户没有访问登录页面,也没有登录的话就跳转到去登录页面
                request.setAttribute("err","您还未登录,请先登录");
                wrapper.sendRedirect("/freemark/toLogin");
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("拦截器销毁========拦截器销毁========");
    }
}*/
