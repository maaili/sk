package org.webdriver.patatiumappui.pageObject;
import java.io.IOException;
import java.io.InputStream;
import org.webdriver.patatiumappui.utils.BaseAction;
import org.webdriver.patatiumappui.utils.Locator;
import org.webdriver.patatiumappui.pageObjectConfig.PageObjectAutoCode;//就医宝APP我的页面_对象库类
public class MyPage extends BaseAction {
//用于eclipse工程内运行查找对象库文件路径
private String path="src/main/java/org/webdriver/patatiumappui/pageObjectConfig/UILibrary.xml";
 public   MyPage() {
//工程内读取对象库文件
	setXmlObjectPath(path);
getLocatorMap();
}
/***
* 立即登录
* @return
* @throws IOException
*/
public Locator 立即登录() throws IOException
 {
   Locator locator=getLocator("立即登录");
   return locator;
 }

/***
* 我知道了
* @return
* @throws IOException
*/
public Locator 我知道了() throws IOException
 {
   Locator locator=getLocator("我知道了");
   return locator;
 }
}