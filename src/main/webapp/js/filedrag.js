/*
 filedrag.js - HTML5 File Drag & Drop demonstration
 Featured on SitePoint.com
 Developed by Craig Buckler (@craigbuckler) of OptimalWorks.net
 */

/** 是否需要修改按钮的样式 */
var selected = false;

(function() {

	// getElementById
	function $id(id) {
		return document.getElementById(id);
	}


	// output information
	function Output(msg) {
		var m = $id("messages");
		m.innerHTML = msg;
	}


	// file drag hover
	function FileDragHover(e) {
		e.stopPropagation();
		e.preventDefault();
		e.target.className = (e.type == "dragover" ? "hover" : "");
	}


	// file selection
	function FileSelectHandler(e) {

		if (!selected){
			$("#uploadImageBtn").css("height","52px");
		}

		selected = true;

		if (selected){
			$("#uploadImageBtn").removeAttr("disabled");
			$("#uploadImageBtn").css({cursor:"pointer",background:"#0099e5"})
		}
		// cancel event and hover styling
		FileDragHover(e);

		// fetch FileList object
		var files = e.target.files || e.dataTransfer.files;

		// process all File objects
		for (var i = 0, f; f = files[i]; i++) {
			ParseFile(f);
		}
	}


	// output file information
	function ParseFile(file) {
		Output(
			"<p>文件上传信息: <strong>" + file.name +
			"</strong> 类型: <strong>" + file.type +
			"</strong> 大小: <strong>" + file.size +
			"</strong> 字节数</p>"
		);

	}


	// initialize
	function Init() {

		var fileselect = $id("fileselect"),
			filedrag = $id("filedrag"),
			submitbutton = $id("submitbutton");

		// file select
		fileselect.addEventListener("change", FileSelectHandler, false);

	}

	// call initialization file
	if (window.File && window.FileList && window.FileReader) {
		Init();
	}


})();