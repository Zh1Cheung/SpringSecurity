## 一、项目组织结构
项目 | 作用
---|---
ecurity |聚合项目，主要控制整个项目所需依赖的版本
security-core | 认证与授权的核心模块
security-browser | 浏览器作为客户端的认证与授权模块，依赖lemon-security-core模块
security-app | 移动端作为客户端的认证与授权模块，依赖lemon-security-core模块
security-demo |案例模块，依赖lemon-security-browser和lemon-security-app模块


## 二、RESTful API

行为 | 传统API|  RESTful API | RESTful API
---|---|---|---
查询|/user/query?name=zzh|/user?name=zzh|GET
详情|/user/getInfo?id=1|/user/1|GET|
创建|/user/create?name=zzh|/user|POST|
修改|/user/update?id=1&name=tom|/user/1|POST|
删除|/user/delete?id=1|/user/1|GET|


### 常用注解
- @RestController标明此Controller提供RESTful API
- @RequestMapping及其变体（@GetMapping、PostMapping等），映射HTTP请求到Java方法
- @RequestParam映射请求参数到Java方法的参数
- @PathVariable映射URL片段到Java方法的参数
- @PageableDefault指定默认分页参数
- @JsonView按照指定方式序列化Java对象
 
 

## 常见的数据校验注解


注解 | 说明
---|---
@NotNull|值不能为空
@Null|值必须为空
@Pattern(regex=)|字符串必须匹配正则表达式
@Size(min=, max=)|集合元素数量必须在min和max之间
@CreditCardNumber(ignoreNonDigitCharaters=)|字符串必须是信用卡号（美国标准信用卡）
@Email|字符串必须是Email地址
@Length(min=, max=)|字符串长度必须在min和max之间
@NotBlank|字符串必须有字符
@NotEmpty|字符串不为null，集合必须有元素
@Range(min=, max=)|数字必须在min和max之间
@SafeHtml|字符串是安全的HTML
@URL|字符串是合法的URL
@AssertFalse|值必须是false
@AssertTrue|值必须是true
@DecimalMax(value=, @inclusive=)|如果inclusive=true，那么值必须大于等于value，如果inclusive=false，那么值必须大于value
@DecimalMin(value=, inclusive=)|如果inclusive=true，那么值必须小于等于value，如果inclusive=false，那么值必须小于value
@Digits(integer=, fraction=)|数字格式检查，integer是指整数部分最大长度，fraction是指小数部分最大长度
@Future|值必须是未来的日期
@Past|值必须是过去的日期
@Max(value=)|值必须小于等于value指定的值，不能注释在字符串类型属性上
@Min(value=)|值必须大于等于value指定的值，不能注释在字符串类型属性上


- @NotNull 任何对象的value不能为null
- @NotEmpty 集合对象的元素不为0，即集合不为空，也可以用于字符串不为null
- @NotBlank 只能用于字符串不为null，并且字符串trim()以后length要大于0
- @Valid注解在数据封装之间会对数据的合法性进行校验，并将校验的错误结果存储在BindingResult对象中。


### 自定义校验注解
- 第一步： 编写校验注解，但是需要注意的是，自定义的校验注解也得和其他Java提供的校验注解一样，必须拥**有message、groups、payload**三个属性。 
- 第二步： 编写自定义校验的逻辑实体类，这个类必须实现**ConstraintValidator**这个接口，这样才可以被注解用来校验。 
- 第三步： 编写具体的校验逻辑。


## RESTful API服务异常处理
从APP端访问我们可以使用模拟RESTful API发送器来进行发送，我这里使用的Paw软件，你可以在你的谷歌浏览器上安装Postman来进行发送。

**浏览器访问返回的是一个HTML页面，而客户端访问返回的是一个JSON数据**


Spring Boot是如何确定当前请求来自浏览器还是客户端:org.springframework.boot.autoconfigure.web中的BasicErrorController


### 自定义服务异常处理
这里仅仅介绍一种最简单的方式来处理异常，在resources文件夹下再建立一个resources文件夹，然后再在新建的resources文件夹下建立一个error，在error文件夹里面建立404.html和500.html，在访问出现404错误的时候，就会跳转到我们自己定义的HTML中，而不是Spring Boot默认的界面。


## 使用Filter、Interceptor和AOP拦截REST服务
一般情况，在访问RESTful风格的API之前，可以对访问行为进行拦截，并做一些逻辑处理，本文主要介绍三种拦截方式，分别是：过滤器Filter、拦截器Interceptor以及面向切面的拦截方式AOP。

使用过滤器进行拦截主要有**两种方式**:
- 第一种是将自定义的拦截器标注为Spring的Bean，在SpringBoot应用就可以对RESTful风格的API进行拦截。
- 第二种方式往往应用在继承第三方过滤器，这时候就需要将第三方拦截器使用FilterRegistrationBean对象进行注册即可。



