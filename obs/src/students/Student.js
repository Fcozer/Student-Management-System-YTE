import React, { useState } from "react";
import {AppBar} from "@mui/material";
import Toolbar from '@mui/material/Toolbar';
import {Avatar} from "@mui/material";
import ListLesson from "../components/lessons/ListLesson";
import {Button} from "@mui/material";

function Student() {
    const[lessons, setLessons] = useState([]);
    return(
        <div>
            <AppBar  className="appbar" position="static" color="secondary" >
                <Toolbar>
                    <Button href = "./Login" ><Avatar sx={{ width: 32, height: 32, marginLeft: 180}}>S</Avatar></Button>

                </Toolbar>
            </AppBar>
            <ListLesson lessons={lessons}/>
        </div>
    );
}
export default Student;