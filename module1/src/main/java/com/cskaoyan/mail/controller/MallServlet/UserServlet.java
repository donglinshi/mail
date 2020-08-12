package com.cskaoyan.mail.controller.MallServlet;

import com.cskaoyan.mail.model.Result;
import com.cskaoyan.mail.model.User;
import com.cskaoyan.mail.model.UserModify;
import com.cskaoyan.mail.model.bo.AdminLoginBO;
import com.cskaoyan.mail.model.bo.UserInfoBO;
import com.cskaoyan.mail.model.bo.UserUpdatePwdBO;
import com.cskaoyan.mail.model.vo.UserInfoVO;
import com.cskaoyan.mail.model.vo.UserName;
import com.cskaoyan.mail.model.vo.UserReturnVO;
import com.cskaoyan.mail.service.UserService;
import com.cskaoyan.mail.service.UserServiceImpl;
import com.cskaoyan.mail.utils.HttpUtils;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 史栋林
 * @date 2020/8/10 22:04
 */
@WebServlet("/api/mall/user/*")
public class UserServlet extends HttpServlet {

    private Gson gson = new Gson();

    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/user/", "");

        if ("signup".equals(action)){
            signUp(request,response);
        }else if ("login".equals(action)){
            login(request,response);
        }else if ("updatePwd".equals(action)){
            updatePwd(request,response);
        }else if ("updateUserData".equals(action)){
            updateUserData(request,response);
        }
    }

    /**
     * @description:提交修改
     * @params:
     * @author: 史栋林
     */
    private void updateUserData(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestBody = HttpUtils.getRequestBody(request);
        UserInfoBO userInfoBO = gson.fromJson(requestBody, UserInfoBO.class);

        if (StringUtils.isEmpty(userInfoBO.getNickname()) || StringUtils.isEmpty(userInfoBO.getRecipient()) ||
                StringUtils.isEmpty(userInfoBO.getAddress()) || StringUtils.isEmpty(userInfoBO.getPhone())){
            response.getWriter().println(gson.toJson(Result.error("修改的信息不可为空！")));
            return;
        }
        //修改信息
        userService.updateData(userInfoBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * @description:用户更改密码
     * @params:
     * @author: 史栋林
     */
    private void updatePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        UserUpdatePwdBO userUpdatePwdBO = gson.fromJson(requestBody, UserUpdatePwdBO.class);
        if (StringUtils.isEmpty(userUpdatePwdBO.getOldPwd()) ||
                StringUtils.isEmpty(userUpdatePwdBO.getNewPwd()) || StringUtils.isEmpty(userUpdatePwdBO.getConfirmPwd())){
            response.getWriter().println(gson.toJson(Result.error("所有输入不能为空！")));
        }
        if (!userUpdatePwdBO.getNewPwd().equals(userUpdatePwdBO.getConfirmPwd())){
            response.getWriter().println(Result.error("新旧密码不一致！"));
        }
        int code = userService.updateUserPwd(userUpdatePwdBO);
        if (code == 0){
            response.getWriter().println(gson.toJson(Result.error("原有密码输入错误！")));
            return;
        }
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * @description:用户登录
     * @params:
     * @author: 史栋林
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);

        AdminLoginBO user = gson.fromJson(requestBody, AdminLoginBO.class);

        if (StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPwd())){
            response.getWriter().println(gson.toJson(Result.error("账号密码不可为空")));
        }

        int code = userService.login(user);
        if (code == 200){
            request.getSession().setAttribute("username",user.getEmail());

            UserName userName = userService.searchName(user.getEmail(),user.getPwd());
            response.getWriter().println(gson.toJson(Result.ok(new UserReturnVO(userName.getNickname(),userName.getNickname()))));
            return;
        }
        response.getWriter().println(gson.toJson(Result.error("账号或密码不正确")));
    }

    /**
     * @description:注册用户
     * @params:
     * @author: 史栋林
     */
    private void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        User user = gson.fromJson(requestBody, User.class);

        if (StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPwd())){
            response.getWriter().println(gson.toJson(Result.error("注册信息中email和password不可为空")));
            return;
        }
        int code = userService.signUp(user);

        if (code == 200){
            response.getWriter().println(gson.toJson(Result.ok(new UserReturnVO(user.getNickname(),user.getNickname()))));
            return;
        }else if (code == 403){
            response.getWriter().println(gson.toJson(Result.error("该账号已存在")));
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/user/", "");

        if ("data".equals(action)){
            getUserData(request,response);
        }
    }

    /**
     * @description:用户修改资料时获取数据
     * @params:
     * @author: 史栋林
     */
    private void getUserData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getParameter("token");

        UserModify user = userService.getData(token);

        response.getWriter().println(gson.toJson(Result.ok(user)));
    }
}
