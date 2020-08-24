

$(function () {
    var options = {
        url: contextPath + "teacher/design/look",
        pageNumber: 1, // 当前页面
        pageSize: 5, //每页显示条数
        columns: [
            {title: '序号', width: 20, formatter: function (value, row, index) {
                    return index+1;
                }},
            {field: 'id', visible: false},
            {field: 'student.username', title: '学号'},
            {field: 'student.nickName', title: '学生姓名'},
            {field: 'design.no', title: '课程设计编号'},
            {field: 'design.name', title: '课程设计名称'},
            {field: 'upload', title: '是否完成',formatter:statusFormatter},
            {field: 'uploadTime', title: '完成时间',formatter:statusFormatter2},
            {field: 'action', title: '操作', width: 50, visible: false,
                align: 'center', formatter: $.operationFormatter}
        ]
    };

    $.pageTable(options);
});


function statusFormatter(value, row, index) {
    return value == 0 ? "<span class='badge bg-danger'>否</span>"
        : "<span class='badge bg-success'>是</span>"
}

function statusFormatter2(value, row, index) {
    return value == ""||value==null ? "<span class='badge  bg-danger'>未上传</span>"
        : "<span class='badge bg-success'>"+value+"</span>"
}
var $table = $("#table");

