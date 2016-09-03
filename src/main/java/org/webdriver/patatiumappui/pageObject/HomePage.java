package org.webdriver.patatiumappui.pageObject;
import java.io.IOException;
import java.io.InputStream;
import org.webdriver.patatiumappui.utils.BaseAction;
import org.webdriver.patatiumappui.utils.Locator;
import org.webdriver.patatiumappui.pageObjectConfig.PageObjectAutoCode;//就医宝APP首页_对象库类
public class HomePage extends BaseAction {
//用于eclipse工程内运行查找对象库文件路径
private String path="src/main/java/org/webdriver/patatiumappui/pageObjectConfig/UILibrary.xml";
 public   HomePage() {
//工程内读取对象库文件
	setXmlObjectPath(path);
getLocatorMap();
}
/***
* 我的
* @return
* @throws IOException
*/
public Locator 我的() throws IOException
 {
   Locator locator=getLocator("我的");
   return locator;
 }
}