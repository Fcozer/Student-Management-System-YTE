import * as React from "react";
import {useState} from "react";
import {Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField} from "@mui/material";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import Box from "@mui/material/Box";
export function AddLesson(props) {

    const [formState, setFormState] = useState({});
    const [timeSlot, setTimeSlot] = useState('');


    const handleChange = (event) => {
        setTimeSlot(event.target.value);
        const newState = {...formState};
        newState["timeSlot"] = event.target.value;
        setFormState(newState);
    };
    function onFormInputChange(event) {
        const field = event.target.name;
        const value = event.target.value;
        const newState = {...formState};
        newState[field] = value;
        setFormState(newState);
    }


    return (
        <Dialog open={props.isOpen}>
            <DialogTitle>Add new lesson</DialogTitle>
            <DialogContent>
                <TextField onChange={onFormInputChange} name="lessonName" label="lessonName" fullWidth></TextField>
                <Box sx={{ minWidth: 120 }}>
                    <FormControl fullWidth>
                        <InputLabel id="demo-simple-select-label">Lesson Time</InputLabel>
                        <Select
                            labelId="demo-simple-select-label"
                            id="demo-simple-select"
                            value={timeSlot}
                            label="lesson Time"
                            onChange={handleChange}
                        >
                            <MenuItem value={"8.30-09.20"}>08:30-09:20</MenuItem>
                            <MenuItem value={"9.30-10.20"}>09:30-10:20</MenuItem>
                            <MenuItem value={"10.30-11.20"}>10:30-11:20</MenuItem>
                            <MenuItem value={"11.30-12.20"}>11:30-12:20</MenuItem>
                            <MenuItem value={"13.30-14.20"}>13:30-14:20</MenuItem>
                            <MenuItem value={"14.30-15.20"}>14:30-15:20</MenuItem>
                            <MenuItem value={"15.30-16.20"}>15:30-16:20</MenuItem>
                            <MenuItem value={"16.30-17.20"}>16:30-17:20</MenuItem>
                        </Select>
                    </FormControl>
                </Box>
                <TextField onChange={onFormInputChange} name="academicianId" label="Academician" fullWidth></TextField>
            </DialogContent>
            <DialogActions>
                <Button onClick={() => props.close()} color="secondary">Cancel</Button>
                <Button onClick={() => props.addLesson(formState)}>Submit</Button>
            </DialogActions>
        </Dialog>
    );
}