//获取路径
var pathName = window.document.location.pathname;
// 截取，得到项目名称
var path = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
$("#back").on("click", function() {
	window.location.href = path + "/manager/flatform/app/list";
});