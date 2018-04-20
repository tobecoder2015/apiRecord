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

<ul class="nav nav-tabs">
    <li><a href="/index">API录制</a></li>
    <li><a href="/config">配置管理</a></li>
    <li class="active"><a href="/code">代码模板管理</a></li>
    <li><a href="/para">生成参数</a></li>
</ul>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <form class="form-inline">
                        <div class="row">
                            <div class="col-xs-6">
                                <label >代码模板</label>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-body">
                            <div class="row">
                                <div class="col-xs-5"><label >API class</label></div>
                                <div class="col-xs-5">
                                    <button type="button" class="btn btn-success" onclick="sumbmit('apiClass')"> 模板更新</button>
                                </div>
                            </div>

                            <div class="panel-body">
                                <div class="col-xs-10">
                                    <textarea  class="form-control" rows="10" cols="20" id="apiClass" name="apiClass" placeholder="apiClass" ">${code.apiClass}</textarea>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-xs-5"><label >Get Api定义</label></div>
                                <div class="col-xs-5">
                                    <button type="button" class="btn btn-success" onclick="sumbmit('apiDefGet')"> 模板更新</button>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="col-xs-10">
                                    <textarea  class="form-control"  rows="10" cols="20" id="apiDefGet" name="apiDefGet" placeholder="apiDefGet" >${code.apiDefGet}</textarea>
                                </div>
                             </div>
                            <br>
                            <div class="row">
                                <div class="col-xs-5"><label >Post Api定义</label></div>
                                <div class="col-xs-5">
                                    <button type="button" class="btn btn-success" onclick="sumbmit('apiDefPost')"> 模板更新</button>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="col-xs-10">
                                    <textarea  class="form-control"  rows="10" cols="20" id="apiDefPost" name="apiDefPost" placeholder="apiDefPost" >${code.apiDefPost}</textarea>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-xs-5"><label >Get 请求方法</label></div>
                                <div class="col-xs-5">
                                    <button type="button" class="btn btn-success" onclick="sumbmit('apiMethodGet')" >模板更新</button>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="col-xs-10">
                                    <textarea  class="form-control"  rows="10" cols="20" id="apiMethodGet" name="apiMethodGet" placeholder="apiMethodGet" >${code.apiMethodGet}</textarea>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-xs-5"><label >Post 请求方法</label></div>
                                <div class="col-xs-5">
                                    <button type="button" class="btn btn-success" onclick="sumbmit('apiMethodPost')" > 模板更新</button>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="col-xs-10">
                                    <textarea  class="form-control"  rows="10" cols="20" id="apiMethodPost" name="apiMethodPost" placeholder="apiMethodPost" >${code.apiMethodPost}</textarea>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-xs-5"><label >Case 方法</label></div>
                                <div class="col-xs-5">
                                    <button type="button" class="btn btn-success" onclick="sumbmit('method')"> 模板更新</button>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="col-xs-10">
                                    <textarea  class="form-control" rows="10" cols="20"  id="method" name="method" placeholder="method" >${code.method}</textarea>
                                </div>
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
    function sumbmit(type) {
        var code=document.getElementById(type).value;
        var body={};
        body['type']=type;
        body['code']=code;
        $.ajax({
            url:"/code/update",
            type:"post",
            data:JSON.stringify(body),
            contentType:"application/json; charset=utf-8",
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