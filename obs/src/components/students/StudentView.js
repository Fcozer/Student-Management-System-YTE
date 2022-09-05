import * as React from "react";
import {useEffect, useState} from "react";
import {Button} from "@mui/material";
import {AddStudent} from "./AddStudent/AddStudent";
import {toast} from "react-toastify";
import {StudentViewApi} from "./Api/StudentViewApi";
import {AppBar} from "@mui/material";
import Toolbar from '@mui/material/Toolbar';
import Box from '@mui/material/Box';
import {DataGrid} from '@mui/x-data-grid';
import { IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete'

function StudentView() {

    const [students, setStudents] = useState([]);
    const [isAddStudentDialogOpen, setAddStudentDialogOpen] = useState(false);
    const [selectionModel, setSelectionModel] = useState();
    const studentViewApi = new StudentViewApi();

    async function getStudents() {
        const response = await studentViewApi.getStudents(); //await: asenkron çalışma
        setStudents(response.data);
    }

    useEffect(() => {
        getStudents().then(r => {});
    }, []);

    async function addStudent(formState) {
        const response = await studentViewApi.addStudent(formState);
        const messageResponse = response.data;
        if (messageResponse.responseType === "SUCCESS") {
            toast.success(messageResponse.message);
            setAddStudentDialogOpen(false);
        }
    }

    async function deleteCell(e){
        e.preventDefault();
        const selectedIDs = selectionModel;
        try {
            const response = await studentViewApi.deleteStudent(selectedIDs);
            const messageResponse = response.data;
            if (messageResponse.responseType === "SUCCESS") {
                toast.success(messageResponse.message);
                setStudents((r) => r.filter((x) => !x.id===selectedIDs));
                await getStudents();
            }
            else{
                toast.error(messageResponse.message);
            }
        }catch (error) {
            toast.error("Please select id");
        }
    }

    async function handleCellChange(params, newValue) {
        const studentIndex = students.findIndex(student => {
            return student.id === params.id;
        });


        const updateStudents = [... students];
        updateStudents[studentIndex][params.field] = newValue;
        setStudents(updateStudents)

        const id = params.id;

        const response = await studentViewApi.updateStudent(id,updateStudents[studentIndex]);
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
            field: "name",
            headerName: "Name",
            width: 150,
            editable: true,
        },
        {
            field: "surname",
            headerName: "Surname",
            width: 150,
            editable: true,
        },
        {
            field: "email",
            headerName: "E-Mail",
            width: 150,
            editable: true,
        },
        {
            field: "username",
            headerName: "Username",
            width: 150,
            editable: true,
        },
        {
            field: "password",
            headerName: "Password",
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
            <h2>Students</h2>
            <Box sx={{height: 500, width: '80%', paddingLeft: 15}}>
                <DataGrid
                    rows={students}
                    columns={columns}
                    pageSize={5}
                    rowsPerPageOptions={[5]}
                    experimentalFeatures={{ newEditingApi: true }}
                    onCellEditStop={(params,event) =>handleCellChange(params, event.target.value)} //GÜncelleme iptal edilirse eski kayıtları getirir
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
                <Button className="bttn" sx={{marginLeft: 119 }} onClick={() => setAddStudentDialogOpen(true)}>Add Student</Button>
            </Box>
            <AddStudent isOpen={isAddStudentDialogOpen}
                        close={() => setAddStudentDialogOpen(false)}
                        addStudent={addStudent}
            />
        </div>
    )
}

export default StudentView;