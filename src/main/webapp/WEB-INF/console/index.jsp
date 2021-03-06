<%--
  Created by IntelliJ IDEA.
  User: pro
  Date: 17-3-6
  Time: 下午6:14
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>首页</title>

    <%--<meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">--%>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico">
    <link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <%--<ul class="nav" id="side-menu">
                <li>--%>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="/ad.do">广告管理</a>
                        </li>
                        <li><a class="J_menuItem" href="/information.do?ip=GOV">政府资讯管理</a>
                        </li>
                        <li><a class="J_menuItem" href="/information.do?ip=RELIGION">宗教资讯管理</a>
                        </li>
                        <li><a class="J_menuItem" href="/product.do">宗教产品管理</a>
                        </li>
                        <li><a class="J_menuItem" href="/conferenceHall.do">会场管理</a>
                        </li>
                        <li><a class="J_menuItem" href="/rating.do">评比管理</a>
                        </li>
                        <li><a class="J_menuItem" href="/creed.do">教义管理</a>
                        </li>
                        <li><a class="J_menuItem" href="/scripture.do">经文管理</a>
                        </li>
                        <li><a class="J_menuItem" href="faq.html">用户管理</a>
                        </li>
                        <li><a class="J_menuItem" href="calendar.html">通知管理</a>
                        </li>
                        <li><a class="J_menuItem" href="pin_board.html">权限管理</a>
                        </li>
                        <li><a class="J_menuItem" href="404.html">404页面</a>
                        </li>
                        <li><a class="J_menuItem" href="500.html">500页面</a>
                        </li>
                        <li><a class="J_menuItem" href="empty_page.html">空白页</a>
                        </li>
                    </ul>
                <%--</li>
            </ul>--%>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                    <form role="search" class="navbar-form-custom" method="post" action="search_results.html">
                        <div class="form-group">
                            <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">
                        </div>
                    </form>
                </div>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive"><a>定位当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
            <a href="/logout.do" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="../../index_v1.html" frameborder="0" data-id="" seamless></iframe>
        </div>
        <div class="footer">
            <div class="pull-right">版权所有:郑州爱峰科技有限公司</div>
        </div>
    </div>
    <!--右侧部分结束-->
</div>

<!-- 全局js -->
<script src="/js/jquery.min.js?v=2.1.4"></script>
<script src="/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="/js/plugins/layer/layer.min.js"></script>

<!-- 自定义js -->
<script src="/js/hplus.js?v=4.1.0"></script>
<script type="text/javascript" src="/js/contabs.js"></script>

<!-- 第三方插件 -->
<script src="/js/plugins/pace/pace.min.js"></script>

</body>

</html>