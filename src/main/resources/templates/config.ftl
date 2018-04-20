<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>匹配结果</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body>
<#--<style>-->
    <#--body{min-height:20px;padding-top:20px;padding-right:10px;padding-left:10px;background: #f6f6f6;}.table{width:98%;padding-right:5px;padding-left:5px;margin-top:20px;margin-right:10px;margin-left:10px}-->
<#--</style>-->

<ul class="nav nav-tabs">
    <li><a href="/index">API录制</a></li>
    <li class="active"><a href="/config">配置管理</a></li>
    <li><a href="/code">代码模板管理</a></li>
    <li><a href="/para">生成参数</a></li>
</ul>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <form class="form-inline">
                        <div class="row">
                            <div class="col-xs-6">
                                <label >配置</label>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-body">
                        <form id="config">
                            <div class="row"><div class="col-xs-10"><label >返回内容类型（包含,分隔的某一个类型即可）</label></div> </div>

                            <div class="row">
                                <div class="col-xs-10">
                                    <input  class="form-control"  id="contentType" name="contentType" placeholder="contentType" value="${config.contentType}"></input>
                                </div>
                            </div>
                            <br>
                            <div class="row"><div class="col-xs-10"><label >请求方法（包含,分隔的某一个方法即可）</label></div> </div>
                            <div class="row">
                                <div class="col-xs-10">
                                    <input  class="form-control"  id="methods" name="methods" placeholder="methods" value="${config.methods}"></input>
                                </div>
                             </div>
                            <br>
                            <div class="row"><div class="col-xs-10"><label >请求url过滤（包含,分隔的某一段即可）</label></div> </div>
                            <div class="row">
                                <div class="col-xs-10">
                                    <input  class="form-control"  id="urls" name="urls" placeholder="urls" value="${config.urls}"></input>
                                </div>
                            </div>
                            <br>
                            <div class="row"><div class="col-xs-10"><label >代码模块名（ 如：price）</label></div> </div>
                            <div class="row">
                                <div class="col-xs-10">
                                    <input  class="form-control"  id="codeModule" name="codeModule" placeholder="codeModule" value="${config.codeModule}"></input>
                                </div>
                            </div>
                            <br>
                            <div class="row"><div class="col-xs-10"><label >项目根路径 （如： /Users/wangqingshan/app/hotel-bos-test）</label></div> </div>
                            <div class="row">
                                <div class="col-xs-10">
                                    <input  class="form-control"  id="codeRoot" name="codeRoot" placeholder="codeRoot" value="${config.codeRoot}"></input>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-xs-6">
                                    <button type="button" class="btn btn-success" onclick="sumbmit()" id="submitBTN"><span class="glyphicon glyphicon-send"></span> 更新</button>
                                </div>
                            </div>
                        </form>
                    </div>
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
<script>
    function sumbmit() {
        $.ajax({
            url:"/config/update",
            type:"post",
            data:$('#config').serialize(),
            processData:false,
            contentType:"application/x-www-form-urlencoded",
            success:function(data){
                alert(data);
            },
            error:function(e){
                alert(e.responseText);
            }
        });
    }
</script>
</body>
</html>