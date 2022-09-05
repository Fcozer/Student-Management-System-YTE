import axios from "axios";

export class AcademicianViewApi {
    getAcademicians() {
        return axios.get("/academicians");
    }

    addAcademician(formState) {
        return axios.post("/academicians", formState);
    }

    deleteAcademician(id) {
        return axios.delete("/academicians/" +id)
    }

    updateAcademician(id, newData) {
        return axios.put("/academicians/" +id, newData)
    }
}