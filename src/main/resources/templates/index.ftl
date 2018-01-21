<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>API录制</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top" style="background: #96b97d;">
    <div class="container">
        <#--<div class="navbar-header logo"><h1>-->
            <#--<a target="_blank" href="/" style="display: block;">API录制</a></h1>-->
        <#--</div>-->
        <#--<div class="navbar-header logo"><h1>-->
            <#--<a target="_blank" href="/" style="display: block;">API录制2</a></h1>-->
        <#--</div>-->
        <h3>API录制</h3>
    </div>
</nav>
<div class="container" >
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <form class="form-inline">
                        <div class="row">
                            <div class="col-xs-6">
                                <label >录制列表</label>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>时间</th>
                            <th>方法</th>
                            <th>路径</th>
                            <th>返回值</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list records as record >
                        <tr>
                        <td>${record.id}</td>
                        <td>${record.createTime}</td>
                        <td>${record.request.method}</td>
                        <td>${record.request.path}</td>
                        <td>${record.response.body}</td>
                        <td><button type="button" class="btn btn-success" onclick="javascript:window.open ('api/'+${record.id}, 'API结果', 'height=600, width=800, top=100, left=100,toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no')">处理</button></td>
                        <tr>
                        </#list>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>

    </div>
    <footer>
        <div class="row">
            <div class="col-sm-12">
                <div style="text-align: center;">

                    <hr>

                    <p><a target="_blank" href="https://wiki.sankuai.com/pages/viewpage.action?pageId=1172686686">测试流程wiki</a>,有问题大象联系王庆山</p>
                </div>
            </div>
        </div>
    </footer>
</div>
</body>
</html>