import org.dom4j.DocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.webdriver.patatiumappui.action.LoginAction;
import org.webdriver.patatiumappui.utils.Assertion;
import org.webdriver.patatiumappui.utils.ExcelReadUtil;
import org.webdriver.patatiumappui.utils.TestBaseCase;
import org.webdriver.patatiumappui.utils.XmlReadUtil;

import java.io.IOException;

/**
 * Created by zhengshuheng on 2016/9/2 0002.
 */
public class LoginTest extends TestBaseCase {
    @Test(description = "登录测试")
    public  void login() throws IOException {
        //调用登录方法(需填写正确的用户名和密码)
        new LoginAction("13026696420", "696420");
        Assertion.VerityTextPresent("郑树恒","检查是否登录成功");
        Assertion.VerityError();
    }
    //数据驱动案例--start
    @DataProvider(name="longinData")
    public Object[][] loginData()
    {
        //读取登录用例测试数据
        String filePath="src/main/resources/data/loginData.xls";
        //读取第一个sheet，第2行到第5行-第2到第4列之间的数据
        return ExcelReadUtil.case_data_excel(0, 1, 4, 1, 3,filePath);
    }
    @Test(description="登录失败用例",dataProvider = "longinData")
    public void loginFail (String userName,String password,String message) throws IOException, DocumentException {
        //调用登录方法
        new  LoginAction(userName,password);
        Assertion.VerityTextPresent(message,"验证是否出现预期的错误提示信息:"+message);
        //设置断言
        Assertion.VerityError();
    }
    //数据驱动案例--end
}
