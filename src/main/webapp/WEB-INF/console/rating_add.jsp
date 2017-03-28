<%--
  Created by IntelliJ IDEA.
  User: pro
  Date: 17-3-14
  Time: 上午11:36
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>添加评选</title>
    <%--<meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">--%>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">
    <%@taglib prefix="cs" tagdir="/WEB-INF/tags" %>
</head>

<body id="content" class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>完整验证表单</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" enctype="multipart/form-data"
                          method="post" action="/rating_add.do">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">名称：</label>
                            <div class="col-sm-7">
                                <input name="name" class="form-control" type="text" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">选择图片：</label>
                            <div class="col-sm-7">
                                <input name="img" type="file" required
                                       aria-required="true" aria-invalid="false" class="valid">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">所属宗教：</label>
                            <div class="col-sm-7">
                                <select id="religionType" class="form-control" name="religionType">
                                    <option>佛教</option>
                                    <option>道教</option>
                                    <option>基督教</option>
                                    <option>天主教</option>
                                    <option>伊斯兰教</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">评比介绍：</label>
                            <div class="col-sm-8">
                                <cs:wordedit name="评比介绍"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">评选类型：</label>
                            <div class="col-sm-7">
                                <select id="rt" class="form-control" name="rt">
                                    <option>会场</option>
                                    <option>人员</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">选择对象：</label>
                            <div class="col-sm-7">
                                <input type="button" value="选择" name="select_obj" id="select_obj"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"></label>
                            <table id="select_obj_table">

                            </table>
                        </div>
                        <div class="form-group">
                            <div class="form-group">
                                <div class="col-sm-7 col-sm-offset-3">
                                    <button id="submit" class="btn btn-primary" type="submit">提交</button>
                                </div>
                            </div>
                        </div>
                    </form>
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

<!-- jQuery Validation plugin javascript-->
<script src="/js/plugins/validate/jquery.validate.min.js"></script>
<script src="/js/plugins/validate/messages_zh.min.js"></script>
<script type="text/javascript" src="/js/plugins/layer/layer.min.js"></script>

<script src="/js/demo/form-validate-demo.js"></script>

<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
<!--统计代码，可删除-->

<script>
    $(function () {

        $("#select_obj").click(function () {
            var rt = $("#rt").val();
            parent.layer.open({
                type: 2,
                area: ['800px', '800px'],
                fixed: false, //不固定
                resize: true,
                maxmin: true,
                shade: 0.6,
                title: '选择' + rt,
                content: rt=='会场'?'/conferenceHall_select.do':'/ad.do'
            });
        });

        $("#select_obj_table").on('click','a',function () {
            $(this).parent().parent('tr').remove();
        });
    });
</script>
</body>

</html>