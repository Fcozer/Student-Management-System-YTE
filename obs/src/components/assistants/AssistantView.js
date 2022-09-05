import * as React from "react";
import {useEffect, useState} from "react";
import {Button} from "@mui/material";
import {AddAssistant} from "./AddAssistant/AddAssistant";
import {toast} from "react-toastify";
import {AssistantViewApi} from "./Api/AssistantViewApi";
import {AppBar} from "@mui/material";
import Toolbar from '@mui/material/Toolbar';
import Box from '@mui/material/Box';
import {DataGrid} from '@mui/x-data-grid';
import { IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete'

function AssistantView() {

    const [assistants, setAssistants] = useState([]);
    const [isAddAssistantDialogOpen, setAddAssistantDialogOpen] = useState(false);
    const [selectionModel, setSelectionModel] = useState();
    const assistantViewApi = new AssistantViewApi();

    async function getAssistants() {
        const response = await assistantViewApi.getAssistants();
        setAssistants(response.data);
    }

    useEffect(() => {
        getAssistants().then(r => {});
    }, []);

    async function addAssistant(formState) {
        const response = await assistantViewApi.addAssistant(formState);
        const messageResponse = response.data;
        if (messageResponse.responseType === "SUCCESS") {
            toast.success(messageResponse.message);
            setAddAssistantDialogOpen(false);
        }
    }

    async function deleteCell(e){
        e.preventDefault();

        const selectedIDs = selectionModel;
        try {
            const response = await assistantViewApi.deleteAssistant(selectedIDs);
            const messageResponse = response.data;
            if (messageResponse.responseType === "SUCCESS") {
                toast.success(messageResponse.message);
                setAssistants((r) => r.filter((x) => !x.id===selectedIDs));
                await getAssistants();
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
        const assistantIndex = assistants.findIndex(assistant => {
            return assistant.id === params.id;
        });


        const updateAssistants = [... assistants];
        updateAssistants[assistantIndex][params.field] = newValue;
        setAssistants(updateAssistants)

        const id = params.id;
        console.log(id);
        console.log(assistantIndex);
        console.log(updateAssistants[assistantIndex]);

        const response = await assistantViewApi.updateAssistant(id,updateAssistants[assistantIndex]);
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
            <h2>Assistant</h2>
            <Box sx={{height: 500, width: '80%', paddingLeft:15}}>
                <DataGrid
                    rows={assistants}
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
                <Button className="bttn" sx={{marginLeft: 119 }} onClick={() => setAddAssistantDialogOpen(true)}>Add Assistant</Button>
            </Box>
            <AddAssistant isOpen={isAddAssistantDialogOpen}
                          close={() => setAddAssistantDialogOpen(false)}
                          addAssistant={addAssistant}
            />
        </div>
    )
}

export default AssistantView;