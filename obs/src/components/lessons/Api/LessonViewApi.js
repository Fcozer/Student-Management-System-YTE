import axios from "axios";

export class LessonViewApi {
    getLesson() {
        return axios.get("/Lessons");
    }

    addLesson(formState) {
        return axios.post("/Lessons", formState);
    }

    deleteLesson(id) {
        return axios.delete("/Lessons/" +id)
    }

    updateLesson(id, newData) {
        return axios.put("/Lessons/" +id, newData)
    }
}