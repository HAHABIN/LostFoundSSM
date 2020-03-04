$(function(){
    $('#personinfoTable').datagrid({loadFilter:pagerFilter}).datagrid('loadData', getData());
});

function getData(){
    var rows = [];
    $.ajax({
        url: "showpersoninfo",
        type: "POST",
        async : false,
        success : function (data) {
            var success = data.success;
            if (success == true) {
                rows = reLodadDateGrid(data.result);
            } else {// 后台异常处理
                alert(data.message);
            }

        },
        error : function (data){
            alert('请求失败');
        }
    });
    return rows;
}
/*
 * 组装dataGrid数据
 */
function reLodadDateGrid(data)
{
    var values = [];
    for ( var i = 0; i <data.length; i++) {
        var typeName ;
        if(data[i].userType == 1) {
            typeName = "用户";
        } else {
            typeName = "管理员";
        }
        var a = {
            'userId' : data[i].userId,
            'nickName' : data[i].nickname,
            'gender' : data[i].gender,
            'phone' : data[i].phone,
            'email' : data[i].email,
            'userType' : typeName,
            'profileImg' : data[i].profileImg,
            'createTime' : data[i].createTime,
            'lastEditTime' : data[i].lastEditTime,
            'helpTimes' : data[i].helpTimes,
        };
        values.push(a);
    }
    return values;
    // $('#personinfoTable').datagrid('loadData', values);
}
function pagerFilter(data){
    if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
        data = {
            total: data.length,
            rows: data
        }
    }
    var dg = $(this);
    var opts = dg.datagrid('options');
    var pager = dg.datagrid('getPager');
    pager.pagination({
        onSelectPage:function(pageNum, pageSize){
            opts.pageNumber = pageNum;
            opts.pageSize = pageSize;
            pager.pagination('refresh',{
                pageNumber:pageNum,
                pageSize:pageSize
            });
            dg.datagrid('loadData',data);
        }
    });
    if (!data.originalRows){
        data.originalRows = (data.rows);
    }
    var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
    var end = start + parseInt(opts.pageSize);
    data.rows = (data.originalRows.slice(start, end));
    return data;
}
/**
 * 设置操作列的信息 参数说明 value 这个可以不管，但是要使用后面 row 和index 这个参数是必须的 row 当前行的数据 index
 * 当前行的索引 从0 开始
 */
function optFormater(value, row, index) {
    var userName = row.name;
    var userId = row.userId;
    var enableStatus = row.enableStatus;
    var params = userId + "," + enableStatus + ",'" + userName + "'";
    var edit = '<a href="javascript:openDialog_edit(' + params + ')">编辑</a>';
    return edit;
};

function imgFormater(value, row, index) {
    var profileImg = row.profileImg;
    return '<img src="' + profileImg + '" width="100px" height="60px">';
}
