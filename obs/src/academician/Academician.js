import React from "react";
import {AppBar} from "@mui/material";
import Toolbar from '@mui/material/Toolbar';
import MenuIcon from '@mui/icons-material/Menu';
import {useState} from "react";

import {Button} from "@mui/material";
import {Menu} from "@mui/material";
import {Avatar} from "@mui/material";
import ListLesson from "../components/lessons/ListLesson";

function Academician() {
    const[lessons, setLessons] = useState([]);
    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);
    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };
    return(
        <div>
            <AppBar  className="appbar" position="static" color="secondary" >
                <Toolbar>
                    <Button
                        id="basic-button"
                        aria-controls={open ? 'basic-menu' : undefined}
                        aria-haspopup="true"
                        aria-expanded={open ? 'true' : undefined}
                        onClick={handleClick}
                    >
                        <MenuIcon />
                    </Button>
                    <Menu
                        id="basic-menu"
                        anchorEl={anchorEl}
                        open={open}
                        onClose={handleClose}
                        MenuListProps={{
                            'aria-labelledby': 'basic-button',
                        }}
                    >

                    </Menu>
                    <Button href="./Login"><Avatar sx={{ width: 32, height: 32, marginLeft: 170}}>AC</Avatar></Button>
                </Toolbar>
            </AppBar>
            <ListLesson lessons={lessons}/>
        </div>
    );

}
export default Academician;