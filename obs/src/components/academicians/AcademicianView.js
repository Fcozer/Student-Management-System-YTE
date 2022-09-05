import * as React from "react";
import {useEffect, useState} from "react";
import {Button} from "@mui/material";
import {AddAcademician} from "./AddAcademician/AddAcademician";
import {toast} from "react-toastify";
import {AcademicianViewApi} from "./Api/AcademicianViewApi";
import {AppBar} from "@mui/material";
import Toolbar from '@mui/material/Toolbar';
import Box from '@mui/material/Box';
import {DataGrid} from '@mui/x-data-grid';
import { IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete'


function AcademicianView() {

    const [academicians, setAcademicians] = useState([]);
    const [isAddAcademicianDialogOpen, setAddAcademicianDialogOpen] = useState(false);
    const [selectionModel, setSelectionModel] = useState();
    const academicianViewApi = new AcademicianViewApi();

    async function getAcademicians() {
        const response = await academicianViewApi.getAcademicians();
        setAcademicians(response.data);
    }

    useEffect(() => {
        getAcademicians().then(r => {});
    }, []);

    async function addAcademician(formState) {
        const response = await academicianViewApi.addAcademician(formState);
        const messageResponse = response.data;
        if (messageResponse.responseType === "SUCCESS") {
            toast.success(messageResponse.message);
            setAddAcademicianDialogOpen(false);
        }
    }

    async function deleteCell(e){
        e.preventDefault();
        const selectedIDs = selectionModel;
        try {
            const response = await academicianViewApi.deleteAcademician(selectedIDs);
            const messageResponse = response.data;
            if (messageResponse.responseType === "SUCCESS") {
                toast.success(messageResponse.message);
                setAcademicians((r) => r.filter((x) => !x.id===selectedIDs));
                await getAcademicians();
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
        const academicianIndex = academicians.findIndex(academician => {
            return academician.id === params.id;
        });


        const updateAcademicians = [... academicians];
        updateAcademicians[academicianIndex][params.field] = newValue;
        setAcademicians(updateAcademicians)

        const id = params.id;
        console.log(id);
        console.log(academicianIndex);
        console.log(updateAcademicians[academicianIndex]);

        const response = await academicianViewApi.updateAcademician(id,updateAcademicians[academicianIndex]);
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
            <h2>Academician</h2>
            <Box sx={{height: 500, width: '80%', paddingLeft:15}}>
                <DataGrid
                    rows={academicians}
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
                <Button className="bttn" sx={{marginLeft: 119 }} onClick={() => setAddAcademicianDialogOpen(true)}>Add Student</Button>
            </Box>
            <AddAcademician isOpen={isAddAcademicianDialogOpen}
                            close={() => setAddAcademicianDialogOpen(false)}
                            addAcademician={addAcademician}
            />
        </div>
    )
}

export default AcademicianView;