var url;
var mType = [];
$(function () {
    load(null, 'sname');
});

function load(value, name) {
    //获取物品类型列表
    getType();
    //获取物品数据
    $('#articleInfoTable').datagrid({loadFilter: pagerFilter}).datagrid('loadData', getData(value, name));

}

function getData(value, name) {
    var jsonmap = {};
    if (name == 'sdescription') {
        jsonmap = {description: value,pageNo:1,pageSize:100}
    } else if (name == 'saddress') {
        jsonmap = {addressContent: value,pageNo:1,pageSize:100}
    }

    $.ajax({
        url: "../articleInfo/QueryArticleInfo",
        type: "POST",
        async: false,
        data: jsonmap,
        success: function (data) {
            var success = data.success;
            if (success == true) {
                rows = reLodadDateGrid(data.result);
            } else {// 后台异常处理
                alert(data.message);
            }

        },
        error: function (data) {
            alert('请求失败');
        }
    });
    return rows;
}

/*
 * 组装dataGrid数据
 */
function reLodadDateGrid(data) {
    var values = [];
    for (var i = 0; i < data.length; i++) {
        var typeName;
        //判断用户类别
        if (data[i].userType == 1) {
            typeName = "用户";
        } else {
            typeName = "管理员";
        }
        //将时间戳转化为日期格式
        var status = "失物";

        if (data[i].status == 2) {
            status = "拾物";
        }
        var recordStatus;
        switch (data[i].recordStatus) {
            case 1:
                recordStatus =  "丢失状态";
                break;
            case 2:
                recordStatus =  "拾物状态";
                break;
            case 3:
                recordStatus = "完成状态";
                break;
            case 4:
                recordStatus = "取消状态";
                break;
        }
        var a = {
            'id': data[i].id,
            'userId': data[i].personInfo.userId,
            'nickName': data[i].personInfo.nickname,
            'profileImg': data[i].personInfo.profileImg,
            'typeId': getTypeName(data[i].typeId),
            'addressContent': data[i].addressContent,
            'findTime': gethour(data[i].findTime),
            'description':data[i].description,
            'phone': data[i].phone,
            'status': status,
            'recordStatus': recordStatus,
            'createTime': gethour(data[i].createTime),
            'lastEditTime': gethour(data[i].lastEditTime),
            'backTime': gethour(data[i].backTime),
        };
        values.push(a);
    }
    return values;
    // $('#personinfoTable').datagrid('loadData', values);
}

//加载分页
function pagerFilter(data) {
    if (typeof data.length == 'number' && typeof data.splice == 'function') {	// is array
        data = {
            total: data.length,
            rows: data
        }
    }
    //加载当前Table
    var dg = $(this);
    var opts = dg.datagrid('options');
    var pager = dg.datagrid('getPager');
    pager.pagination({
        onSelectPage: function (pageNum, pageSize) {
            //复制也是 和最大行
            opts.pageNumber = pageNum;
            opts.pageSize = pageSize;
            pager.pagination('refresh', {
                pageNumber: pageNum,
                pageSize: pageSize
            });
            dg.datagrid('loadData', data);
        }
    });
    if (!data.originalRows) {
        data.originalRows = (data.rows);
    }
    var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
    var end = start + parseInt(opts.pageSize);
    data.rows = (data.originalRows.slice(start, end));
    return data;
}

function getType() {
    $.ajax({
        url: "../articletype/queryAll",
        type: "POST",
        async: false,
        success: function (data) {
            var success = data.success;
            if (success == true) {
                mType=data.result;
            } else {// 后台异常处理
                alert(data.message);
            }

        },
        error: function (data) {
            alert('请求失败');
        }
    });
}

function getTypeName(typeId) {
    for (var i = 0; i < mType.length; i++) {
        if (typeId == mType[i].typeId){
            return mType[i].typeName;
        }
    }
    return "其他";
}
/*图片加载*/
function imgFormater(value, row, index) {
    var profileImg = row.profileImg;
    return '<img src="' + profileImg + '" width="100px" height="60px">';
}

/*搜索*/
function doSearch(value, name) {
    //请求加载数据
    $('#articleInfoTable').datagrid({loadFilter: pagerFilter}).datagrid('loadData', getData(value, name));
    // alert('You input: ' + value+'('+name+')');
}
//批量删除
function DeleteById(){
    var SelectRows = $("#articleInfoTable").datagrid('getSelections');
    if( 0 == SelectRows.length ){
        $.messager.alert("系统提示", "请选择要删除的数据");
        return;
    }
    var SelectIndexArr = [];
    for( var i = 0 ; i < SelectRows.length; i++ ){
        SelectIndexArr.push(SelectRows[i].id);
    }
    var SelectIndexToString = SelectIndexArr.join(",");
    $.messager.confirm("系统提示", "你确定要删除<font color=red> " + SelectRows.length + " </font>条数据吗?", function(xo){
        if( xo ){
            $("#articleInfoTable").datagrid('reload');
            $.post("../articleInfo/batchdeleteArticle",{DeleteIndexArr:SelectIndexToString},function(result){
                if( result.success ){
                    $.messager.alert("系统提示", "你已成功删除 <font color=green> " + result.DeleteCounts + " </font>条数据!~");
                    load(null,'sname');

                }
                else{
                    $.messager.alert("系统提示", "<font color=red>删除失败</font>");
                }
            },"json");
        }
    });
}
