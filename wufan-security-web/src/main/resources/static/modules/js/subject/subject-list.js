

$(function () {
    var options = {
        url: contextPath + "subject/page",
        pageNumber: 1, // 当前页面
        pageSize: 5, //每页显示条数
        columns: [
            {title: '序号', width: 20, formatter: function (value, row, index) {
                    return index+1;
                }},
            {field: 'id', visible: false},
            {field: 'no', title: '学科编号'},
            {field: 'name', title: '学科名称'},
            {field: 'major.name', title: '所属专业'},
            {field: 'action', title: '操作', width: 50, visible: false,
                align: 'center', formatter: $.operationFormatter}
        ]
    };
    $.pageTable(options);
});


function statusFormatter(value, row, index) {
    return value == true ? "<span class='badge bg-success'>否</span>"
        : "<span class='badge bg-danger'>是</span>"
}


var $table = $("#table");
//搜索功能
function searchForm() {
    var query = {
        size: 5, // 每页显示多少条
        current: 1, // 当前页码
        no: $("#no").val().trim(),
        name: $("#name").val().trim()
    };
    $table.bootstrapTable("refresh", {query: query});
}
