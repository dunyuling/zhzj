<%--
  Created by IntelliJ IDEA.
  User: pro
  Date: 17-3-14
  Time: 上午11:36
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>添加产品</title>
    <%--<meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">--%>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>完整验证表单</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" enctype="multipart/form-data"
                          method="post" action="/product_edit.do">
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
                            <label class="col-sm-3 control-label">价格：</label>
                            <div class="col-sm-7">
                                <input name="price" type="text" required
                                       aria-required="true" aria-invalid="false" class="valid">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">卖家：</label>
                            <div class="col-sm-7">
                                <input name="seller" type="text" required
                                       aria-required="true" aria-invalid="false" class="valid">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">卖家电话：</label>
                            <div class="col-sm-7">
                                <input name="telephone" type="tel" required
                                       aria-required="true" aria-invalid="false" class="valid">
                            </div>
                        </div>


                        <div id="slide_zone">
                            <c:forEach var="productSlide" items="${product.productSlideSet}">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">商品图片：</label>
                                    <div class="col-lg-3">
                                        <input name="product_slide" type="file" required
                                               aria-required="true" aria-invalid="false" class="valid">
                                        <input readonly type="text" value="${productSlide.name}" />
                                    </div>
                                    <button name="product_intro_add" type="button" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                    </button>
                                </div>
                            </c:forEach>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-7 col-sm-offset-3">
                                <button id="submit" class="btn btn-primary" type="submit">提交</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<div style="visibility:hidden;" name="slide_append">
    <div class="form-group">
        <label class="col-sm-3 control-label"></label>
        <div class="col-lg-3">
            <input name="product_slide" type="file" required
                   aria-required="true" aria-invalid="false" class="valid">
        </div>
        <button name="product_intro_add" type="button" class="btn btn-outline btn-default">
            <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
        </button>
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

<script src="/js/demo/form-validate-demo.js"></script>

<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
<script type="text/javascript" src="/js/inner/product_add.js"></script>
<!--统计代码，可删除-->


</body>

</html>