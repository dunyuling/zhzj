<%--
  Created by IntelliJ IDEA.
  User: pro
  Date: 17-3-14
  Time: 上午10:42
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>广告</title>
    <%--<meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">--%>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">


</head>

<body class="gray-bg">

<div class="wrapper wrapper-content animated fadeInUp">
    <div class="row">
        <div class="col-sm-12">

            <div class="ibox">
                <div class="ibox-title">
                    <h5>所有广告</h5>
                    <div class="ibox-tools">
                        <a href="/conferenceHall_toAdd.do" class="btn btn-primary btn-xs">创建新会场门票</a>
                    </div>
                </div>
                <div class="ibox-content">
                    <%--<div class="row m-b-sm m-t-sm">
                        <div class="col-md-1">
                            <button type="button" id="loading-example-btn" class="btn btn-white btn-sm"><i
                                    class="fa fa-refresh"></i> 刷新
                            </button>
                        </div>
                        <div class="col-md-11">
                            <div class="input-group">
                                <input type="text" placeholder="请输入广告名称" class="input-sm form-control"> <span
                                    class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-primary"> 搜索</button> </span>
                            </div>
                        </div>
                    </div>--%>
                    <div class="ad-list">

                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>图片</th>
                                <th>标题</th>
                                <th>地址</th>
                                <th>所属宗教</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="conferenceHall" items="${conferenceHalls}">
                                <tr>
                                    <td class="project-people">
                                        <a href="#"><img alt="image" class="img-responsive" src="${conferenceHall.img}"></a>
                                    </td>
                                    <td class="project-status">
                                        <span class="label label-primary">${conferenceHall.name}</span>
                                    </td>
                                    <td class="project-status">
                                        <span class="label label-primary">${conferenceHall.address}</span>
                                    </td>
                                    <td class="ad-title">
                                        <a href="#">${conferenceHall.religionType}</a>
                                    </td>
                                    <td class="ad-actions">
                                        <a href="/conferenceHall_toEdit.do?id=${conferenceHall.id}"
                                           class="btn btn-white btn-sm"><i class="fa fa-pencil"></i>
                                            编辑 </a>
                                        <a href="/conferenceHall_del.do?id=${conferenceHall.id}"
                                           class="btn btn-white btn-sm"><i class="fa fa-times"></i>
                                            删除 </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 全局js -->
<script src="/js/jquery.min.js?v=2.1.4"></script>
<script src="/js/bootstrap.min.js?v=3.3.6"></script>


<!-- 自定义js -->
<script src="/js/content.js?v=1.0.0"></script>


<script>
    $(document).ready(function () {

        $('#loading-example-btn').click(function () {
            btn = $(this);
            simpleLoad(btn, true)

            // Ajax example
//                $.ajax().always(function () {
//                    simpleLoad($(this), false)
//                });

            simpleLoad(btn, false)
        });
    });

    function simpleLoad(btn, state) {
        if (state) {
            btn.children().addClass('fa-spin');
            btn.contents().last().replaceWith(" Loading");
        } else {
            setTimeout(function () {
                btn.children().removeClass('fa-spin');
                btn.contents().last().replaceWith(" Refresh");
            }, 2000);
        }
    }
</script>

<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script><!--统计代码，可删除-->

</body>
</html>