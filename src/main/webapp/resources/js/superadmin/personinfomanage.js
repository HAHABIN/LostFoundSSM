var url;
$(function(){
   load(null,'sname');
});
function load(value,name){
    $('#personinfoTable').datagrid({loadFilter:pagerFilter}).datagrid('loadData', getData(value,name));
}
function getData(value,name){
    var jsonmap = {};
    if (name == 'sid'){
        jsonmap = {userId:value}
    } else if (name == 'sname') {
        jsonmap = {nickname:value}
    }

    $.ajax({
        url: "../superadmin/showpersoninfo",
        type: "POST",
        async : false,
        data: jsonmap,
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
        //判断用户类别
        if(data[i].userType == 1) {
            typeName = "用户";
        } else {
            typeName = "管理员";
        }
        //将时间戳转化为日期格式
        var createTime = gethour(data[i].createTime);
        var lastEditTime = gethour(data[i].lastEditTime);

        var a = {
            'userId' : data[i].userId,
            'nickName' : data[i].nickname,
            'gender' : data[i].gender,
            'email' : data[i].email,
            'userType' : typeName,
            'profileImg' : data[i].profileImg,
            'createTime' : createTime,
            'lastEditTime' : lastEditTime,
            'helpTimes' : data[i].helpTimes,
        };
        values.push(a);
    }
    return values;
    // $('#personinfoTable').datagrid('loadData', values);
}
//加载分页
function pagerFilter(data){
    if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
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
        onSelectPage:function(pageNum, pageSize){
            //复制也是 和最大行
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


/*图片加载*/
function imgFormater(value, row, index) {
    var profileImg = row.profileImg;
    return '<img src="' + profileImg + '" width="100px" height="60px">';
}
/*搜索*/
function doSearch(value,name){
    //请求加载数据
    $('#personinfoTable').datagrid({loadFilter:pagerFilter}).datagrid('loadData', getData(value,name));
    // alert('You input: ' + value+'('+name+')');
}

function newUser() {
    $('#EditDialog').dialog('open').dialog('setTitle','新增用户');
    $('#ModiyDialogForm').form('clear');
    url = 'save_user.php';
}

/*修改用户*/
function editUser() {
    //获取选中表行数
    var SelectRows = $("#personinfoTable").datagrid('getSelections');
    if( 1 != SelectRows.length ){
        $.messager.alert("系统提示", "请选择一行要编辑的数据");
        return;
    }
    var SelectRow = SelectRows[0];
    $("#EditDialog").dialog('open').dialog('setTitle',"修改用户信息");
    $("#ModiyDialogForm").form('load',SelectRow);
    url = "../user/updateInfo?userId="+SelectRow.userId;
}
//保存数据
function SaveDialog(){
    $("#ModiyDialogForm").form('submit',{
        url:url,
        onSubmit:function(){
            return $(this).form('validate');
        },
        success:function(result){
            if( result.success == true){
                showMessage(result.message);
                return;
            }
            else{
                showMessage(result.message);
                $("#EditDialog").dialog('close');
                $("#personinfoTable").datagrid('reload');
            }
        }
    });
    load(null,'sname')
}
//关闭弹窗
function CloseDialog(){
    $("#EditDialog").dialog('close');
}


//批量删除
function DeleteById(){
    var SelectRows = $("#personinfoTable").datagrid('getSelections');
    if( 0 == SelectRows.length ){
        $.messager.alert("系统提示", "请选择要删除的数据");
        return;
    }
    var SelectIndexArr = [];
    for( var i = 0 ; i < SelectRows.length; i++ ){
        SelectIndexArr.push(SelectRows[i].userId);
    }
    var SelectIndexToString = SelectIndexArr.join(",");
    $.messager.confirm("系统提示", "你确定要删除<font color=red> " + SelectRows.length + " </font>条数据吗?", function(xo){
        if( xo ){
            $("#personinfoTable").datagrid('reload');
            $.post("../user/batchdeleteUser",{DeleteIndexArr:SelectIndexToString},function(result){
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



