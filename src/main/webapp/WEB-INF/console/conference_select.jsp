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
                <div class="ibox-content">
                    <div class="ad-list">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>选择</th>
                                <th>图片</th>
                                <th>名称</th>
                                <th>地址</th>
                                <th>所属宗教</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="conferenceHall" items="${conferenceHalls}" varStatus="status">
                                <tr>
                                    <td class="project-people">
                                        <c:choose>
                                            <c:when test="${toValidIds != null && not empty toValidIds }">
                                                <c:set var="match" value="false"/>
                                                <c:forEach var="validId" items="${toValidIds}" varStatus="status1">
                                                    <c:if test="${validId == conferenceHall.id}">
                                                        <c:set var="match" value="true"/>
                                                    </c:if>
                                                </c:forEach>
                                                <c:choose>
                                                    <c:when test="${match}">
                                                        <input type="checkbox" disabled
                                                               value="${conferenceHall.id}_${conferenceHall.name}"
                                                               name="id"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="checkbox"
                                                               value="${conferenceHall.id}_${conferenceHall.name}"
                                                               name="id"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="checkbox" value="${conferenceHall.id}_${conferenceHall.name}"
                                                       name="id"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="project-people">
                                        <a href="#"><img alt="image" class="img-responsive" src="${conferenceHall.img}"></a>
                                    </td>
                                    <td class="project-status">
                                        <span class="label label-primary"
                                              name="name">${conferenceHall.name}</span>
                                    </td>
                                    <td class="project-status">
                                        <span class="label label-primary">${conferenceHall.address}</span>
                                    </td>
                                    <td class="ad-title">
                                        <a href="#">${conferenceHall.religionType}</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <input type="button" value="确定" name="ensure" id="ensure"/>
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
<script type="text/javascript">
    $(function () {
        $("#ensure").click(function () {
            $("input[name^='id']:checked").each(function (i, item) {
                var id_ = item.value;
                var name = id_.substr(id_.indexOf('_') + 1, id_.length);
                var id = id_.substr(0, id_.indexOf('_'));

                var toAppend = "<tr class='col-sm-7'><td>" +
                    "<input type='hidden' name='select_obj_id' value='" + id + "'>" +
                    "<input type='text' name='select_obj_name' value='" + name + "'>" +
                    "<a href='#times' id='select_obj_to_del'><i class='fa fa-close'></i> 删除</a>" +
                    "</td></tr>";
                //TODO iframe5 maybe change
                parent.frames.iframe5.$("#select_obj_table").append(toAppend);
            });
            parent.layer.closeAll();
        });
    });
</script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script><!--统计代码，可删除-->

</body>
</html>