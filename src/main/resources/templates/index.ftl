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
<#--<style>-->
    <#--body{min-height:20px;padding-top:20px;padding-right:10px;padding-left:10px;background: #f6f6f6;}.table{width:98%;padding-right:5px;padding-left:5px;margin-top:20px;margin-right:10px;margin-left:10px}-->
    <#--.ellipsis{-->
        <#--overflow: hidden;-->
        <#--text-overflow: ellipsis;-->
        <#--display: -webkit-box;-->
        <#---webkit-line-clamp: 5;-->
        <#---webkit-box-orient: vertical;-->
    <#--}-->
<#--</style>-->
<ul class="nav nav-tabs">
    <li class="active"><a href="/index">API录制</a></li>
    <li><a href="/config">配置管理</a></li>
    <li><a href="/code">代码模板管理</a></li>
    <li><a href="/para">生成参数</a></li>

</ul>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <form class="form-inline">
                        <div class="row">ll
                            <div class="col-xs-6">
                                <label >录制列表</label>
                                <button type="button" class="btn btn-success" onclick="clearAll();javascript:window.location.href='index'">清空</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <#--<th>id</th>-->
                            <#--<th>时间</th>-->
                                <th>操作</th>
                                <th>方法</th>
                            <th>路径</th>
                            <th>返回值</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list records as record >
                        <tr>
                        <#--<td>${record.id}</td>-->
                        <#--<td>${record.createTime}</td>-->
                            <td>
                                <button type="button" class="btn btn-success" onclick="javascript:window.open ('api/'+${record.id}, 'API结果', 'height=600, width=800, top=100, left=100,toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no')">查看</button>
                                <button type="button" class="btn btn-success" onclick="writeFile(${record.id});">保存</button>
                                <button type="button" class="btn btn-success" onclick="writeData(${record.id});">保存用例数据</button>
                                <button type="button" class="btn btn-success" onclick="writeSchema(${record.id});">保存Schema</button>
                                <button type="button" class="btn btn-success" onclick="del(${record.id});javascript:window.location.href='index'">删除</button>
                            </td>
                        <td>${record.request.method}</td>
                        <td>${record.request.path}</td>
                        <td class="ellipsis">${record.response.body}</td>

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
                    <p>有问题大象联系王庆山</p>
                </div>
            </div>
        </div>
    </footer>
</body>
<script>
    function myrefresh(){window.location.reload();}
    setTimeout('myrefresh()',5000); //指定2秒刷新一次


function writeSchema(id) {
        $.ajax({
            url:"api/"+id+"/writeSchema",
            type:"get",
            success:function(data){
                alert(data);
            },
            error:function(e){
                alert(e.responseText);
            }
        });
    }

    function writeData(id) {
        $.ajax({
            url:"api/"+id+"/writeData",
            type:"get",
            success:function(data){
                alert(data);
            },
            error:function(e){
                alert(e.responseText);
            }
        });
    }

    function writeFile(id) {
        $.ajax({
            url:"api/"+id+"/write",
            type:"get",
            success:function(data){
                alert(data);
            },
            error:function(e){
                alert(e.responseText);
            }
        });
    }
    function del(id) {
        $.ajax({
            url:"api/"+id+"/del",
            type:"get",
            success:function(data){
                alert(data);
            },
            error:function(e){
                alert(e.responseText);
            }
        });
    }
    function clearAll() {
        $.ajax({
            url:"api/clear",
            type:"get",
            success:function(data){
                alert(data);
            },
            error:function(e){
                alert(e.responseText);
            }
        });
    }
</script>
</html>