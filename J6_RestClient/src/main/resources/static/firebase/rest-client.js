const host = "https://fpolyedu.firebaseio.com";

function doGetAll() {
	var url = `${host}/students.json`;
	axios.get(url).then(resp => {
		console.log("Success", resp.data);
	}).catch(error => {
		console.log("Error", error);
	});
}

function doGetByKey() {
	var key = "-ORe_wrD_2hVSTzGjMJ0";
	var url = `${host}/students/${key}.json`;
	axios.get(url).then(resp => {
		console.log("Success", resp.data);
	}).catch(error => {
		console.log("Error", error);
	})
}

function doPost() {
	var url = `${host}/students.json`;
	var data = {
		"id": "SV006",
		"name": "Sinh viên 006",
		"mark": 4.5,
		"gender": true
	}
	axios.post(url, data).then(resp => {
		console.log("Success", resp.data);
	}).catch(error => {
		console.log("Error", error);
	})
}

function doPut() {
	var key = "-ORe_wrD_2hVSTzGjMJ0";
	var url = `${host}/students/${key}.json`;
	var data = {
		"id": "SV006",
		"name": "Sinh viên 600",
		"mark": 8.5,
		"gender": true
	}
	axios.put(url, data).then(resp => {
		console.log("Success", resp.data);
	}).catch(error => {
		console.log("Error", error);
	})
}

function doDelete() {
	var key = "-ORe_wrD_2hVSTzGjMJ0";
	var url = `${host}/students/${key}.json`;
	axios.delete(url).then(resp => {
		console.log("delete() success", resp.data);
	}).catch(error => {
		console.log("delete() error", error);
	})
}