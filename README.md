#PatatiumAppUi
<h1>简介</h1>
这是一个AppUi自动化测试框架，由webdriver中文社区创办人土豆(本人技术笔名)所创建,该APP自动化测试框架是用java语言编写的，基于selenium webdriver Appium的开源自动化测试框架，该框架结合了testng,selenium,webdriver，Appium,jxl，jodd-http 等工具。该框架基于页面对象模型（POM）架构，实现了关键字驱动技术，数据驱动,无需掌握多少编程知识即可编写脚本，同时实现了数据与代码分离的功能：1、元素定位信息保存在对象库文件中 2、测试用例数据可以存储在excel中。从而实现，页面元素位置变化，无需改动脚本，只需修改对应的元素定位信息即可。
该框架实现了检查点及用例失败自动截图功能，自动生成html测试报告及自动发送html邮件测试报告功能。
目前框架还不是特别完善，还需要写一些脚本实现自动化；学习该框架需要熟悉一定的安卓APP 和java基础，后续可以考虑自动编码的实现
<h1>主要功能</h1>
1、实现关键字驱动技术，编写用例简单
2、实现数据驱动技术，减少用例代码
3、支持元素对象库管理，页面元素信息与代码分离
3、支持检查点、用例断言设置
4、检查点失败截图，一个检查点失败不影响用例后续执行
5、用例失败自动截图
6、用例之间依赖少，可以自由组合测试用例执行
7、支持安卓系统常用触摸操作
8、支持APP控件常用操作
9、用例集执行完毕自动生成简介美观的html报告
10、用例执行完毕自动发送详实的html邮件报告，可拓展为有失败用例才发送。
<h1>环境配置</h1>
1、JDK1.8
2、IDEA\Eclipse
3、Android SDK 具体安装参考：http://www.webdriver.org/article-52-1.html
4、一台安卓手机或者安卓模拟器，推荐夜神安卓模拟器，下载地址：http://www.yeshen.com/
5、Appium Server端，下载地址：http://pan.baidu.com/s/1jIxzSfO
<h1>Demo演示</h1>
Demo演示视频地址：http://v.youku.com/v_show/id_XMTcxMTY1MzE0NA==.html?beta&
<h3>一、创建对象库</h3>
1、通过Android SDK工具 uiautomatorviewer.bat 获取app元素定位信息，具体使用参考：http://www.webdriver.org/article-53-1.html
2、UILibrary.xml 对象库文件编写
```
<?xml version="1.0" encoding="UTF-8"?>
<!--整个对象库文件的根目录，管理整个项目的对象-->
<map>
    <!--管理一个页面的元素（webelement：input,select,textare,a,li等标签），一个page包含多个locator对象
    Pagename:page对象名字，格式：org.webdriver.patatiumappui.pageObject.xxxPage;最后面那位才是真正的页面名字，前面的是java对象库路径；
    另外注意，页面名字是头个单词大写；例如主页：名字定义为 org.webdriver.patatiumappui.pageObject.HomePage
    Value：页面对象的URL，可不填。
    Desc:页面对象中文描述-->
    <page pagename="org.webdriver.patatiumappui.pageObject.StartPage" value="" desc="微信APP启动首页">
        <!--管理一个页面的元素（webelement：input,select,textare,a,li等标签），一个page包含多个locator对象
        Type：定位方式，包含id,name,class,linktext,xpath,css等，定位元素的时候灵活使用，一般可以统一用xpath
        代替id,name,class，linktext的定位方式。
        Timeout：元素加载时间，有些页面元素，可能要等待一段时间才能加载过来，为了查找元素的稳定性，需加等待时间。
        Value:元素定位信息，如果是id,name,class，linktext直接把网页元素对应的这些属性值写上即可，如果是xpath定位方式，
        需要填写正确的xpath语法格式。
        Desc:元素的描述，元素的中文描述信息-->
		<locator type="id" timeout="3" value="com.tencent.mm:id/c4k"  desc="登录">登录</locator>
		<locator type="id" timeout="3" value="com.tencent.mm:id/cuh"  desc="注册">注册</locator>
	</page>
	<page pagename="org.webdriver.patatiumappui.pageObject.LoginPage" value="" desc="微信App登录页面">
	   <locator type="id" timeout="3" value="com.tencent.mm:id/b6c"  desc="使用其他方式登录">使用其他方式登录</locator>
		<locator type="id" timeout="3" value="com.tencent.mm:id/b5r"  desc="账号">账号输入框</locator>
		<locator type="id" timeout="3" value="com.tencent.mm:id/b5s"  desc="密码">密码输入框</locator>
		<locator type="id" timeout="3" value="com.tencent.mm:id/b5t"  desc="登录">登录按钮</locator>
		<locator type="id" timeout="3" value="com.tencent.mm:id/avt"  desc="失败提示信息确认按钮">登录失败提示信息</locator>
		<locator type="id" timeout="3" value="com.tencent.mm:id/bim"  desc="失败提示信息确认按钮">登录失败确认按钮</locator>
    </page>
</map>
```
编写完后，运行/src/main/java/org/webdriver/patatiumappui/PageObjectConfig/PageObjectAutoCode.java 文件生成对象库java代码
<h3>二、公共action封装实例（业务操作）</h3>
```
package org.webdriver.patatiumappui.action;

import org.webdriver.patatiumappui.pageObject.LoginPage;
import org.webdriver.patatiumappui.utils.ElementAction;
import org.webdriver.patatiumappui.utils.TestBaseCase;

import java.io.IOException;

/**
 * Created by zhengshuheng on 2016/9/2 0002.
 */
public class LoginAction extends TestBaseCase {
    public  LoginAction(String username,String password) throws IOException {
        ElementAction action=new ElementAction();
        LoginPage loginPage=new LoginPage();
        action.click(loginPage.账号输入框());
        action.clear(loginPage.账号输入框());
        action.type(loginPage.账号输入框(),username);
        action.click(loginPage.密码输入框());
        action.clear(loginPage.密码输入框());
        action.type(loginPage.密码输入框(),password);
        action.sleep(1);
        action.click(loginPage.登录按钮());
    }
}

```
<h3>驱动数据来源实例</h3>
在src/main/resources/data下创建loginData.xls文件
![输入图片说明](http://git.oschina.net/uploads/images/2016/0903/210055_8e091e1d_482055.png "在这里输入图片标题")