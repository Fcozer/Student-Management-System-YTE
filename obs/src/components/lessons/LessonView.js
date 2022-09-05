import * as React from "react";
import {useEffect, useState} from "react";
import {Button} from "@mui/material";
import {AddLesson} from "./AddLesson/AddLesson";
import {toast} from "react-toastify";
import {LessonViewApi} from "./Api/LessonViewApi";
import {AppBar} from "@mui/material";
import Toolbar from '@mui/material/Toolbar';
import Box from '@mui/material/Box';
import {DataGrid} from '@mui/x-data-grid';
import { IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete'


function LessonView() {

    const [lessons, setLessons] = useState([]);
    const [isAddLessonDialogOpen, setAddLessonDialogOpen] = useState(false);
    const [selectionModel, setSelectionModel] = useState();
    const lessonViewApi = new LessonViewApi();

    async function getLessons() {
        const response = await lessonViewApi.getLesson();
        setLessons(response.data);
    }

    useEffect(() => {
        getLessons().then(r => {});
    }, []);

    async function addLesson(formState) {
        const response = await lessonViewApi.addLesson(formState);
        const messageResponse = response.data;
        if (messageResponse.responseType === "SUCCESS") {
            toast.success(messageResponse.message);
            setAddLessonDialogOpen(false);
        }
    }

    async function deleteCell(e){
        e.preventDefault();
        console.log("id" + selectionModel)
        const selectedIDs = selectionModel;
        try {
            const response = await lessonViewApi.deleteLesson(selectedIDs);
            const messageResponse = response.data;
            if (messageResponse.responseType === "SUCCESS") {
                toast.success(messageResponse.message);
                setLessons((r) => r.filter((x) => !x.id===selectedIDs));
                await getLessons().then();
                console.log(messageResponse)
            }
            else{
                toast.error(messageResponse.message);
                console.log(messageResponse)
            }
        }catch (error) {
            console.log(error.response.status)
            toast.error("Please select id");
        }
    }

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
            editable: true,
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
            editable: true,
        },
        {
            field: "delete",
            width: 75,
            disableColumnMenu: true,
            renderHeader: () => {
                return (
                    <IconButton
                        onClick={deleteCell}
                    >
                        <DeleteIcon color="secondary" />
                    </IconButton>
                );
            }
        },
    ]

    return (
        <div>
            <AppBar  className="appbar" position="static" sx={{ bgcolor: "black" }} >

            <Toolbar>

                </Toolbar>
            </AppBar>
            <h2>Lessons</h2>
            <Box sx={{height: 500, width: '80%', paddingLeft: 15}}>
                <DataGrid
                    rows={lessons}
                    columns={columns}
                    pageSize={5}
                    rowsPerPageOptions={[5]}
                    experimentalFeatures={{ newEditingApi: true }}
                    onCellEditStop={(params,event) =>handleCellChange(params, event.target.value)}
                    checkboxSelection
                    selectionModel={selectionModel}
                    hideFooterSelectedRowCount
                    onSelectionModelChange={(selection) => {
                        if (selection.length > 1) {
                            const selectionSet = new Set(selectionModel);
                            const result = selection.filter((s) => !selectionSet.has(s));

                            setSelectionModel(result);
                        } else {
                            setSelectionModel(selection);
                        }
                    }}
                />
                <Button className="bttn" sx={{marginLeft: 119 }} onClick={() => setAddLessonDialogOpen(true)}>Add Lesson</Button>
            </Box>
            <AddLesson isOpen={isAddLessonDialogOpen}
                       close={() => setAddLessonDialogOpen(false)}
                       addLesson={addLesson}
            />
        </div>
    )
}

export default LessonView;