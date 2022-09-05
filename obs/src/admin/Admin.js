import React from "react";
import {AppBar} from "@mui/material";
import Toolbar from '@mui/material/Toolbar';
import MenuIcon from '@mui/icons-material/Menu';
import {MenuItem} from "@mui/material";
import {Button} from "@mui/material";
import {Menu} from "@mui/material";
import {Avatar} from "@mui/material";
import {Grid} from "@mui/material";






function Admin() {
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
            <AppBar  className="appbar" position="static"  sx={{ bgcolor: "black" }}>
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
                        <MenuItem onClick={handleClose}>
                            <Button href="./StudentView"> Student</Button>
                        </MenuItem>
                        <MenuItem onClick={handleClose}>
                            <Button href="./AssistantView">Assistant</Button>
                        </MenuItem>
                        <MenuItem onClick={handleClose}>
                            <Button href="./AcademicianView">Academician</Button>
                        </MenuItem>
                        <MenuItem onClick={handleClose}>
                            <Button href="./LessonView">Lesson</Button>
                        </MenuItem>
                    </Menu>
                    <Button href="./Login"><Avatar sx={{ width: 40, height: 40}}>A</Avatar></Button>

                </Toolbar>
            </AppBar>
            <h1 className="title"> HI DEAR ADMIN! HOW IS YOUR DAY GOING?</h1>

            <Grid paddingTop={15} container spacing={2}>
                <Grid button xs={4} md={3}>
                    <Button className="bttn" href ="./StudentView">Student Edit Page</Button>
                </Grid>
                <Grid button xs={4} md={3}>
                    <Button className="bttn" href ="./AcademicianView">Academician Edit Page</Button>
                </Grid>
                <Grid button xs={4} md={3}>
                    <Button className="bttn" href ="./AssistantView">Assistant Edit Page</Button>
                </Grid>
                <Grid button xs={4} md={3}>
                    <Button className="bttn" href ="./LessonView">Lesson Edit Page</Button>
                </Grid>

            </Grid>
        </div>
    );

}
export default Admin;