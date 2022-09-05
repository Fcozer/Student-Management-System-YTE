import axios from "axios";

export class StudentViewApi {
    getStudents() {
        return axios.get("/students");
    }

    addStudent(formState) {
        return axios.post("/students", formState);
    }

    deleteStudent(id) {
        return axios.delete("/students/" +id)
    }

    updateStudent(id, newData) {
        return axios.put("/students/" +id, newData)
    }
}