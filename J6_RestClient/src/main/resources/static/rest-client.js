const host = "http://localhost:8080/api";
const $api = {
    getAll() {
        var url = `${host}/categories`;

        axios.get(url)
            .then(resp => {
                console.log("get() success", resp.data);
            })
            .catch(error => {
                console.log("get() error", error);
            })
    },
    getById() {
        var url = `${host}/categories/C01`;

        axios.get(url).then(resp => {
            console.log("get() success", resp.data);
        }).catch(error => {
            console.log("get() error", error);
        })
    },
    post() {
        var url = `${host}/categories`;
        var data = {
            id: "C03",
            name: "Category 03"
        }

        axios.post(url, data)
            .then(resp => {
                console.log("post() success", resp.data);
            }).catch(error => {
                console.log("post() error", error);
            })
    },
    put() {
        var url = `${host}/categories/C02`;
        var data = {
            id: "C03",
            name: "Category 003"
        }

        axios.put(url, data)
            .then(resp => {
                console.log("put() success", resp.data);
            }).catch(error => {
                console.log("put() error", error);
            })
    },
    delete() {
        var url = `${host}/categories/C02`;

        axios.delete(url).then(resp => {
            console.log("delete() success", resp.data);
        }).catch(error => {
            console.log("delete() error", error);
        })
    }
}