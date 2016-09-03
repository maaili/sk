package org.webdriver.patatiumappui.action;

import org.webdriver.patatiumappui.pageObject.HomePage;
import org.webdriver.patatiumappui.pageObject.LoginPage;
import org.webdriver.patatiumappui.pageObject.MyPage;
import org.webdriver.patatiumappui.utils.Assertion;
import org.webdriver.patatiumappui.utils.ElementAction;
import org.webdriver.patatiumappui.utils.TestBaseCase;

import java.io.IOException;

/**
 * Created by zhengshuheng on 2016/9/2 0002.
 */
public class LoginAction extends TestBaseCase {
    public  LoginAction(String username,String password) throws IOException {
        ElementAction action=new ElementAction();
        HomePage homePage=new HomePage();
        MyPage myPage=new MyPage();
        LoginPage loginPage=new LoginPage();
        action.click(homePage.我的());
        action.sleep(1);
        action.click(myPage.我知道了());
        action.click(myPage.立即登录());
        action.sleep(4);
        action.type(loginPage.用户名输入框(),username);
        //System.out.println("现在输入密码！！！");
        action.click(loginPage.密码输入框());
        action.sleep(2);
        action.type(loginPage.密码输入框(),password);
        action.sleep(1);
        action.click(loginPage.登录按钮());
    }
}
