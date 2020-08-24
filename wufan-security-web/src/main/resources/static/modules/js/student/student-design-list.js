

$(function () {
    var options = {
        url: contextPath + "student/design/page",
        pageNumber: 1, // 当前页面
        pageSize: 5, //每页显示条数
        columns: [
            {title: '序号', width: 20, formatter: function (value, row, index) {
                    return index+1;
                }},
            {field: 'id', visible: false},
            {field: 'design.name', title: '课程设计名'},
            {field: 'design.describe', title: '课程设计描述'},
            {field: 'upload', title: '是否完成',formatter:statusFormatter},
            {field: 'uploadTime', title: '上传时间'},
            {field: 'design.subject.name', title: '所属学科'},
            {field: 'action', title: '操作', width: 50, visible: false,
                align: 'center', formatter: $.operationFormatter}
        ]
    };
    $.pageTable(options);
});


function statusFormatter(value, row, index) {
    return value != 0 ? "<span class='badge bg-success'>是</span>"
        : "<span class='badge bg-danger'>否</span>"
}


var $table = $("#table");
//搜索功能
function searchForm() {

    var query = {
        size: 5, // 每页显示多少条
        current: 1, // 当前页码
        name: $("#name").val().trim()
    };
    $table.bootstrapTable("refresh", {query: query});

}