## 使用Filter、Interceptor和AOP拦截REST服务

一般情况，在访问RESTful风格的API之前，可以对访问行为进行拦截，并做一些逻辑处理，本文主要介绍三种拦截方式，分别是：过滤器Filter、拦截器Interceptor以及面向切面的拦截方式AOP。

对于上面三种拦截方式，他们的执行有一个基本的顺序，进入的顺序是Filter-->Interceptor-->Aspect-->Controller-->Aspect-->Interceptor-->Filter

## 使用Swagger自动生成API文档
由于Spring Boot能够快速开发、便捷部署等特性，相信有很大一部分Spring Boot的用户会用来构建RESTful API。而我们**构建RESTfulAPI的目的通常都是由于多终端的原因**，这些终端会共用很多底层业务逻辑，因此我们会抽象出这样一层来同时服务于多个移动端或者Web前端。本文将介绍RESTful API的重磅好伙伴Swagger2，它可以轻松的整合到Spring Boot中，并与Spring MVC程序配合组织出强大**RESTfulAPI文档**。它既可以减少我们创建文档的工作量，同时说明内容又整合入实现代码中，**让维护文档和修改代码整合为一体**，可以让我们在修改代码逻辑的同时方便的修改文档说明。另外Swagger2也提供了强大的页面测试功能来调试每个RESTful API。

在Spring Boot中应用中Swagger2构建强大的API文档十分方便，只需要在项目中添加Swagger2的依赖，然后在Spring Boot的启动的main方法的类上加上注解@EnableSwagger2就可以完成构建工作。



## Spring Security的基本运行原理与个性化登录实现


正如你可能知道的两个应用程序的两个主要区域是“**认证**”和“**授权**”（或者访问控制）。这两个主要区域是Spring Security的两个目标。“认证”，是建立一个他**声明的主题的过程**（一个“主体”一般是指用户，设备或一些可以在你的应用程序中执行动作的其他系统）。“授权”指确定一个主体是否允许在你的应用程序**执行一个动作的过程**。为了抵达需要授权的店，主体的身份已经有认证过程建立。

在实际的开发中，对于用户的登录认证，不可能使用Spring Security自带的方式或者页面，需要自己定制适用于项目的登录流程

## 开发图形验证码接口

开发可重用的图形验证码接口，该接口支持用户自定义配置，比如验证码的长度、验证码图形的宽度和高度等信息。



## 开发记住我功能
“记住我”几乎在登陆的时候都会被用户勾选，因为它方便地帮助用户减少了输入用户名和密码的次数

- 首先浏览器发送登录请求，也就是认证的请求，首先会进入到UsernamePasswordAuthenticationFilter过滤器中进行验证操作，验证完成之后，这个过滤器还有一项额外的操作，那就是调用RememberMeService服务，这个服务中包含一个TokenRepository，它会生成一个Token，并且会将Token写回到浏览器的Cookie中，并使用TokenRepository将用户名和Token写入到数据库中，也就是说，用户名和Token是一一对应的。
- 当用户再次请求的时候，将不会携带用户名和密码，这时候由RememberMeAuthenticationFilter读取Cookie中的Token来进行验证操作，这时候会使用TokenRepository从数据库中根据Token来查询相关信息，最后调用UserDetailsService来登录验证操作。

###   流程分析
#### 第一次登录
- 第一步：当用户发送登录请求的时候，首先到达的是**UsernamePasswordAuthenticationFilter**这个过滤器，然后执行**attemptAuthentication方法**的代码
- 第二步：验证成功之后，将进**入AbstractAuthenticationProcessingFilter**类的**successfulAuthentication的方法**中，首先将认证信息通过代**码SecurityContextHolder.getContext().setAuthentication(authResult)**;将认证信息**存入到session中**，紧接着这个方法中就调用了**rememberMeServices的loginSuccess方法**
- 第三步：进入**rememberMeServices**的**loginSuccess方法**中，可以看出，它方法内部调用了**PersistentTokenBasedRememberMeServices**的**onLoginSuccess方法**，这个方法中调用了**tokenRepository**来**创建Token并存到数据库**中，且将Token写回到了Cookie中。到这里，基本的登录过程基本完成，生成了**Token存到了数据库**，且写回到了Cookie中。

#### 第二次登录
重启项目，这时候服务器端的session已经不存在了，但是第一次登录成功已经将Token写到了数据库和Cookie中
- 第一步：首先进入到了**RememberMeAuthenticationFilter**的**doFilter方法**中，这个方法首先检查在session中**是否存在已经验证过的Authentication**了，如果为空，就进行下面的**RememberMe的验证代码**，比如调用rememberMeServices的**autoLogin方法**，
- 第二步：然后进入**PersistentTokenBasedRememberMeService**的**processAutoLoginCookie方法**中，从请求中的Cookie中**拿到Token**，并且调用**tokenRepository**的**getTokenForSeries**从数据库中查询到Token，接下来就是进行一系列的对比验证工作。最后调用**UserDetailsService**来完成返回UserDetails的实现类对象。
- 第三步：再次返回到**RememberMeAuthenticationFilter**中将登录信息**存储到session中**，然后去访问自定义的RESTful API。
- 

