package purple.sakura.community.interceptor.customization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import purple.sakura.community.model.CommunityUser;
import purple.sakura.community.service.CommunityUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private CommunityUserService communityUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();
        String token = null;
        // 寻找cookie
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null) {
            CommunityUser communityUser = new CommunityUser();
            communityUser.setToken(token);

            if (communityUserService.userIsExist(communityUser)) {
                communityUser = communityUserService.selectByToken(token);


                request.getSession().setAttribute("user", communityUser);
            }
        }

        System.out.println("+++++++++++++++++++++ COOKIE检查 ++++++++++++++++++++++++");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
