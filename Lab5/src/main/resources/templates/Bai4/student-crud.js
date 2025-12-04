const host = "http://localhost:8080";

const $api = {
    key: null,
    get student() {
        return {
            id: $("#id").val(),
            name: $("#name").val(),
            mark: $("#mark").val(),
            gender: $("#male").prop("checked")
        };
    },
    set student(e) {
        $("#id").val(e.id);
        $("#name").val(e.name);
        $("#mark").val(e.mark);
        $("#male").prop("checked", e.gender);
        $("#female").prop("checked", !e.gender);
    },
    fillToTable() {
        var url = `${host}/students`; // lấy tất cả từ DB
        axios.get(url).then(resp => {
            $("tbody").empty();
            resp.data.forEach(e => {
                var tr = `<tr>
          <td>${e.id}</td>
          <td>${e.name}</td>
          <td>${e.mark}</td>
          <td>${e.gender ? 'Male':'Female'}</td>
          <td>
            <a href="#" onclick="$api.edit('${e.id}')">Edit</a>
            <a href="#" onclick="$api.delete('${e.id}')">Delete</a>
          </td>
        </tr>`;
                $("tbody").append(tr);
            });
        }).catch(error => alert("Lỗi tải danh sách sinh viên!"));
    },
    edit(id) {
        this.key = id.trim();
        var url = `${host}/students/${id}`;
        axios.get(url).then(resp => {
            this.student = resp.data;
        }).catch(error => alert("Lỗi tải sinh viên!"));
    },
    create() {
        var url = `${host}/students`;
        axios.post(url, this.student).then(resp => {
            this.fillToTable();
            this.reset();
        }).catch(error => alert("Lỗi thêm sinh viên mới!"));
    },
    update() {
        var url = `${host}/students/${this.key}`;
        axios.put(url, this.student).then(resp => {
            this.fillToTable();
        }).catch(error => alert("Lỗi cập nhật sinh viên!"));
    },
    delete(id) {
        var url = `${host}/students/${id || this.key}`;
        axios.delete(url).then(resp => {
            this.fillToTable();
            this.reset();
        }).catch(error => alert("Lỗi xóa sinh viên!"));
    },
    reset() {
        this.student = {id:"", name:"", mark:"", gender:false};
        this.key = null;
    }
};

$api.fillToTable();