## Spring Social实现QQ社交登录

![image](http://dandandeshangni.oss-cn-beijing.aliyuncs.com/github/Spring%20Security/OAuth2-Sequence.png)

1. 请求第三方应用
2. 第三方应用将用户请求导向服务提供商
3. 用户同意授权
4. 服务提供商返回code
5. client根据code去服务提供商换取令牌
6. 返回令牌
7. 获取用户信息
- Spring Social已经为我们封装好了1-6步。



在qq互联申请个人开发者，获得appId和appKey或者使用 SpringForAll贡献出来的
配置本地host 添加 127.0.0.1 www.xxx.cn
数据库执行以下sql

```
create table UserConnection (userId varchar(255) not null,
    providerId varchar(255) not null,
    providerUserId varchar(255),
    rank int not null,
    displayName varchar(255),
    profileUrl varchar(512),
    imageUrl varchar(512),
    accessToken varchar(512) not null,
    secret varchar(512),
    refreshToken varchar(512),
    expireTime bigint,
    primary key (userId, providerId, providerUserId));
create unique index UserConnectionRank on UserConnection(userId, providerId, rank);
```

### 引入Spring Social 模块
模块| 描述2
---|---
spring-social-core | 提供社交连接框架和OAuth 客户端支持
spring-social-config|提供Java 配置
spring-social-security | 社交安全的一些支持
spring-social-web|管理web应用程序的连接

- api:定义api绑定的公共接口
- config: 一些配置信息
- connect: 与服务提供商建立连接所需的一些类。
---
- [微信开放平台](https://open.weixin.qq.com/cgi-bin/frame?t=home/web_tmpl&lang=zh_CN)申请网站应用开发，获取appid和appsecret
- [网站应用微信登录开发指南](https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419316505&token=6505faac65c26a79bc1b0218aa8cd24c0e24bceb&lang=zh_CN)



## Spring Security实现短信登录
目前主流的登录方式主要有 3 种：账号密码登录、短信验证码登录和第三方授权登录。已经实现了账号密码和第三方授权登录。本章我们将使用Spring Security实现短信验证码登录。


## Spring Security 退出


### 退出原理
1. 清除Cookie
2. 清除当前用户的remember-me记录
3. 使当前session失效
4. 清空当前的SecurityContext
5. 重定向到登录界面


Spring Security的退出请求（默认为/logout）由LogoutFilter过滤器拦截处理。

## Spring Security OAuth2

**OAuth 是一个开放标准**，允许用户让第三方应用访问该用户在某一网站上存储的私密的资源（如照片，视频，联系人列表），而不需要将用户名和密码提供给第三方应用。OAuth允许用户提供一个**令牌**，而不是用户名和密码来访问他们存放在特定服务提供者的数据。每一个令牌授权一个特定的网站在特定的时段内访问特定的资源。这样，OAuth让用户可以授权第三方网站访问他们存储在另外服务提供者的某些特定信息。

### Spring Security OAuth2整合JWT
Json web token (JWT), 是为了在网络应用环境间传递声明而执行的一种基于JSON的开放标准（RFC 7519).该token被设计为紧凑且安全的，特别适用于分布式站点的单点登录（SSO）场景。JWT的声明一般被用来在身份提供者和服务提供者间传递被认证的用户身份信息，以便于从资源服务器获取资源，也可以增加一些额外的其它业务逻辑所必须的声明信息，该token也可直接被用于认证，也可被加密。

### Spring Security OAuth2基于JWT实现单点登录
单点登录(SSO)，又译为单一签入，一种对于许多相互关连，但是又是各自独立的软件系统，提供访问控制的属性。当拥有这项属性时，当用户登录时，就可以获取所有系统的访问权限，不用对每个单一系统都逐一登录。这项功能通常是以轻型目录访问协议（LDAP）来实现，在服务器上会将用户信息存储到LDAP数据库中。相同的，单一注销（single sign-off）就是指，只需要单一的注销动作，就可以结束对于多个系统的访问权限。

1. 访问client1
2. client1将请求导向sso-server
3. 同意授权
4. 携带授权码code返回client1
5. client1拿着授权码请求令牌
6. 返回JWT令牌
7. client1解析令牌并登录
8. client1访问client2
9. client2将请求导向sso-server
10. 同意授权
11. 携带授权码code返回client2
12. client2拿着授权码请求令牌
13. 返回JWT令牌
14. client2解析令牌并登录

- 用户的登录状态是由sso-server认证中心来保存的，登录界面和账号密码的验证也是sso-server认证中心来做的（client1和clien2返回token是不同的，但解析出来的用户信息是同一个用户）。


