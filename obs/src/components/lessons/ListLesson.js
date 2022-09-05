import * as React from 'react';
import Box from '@mui/material/Box';
import {DataGrid} from '@mui/x-data-grid';
import {LessonViewApi} from "./Api/LessonViewApi";
import {useEffect, useState} from "react";
import {AppBar} from "@mui/material";
import Toolbar from "@mui/material/Toolbar";

const columns = [
    {
        field: "id",
        headerName: "ID",
        width: 150,
        editable: false,
    },
    {
        field: "lessonName",
        headerName: "Lesson Name",
        width: 150,
        editable: false,
    },
    {
        field: "timeSlot",
        headerName: "Time Slot",
        width: 150,
        editable: true,
    },
    {
        field: "academicianId",
        headerName: "Academician Id",
        width: 150,
        editable: false,
    },
]


function ListLesson() {

    const [lessons, setLessons] = useState([]);
    const lessonViewApi = new LessonViewApi();

    async function getLessons() {
        const response = await lessonViewApi.getLesson();
        setLessons(response.data);
    }

    useEffect(() => {
        getLessons().then(r => {});
    }, []);

    async function handleCellChange(params, newValue) {
        const lessonIndex = lessons.findIndex(lesson => {
            return lesson.id === params.id;
        });


        const updateLessons = [... lessons];
        updateLessons[lessonIndex][params.field] = newValue;
        setLessons(updateLessons)

        const id = params.id;
        console.log(id);
        console.log(lessonIndex);
        console.log(updateLessons[lessonIndex]);

        const response = await lessonViewApi.updateLesson(id,updateLessons[lessonIndex]);
        const messageResponse = response.data;
    }

    return (
        <div>
            <h2>Lessons</h2>

            <Box sx={{height: 400, width: '80%', marginLeft: 15}}>
                <DataGrid
                    rows={lessons}
                    columns={columns}
                    pageSize={5}
                    rowsPerPageOptions={[5]}
                    experimentalFeatures={{ newEditingApi: true }}
                    onCellEditStop={(params,event) =>handleCellChange(params, event.target.value)}
                    checkboxSelection
                    disableSelectionOnClick
                />
            </Box>
        </div>
    );
}
export default ListLesson;