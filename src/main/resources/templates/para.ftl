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
    <li><a href="/code">代码模板管理</a></li>
    <li class="active"><a href="/para">生成参数</a></li>

</ul>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <form class="form-inline">
                        <div class="row">
                            <div class="col-xs-6">
                                <label >生成参数组合</label>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-body">
                            <div class="row">
                                <div class="col-xs-5">
                                    <label >参数元数据文件</label>
                                </div>

                                <div class="col-xs-2">
                                    <button type="button" class="btn btn-success" onclick="transfer()"> 转换</button>
                                </div>
                                <div class="col-xs-2">
                                    <button type="button" class="btn btn-success" onclick="download()"> 下载CSV</button>
                                </div>
                            </div>
                        <br>

                        <div class="row">
                            <div class="col-xs-5">
                                <textarea  class="form-control" rows="20" cols="20" id="meta" name="meta" placeholder="meta" ">${para.meta}</textarea>
                            </div>

                            <div class="col-xs-5">
                                <textarea  class="form-control" rows="20" cols="20" id="data" name="data" placeholder="data" ">${para.data}</textarea>
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
    function transfer() {
        var parameta=document.getElementById("meta").value;
        $.ajax({
            url:"/para/transfer",
            type:"post",
            data:parameta,
            contentType:"application/json; charset=utf-8",
            success:function(data){
                document.getElementById("data").value=data;
            },
            error:function(e){
                alert(e.responseText);
            }
        });
    }

    function download() {
        var parameta=document.getElementById("meta").value;
        console.log(parameta)


        var url = "/para/download";
        var form = $("<form></form>").attr("action", url).attr("method", "post");
        form.append($("<input></input>").attr("type", "hidden").attr("name", "meta").attr("value", parameta));
        form.appendTo('body').submit().remove();


    }
</script>
</body>
</html>