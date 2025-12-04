const host = "https://fpolyedu.firebaseio.com";
const $api = {
	get student(){
return {
	id: $("#id").val(),
	name: $("#name").val(),
	mark: $("#mark").val(),
	gender: $("#male").prop("checked")
}
	},
	set student(e){
$("#id").val(e.id);
$("#name").val(e.name);
$("#mark").val(e.mark);
$("#male").prop("checked", e.gender);
$("#female").prop("checked", !e.gender);
	},
    fillToTable() {
var url = `${host}/students.json`;
axios.get(url).then(resp => {
	$("tbody").empty();
    Object.keys(resp.data).forEach(key => {
		var e = resp.data[key];
        var tr = `<tr>
            <td>${e.id}</td>
            <td>${e.name}</td>
            <td>${e.mark}</td>
            <td>${e.gender?'Male':'Female'}</td>
            <td>
                <a href="#" onclick="$api.edit('${key}')">Edit</a>
                <a href="#" onclick="$api.delete('${key}')">Delete</a>
            </td>
        </tr>`;
        $("tbody").append(tr);
    });
}).catch(error => {
    alert("Lỗi tải danh sách sinh viên!");
})
    },
    edit(key) {
this.key = key.trim();
var url = `${host}/students/${key}.json`;
axios.get(url).then(resp => {
	this.student = resp.data;
}).catch(error => {
    alert("Lỗi tải sinh viên!");
})
    },
    create() {
var url = `${host}/students.json`;
axios.post(url, this.student).then(resp => {
    this.fillToTable();
    this.reset();
}).catch(error => {
    alert("Lỗi thêm sinh viên mới!");
})
    },
    update() {
var url = `${host}/students/${this.key}.json`;
axios.put(url, this.student).then(resp => {
    this.fillToTable();
}).catch(error => {
    alert("Lỗi tải cập nhật sinh viên!");
})
    },
    delete(key) {
var url = `${host}/students/${key || this.key}.json`;
axios.delete(url).then(resp => {
	this.fillToTable();
    this.reset();
}).catch(error => {
    alert("Lỗi xóa sinh viên!");
})
    },
    reset(){
this.student = {}
	}
}
$api.fillToTable();