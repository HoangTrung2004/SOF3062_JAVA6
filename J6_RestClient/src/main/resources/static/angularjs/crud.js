var app = angular.module("my-app", []);
app.controller("my-ctrl", function($scope, $http) {
    $scope.item = {}
    $scope.items = [];

    const host = "http://localhost:8080/api";

    $scope.ctrl = {
        load() {
            var url = `${host}/categories`;
            $http.get(url).then(resp => {
                $scope.items = resp.data;
            }).catch(error => {
                console.log("get() error", error);
            })
        },
        edit(id) {
            var url = `${host}/categories/${id}`;
            $http.get(url).then(resp => {
                $scope.item = resp.data;
            }).catch(error => {
                console.log("get() error", error);
            })
        },
        create() {
            var url = `${host}/categories`;
            $http.post(url, $scope.item).then(resp => {
                this.load();
                this.reset();
            }).catch(error => {
                console.log("post() error", error);
            })
        },
        update() {
            var url = `${host}/categories/${$scope.item.id}`;
            axios.put(url, $scope.item).then(resp => {
                this.load();
            }).catch(error => {
                console.log("put() error", error);
            })
        },
        delete(id) {
            var url = `${host}/categories/${id || $scope.item.id}`;
            $http.delete(url).then(resp => {
                this.load();
                this.reset();
            }).catch(error => {
                console.log("delete() error", error);
            })
        },
        reset() {
            $scope.item = {}
        }
    }

    $scope.ctrl.load();
})