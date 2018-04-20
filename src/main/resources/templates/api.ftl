<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>API解析结果</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" >
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <form class="form-inline">
                        <div class="row">
                            <div class="col-xs-6">
                                <label >API接口定义</label>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-body">
                <textarea  class="form-control" rows="10" cols="20" >${api.apiDef}</textarea>
                </div>
            </div>
        </div>

        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <form class="form-inline">
                        <div class="row">
                            <div class="col-xs-6">
                                <label >API接口方法</label>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-body">
                    <textarea  class="form-control" rows="10" cols="20" >${api.apiMethod}</textarea>
                </div>
            </div>
        </div>
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <form class="form-inline">
                        <div class="row">
                            <div class="col-xs-6">
                                <label >API请求参数</label>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-body">
                    <textarea class="form-control"  rows="10" cols="20" >${api.query}</textarea>
                </div>
            </div>
        </div>
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <form class="form-inline">
                        <div class="row">
                            <div class="col-xs-6">
                                <label >API返回值JsonSchema
                                </label>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-body">
                    <textarea  class="form-control"  rows="10" cols="20" >${api.schema}</textarea>
                </div>
            </div>
        </div>
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <form class="form-inline">
                        <div class="row">
                            <div class="col-xs-6">
                                <label >API调用方法</label>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-body">
                    <textarea  class="form-control" rows="10" cols="20" >${api.method}</textarea>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>