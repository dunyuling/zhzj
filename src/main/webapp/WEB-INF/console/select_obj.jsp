<%--
  Created by IntelliJ IDEA.
  User: pro
  Date: 17-3-27
  Time: 下午4:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" />
    <script src="/js/plugins/bootstrap-table/bootstrap-table.js" type="text/javascript"></script>    <script src="/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js" type="text/javascript"></script>
    <script src="/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row row-lg">
        <div class="col-sm-12">
            <table id="tab_selectGoodsCheck"
                   data-toggle="table"
                   data-url="/conferenceHall_select.do"
                   data-method="get"
                   data-click-to-select="true"
                   data-pagination="true"
                   data-data-type="json"
                   data-search-on-enter-key="true"
                   data-toolbar="#def_toolbar"
                   data-side-pagination="server"
                   data-query-params="queryParams"
                   data-mobile-responsive="true">
                <thead>
                <tr>
                    <th data-checkbox="true" data-click-to-select="">选择</th>
                    <th data-field="goodsImage" data-formatter="defParsePic">商品图片
                    </th>
                    <th data-field="goodsName">商品名称</th>
                    <th data-field="sort">分类</th>
                    <th data-field="goodsPrice">价格</th>
                    <!--  <th data-field="is_show" data-formatter="setIsShow">库存</th> -->
                    <th data-field="saleNum" >销量</th>
                    <!-- <th data-field="update_time" data-formatter="">上架时间</th> -->
                </tr>
                </thead>
            </table>
            <div class="form-group">
                <div class="col-sm-12 center-block">
                    <button class="btn btn-default btn-dlg-close">取消</button>
                    <button class="btn btn-primary btn-dlg-confirm" type="submit">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function(){
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        $('.btn-dlg-close').click(function(){
            //var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });

        $('.btn-dlg-confirm').click(function(){
            var data = $('#tab_selectGoodsCheck').bootstrapTable('getSelections');
            if(null == data){
                return;
            }
            var id=parent.frames[0].$('#goodsIds').val();
            var goodsName=parent.frames[0].$('#goodsName').val();

            $.each(data, function(i, item) {
                if(id==""){
                    id=item.id;
                    goodsName=item.goodsName;
                }else{
                    id+=","+item.id;
                    goodsName+=","+item.goodsName;
                }
            });
            console.log(goodsName);
            console.log(id);
            parent.frames[0].$('#goodsIds').val(id);
            parent.frames[0].$('#goodsName').val(goodsName);
            parent.layer.close(index);
        });
    });

</script>
</body>

</html>