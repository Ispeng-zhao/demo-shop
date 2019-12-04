
var APP = function () {
    /** Icheck */
    var _masterCheckbox;
    var _checkbox;

    //定义一个存放ID的数据
    var  _idArray;

    /**
     * 私有方法，初始化选择框
     */
    var handlerCheckbox = function () {
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        })

        // 获取主选框
        _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');
        // 获取子选框
        _checkbox = $('input[type="checkbox"].minimal');

    };

    /**
     * checkbox 全选功能呢
     */
    var handlerCheckboxALL = function () {
        _masterCheckbox.on("ifClicked",function (e) {
            // true表示未选中
            if (e.target.checked){
                _checkbox.iCheck("uncheck");
            }
            // false 表示选中
            else {
                _checkbox.iCheck("check");
            }
        })
    }

    
    /**
     * 批量删除
     */
    var handlerDeleteMulti = function (url) {
        _idArray =  new Array();

        //将选中的ID存放到数组中
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id!= null && _id != "undefine" && $(this).is(":checked")){
                _idArray.push(_id);
            }
        });


        if (_idArray.length === 0 ){
            $('#modal-message').html("你还没有选择数据,请至少选择一项")
        }

        else{
            $('#modal-message').html("你确定删除这"+_idArray.length+"条数据？")
        }
        $('#modal-default').modal("show");

        $("#btnModalOk").bind("click",function () {
          del();
        });
        function del() {

            //如果没有选择项则关闭模态框
            if (_idArray.length === 0){
                $('#modal-default').modal("hide");
            }
            //删除操作
            else {
                $.ajax({
                    "url":url,
                    "type":"POST",
                    "data": {"ids" : _idArray.toString()},
                    "dataType":"JSON",
                    "success" : function (data) {
                       //删除成功
                        if (data.status === 200){
                            $('#modal-message').html(data.message)
                        }

                        else {
                            $('#modal-message').html(data.message)
                        }
                        $('#modal-default').modal("hide");
                        window.location.reload();
                    }
                })
            }
        }
    }
    /**
     * 初始化DataTables
     */
    var  handlerInitDataTables = function f(url,columns) {
        $("#dataTable").DataTable({
            "paging":true,
            "info":true,
            "lengthChanger":false,
            "ordering":false,
            "processing":true,
            "searching":false,
            "serverSide":true,
            "deferRender":true,
            "ajax":{
                "url":url
            },language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "columns":columns,
            "drawCallback": function (settings) {
                handlerCheckbox();
                handlerCheckboxALL();
            }
        });
    }

    return {
        init: function () {
            handlerCheckbox();
            handlerCheckboxALL();
        },

        getCheckbox: function () {
            return _checkbox;
        },

        deleteMulti: function (url) {
            handlerDeleteMulti(url);
        },

        initDataTables : function (url,columns) {
            handlerInitDataTables(url,columns);
        }
    }
}();


$(document).ready( function (){
    APP.init();
});