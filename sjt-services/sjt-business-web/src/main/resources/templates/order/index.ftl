<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>订单管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta id="viewport" name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link href="/sjt/css/bootstrap.css" rel="stylesheet">
    <link href="/sjt/css/reset.css" rel="stylesheet">
    <link href="/sjt/css/common.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="mainTitle">物流更新</div>
    <div class="orderList">
        <div class="dealTitle">订单号</div>
        <div class="dealTitle">物流单号</div>
        <div class="dealTitle">订单状态</div>
        <div class="dealTitle">支付时间</div>
        <div class="dealTitle">操作</div>
    </div>
    <div id="orderList" class="orderList">
        <div class="dealInfo">838277283278</div>
        <div class="dealInfo">838277283278</div>
        <div class="dealInfo">待发货</div>
        <div class="dealInfo">2019-08-08 23:33:52</div>
        <div class="dealBtn">
            <div class="btn btn-primary updateBtn">更新</div>
        </div>
    </div>
</div>
<div class="cssModal" style="z-index: 99999">
    <div class="modalTitle">运单填写</div>
    <div class="modelInput">
        <input type="text" id="logStaticNo">
    </div>
    <div class="modalBtn">
        <div class="modalCancle">确定</div>
        <div class="modalSure">取消</div>
    </div>
</div>
<script src="/sjt/js/jquery.min.js"></script>
<script src="/sjt/js/bootstrap.min.js"></script>
<script src="/sjt/js/line.js"></script>
<script>
    $(document).ready(function(){
        $('.updateBtn').bind("click", function() {
          $('.cssModal').show()
        })
        $('.modalSure').bind('click', function() {
            $('.cssModal').hide()
        })
    })
</script>
</body>
</html>
